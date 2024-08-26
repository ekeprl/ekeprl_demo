package com.steamchk.ekeprl.www

import org.springframework.boot.web.servlet.error.ErrorController
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
class ErrorController : ErrorController {

    @RequestMapping("/error")
    fun handleError(): String {
        return "error/customError" // 기본 에러 페이지
    }

    @RequestMapping("/test-error")
    fun Errortest(): String {
        return "error/globalError" // 기본 에러 페이지
    }

    @RequestMapping("/test-404")
    fun Errortest404(): String {
        return "error/404" // 기본 에러 페이지
    }

}