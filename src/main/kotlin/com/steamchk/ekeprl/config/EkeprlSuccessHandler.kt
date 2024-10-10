package com.steamchk.ekeprl.config

import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.LoggerFactory
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AuthenticationSuccessHandler
import org.springframework.stereotype.Component
import org.springframework.web.servlet.FlashMap
import org.springframework.web.servlet.support.SessionFlashMapManager


@Component
class EkeprlSuccessHandler : AuthenticationSuccessHandler{
    override fun onAuthenticationSuccess(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authentication: Authentication?
    ) {
        val flashMap = FlashMap()
        val redirectUrl = "/main"

        val logger = LoggerFactory.getLogger(this::class.java)
        logger.info("CustomSuccessHandler")
        flashMap["MESSAGE"] = "성공적으로 로그인 되었습니다."

        val flashMapManager = SessionFlashMapManager()
        if (request != null && response != null) {
            flashMapManager.saveOutputFlashMap(flashMap, request, response)
        }
        response?.sendRedirect(redirectUrl)
    }
}