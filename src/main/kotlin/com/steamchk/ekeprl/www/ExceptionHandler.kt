package com.steamchk.ekeprl.www

import org.springframework.http.HttpStatus
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.ModelAndView

@ControllerAdvice
class ExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleException(exception: Exception, model: Model): ModelAndView {
        model.addAttribute("error", exception.message)
        return ModelAndView("error/globalError", model.asMap(), HttpStatus.INTERNAL_SERVER_ERROR)
    }

    @ExceptionHandler(NoSuchElementException::class)
    fun handleNotFoundException(exception: NoSuchElementException, model: Model): ModelAndView {
        model.addAttribute("error", "Resource not found")
        return ModelAndView("error/404", model.asMap(), HttpStatus.NOT_FOUND)
    }
}