package com.steamchk.ekeprl.config

import com.steamchk.ekeprl.www.common.mapper.CommonMapper
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component


@Component
class CustomAuthenticationProvider : AuthenticationProvider{

    @Autowired
    private lateinit var mapper : CommonMapper

    override fun authenticate(authentication: Authentication?): Authentication {

        val userid = authentication?.principal.toString()
        val userpw = authentication?.credentials.toString()

        val logger = LoggerFactory.getLogger(this::class.java)
        logger.info("111111 $userid")
        logger.info("2222222 $userpw")
        mapper.getuserinfo(userid).also {user ->

            if(user?.userpw.equals(userpw)) {
                val authorities = listOf(SimpleGrantedAuthority("ROLE_USER"))
                return UsernamePasswordAuthenticationToken(userid,userpw,authorities)

            }else {
                logger.info("333333" + user?.userpw)
                throw BadCredentialsException("비밀번호 오류 : BadCredentialsException")

            }
        }
    }

    override fun supports(authentication: Class<*>): Boolean {
        return UsernamePasswordAuthenticationToken::class.java.isAssignableFrom(authentication)
    }


}