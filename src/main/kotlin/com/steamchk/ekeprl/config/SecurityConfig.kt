package com.steamchk.ekeprl.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import kotlin.jvm.Throws


@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true, securedEnabled = true, proxyTargetClass = true)
class SecurityConfig {

    //provider
    @Bean
    fun customAuthenticationProvider(): AuthenticationProvider {
        return CustomAuthenticationProvider()
    }

    @Bean
    @Throws(Exception::class)
    fun filterChain(http:HttpSecurity) : SecurityFilterChain{
        return http.csrf {
            obj: CsrfConfigurer<HttpSecurity> -> obj.disable()
        }.sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        }.formLogin {
            it.loginPage("/login").permitAll() //이친구가 없으면 시큐리티 기본 login페이지가 로딩된다.-> 만들어놓은 js,html 작동을 안한다
                .usernameParameter("userid")
                .passwordParameter("userpw")
                .loginProcessingUrl("/login-security-try").permitAll() //시큐리티를 이용한 로그인 할 uri
                .successHandler(authenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler())
                .permitAll()
        }.authorizeHttpRequests {
            it.anyRequest().permitAll()
        }.logout {
            it.logoutUrl("/logout").permitAll()
                .logoutSuccessUrl("/login").permitAll()
                .invalidateHttpSession(true)
                .deleteCookies("SESSION", "JSESSIONID", "XSRF-TOKEN")
        }.sessionManagement {
            it.sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                .invalidSessionUrl("/login")
                .maximumSessions(2)
                .maxSessionsPreventsLogin(false)
                .expiredUrl("/login")
        }.httpBasic { obj: HttpBasicConfigurer<HttpSecurity> -> obj.disable() }
        .build()
    }

    // 성공 핸들러 함수
    private fun authenticationSuccessHandler(): AuthenticationSuccessHandler {
        return EkeprlSuccessHandler()
    }

    // 실패 핸들러 함수
    private fun authenticationFailureHandler(): AuthenticationFailureHandler {
        return EkeprlFailuerHandler()
    }



}