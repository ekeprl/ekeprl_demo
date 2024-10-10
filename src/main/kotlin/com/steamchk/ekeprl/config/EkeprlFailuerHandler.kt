package com.steamchk.ekeprl.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.authentication.BadCredentialsException
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.authentication.AuthenticationFailureHandler
import org.springframework.stereotype.Component
import org.springframework.web.servlet.FlashMap
import org.springframework.web.servlet.support.SessionFlashMapManager


@Component
class EkeprlFailuerHandler : AuthenticationFailureHandler{
    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        exception: AuthenticationException
        ) {
        val flashMap = FlashMap()
        var redirectUrl: String?

        val logger = LoggerFactory.getLogger(this::class.java)

        when(exception) {
            is BadCredentialsException -> {


                flashMap["MESSAGE"] = "아이디와 비밀번호를 확인해주세요"

                logger.info("FlashMap MESSAGE: " + flashMap.get("MESSAGE"));
                redirectUrl= "/login"
            }else -> {
                flashMap["MESSAGE"] = "오류가 발생했습니다. 문의해주세요"
                redirectUrl= "/login"
            }

        }
        /* 이부분 작성하지 않으면 flashmap사용불가 */
        val flashMapManager = SessionFlashMapManager()

        flashMapManager.saveOutputFlashMap(flashMap, request, response)

        response.sendRedirect(redirectUrl)
    }
}