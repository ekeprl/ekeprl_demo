package com.steamchk.ekeprl.www


import com.steamchk.ekeprl.www.common.service.CommonService
import jakarta.servlet.http.HttpSession
import org.json.simple.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody

@Controller
class WebController() {

    // JdbcTemplate 주입
    @Autowired
    lateinit var jdbcTemplate: JdbcTemplate

    @Autowired
    lateinit var service: CommonService

    @GetMapping("/helloo")
    @Throws(Exception::class)
    fun hello(): String {
        return "/hello"
    }

    @GetMapping("/login")
    fun login(session: HttpSession, model: Model): String {
        return service.webLoginPage(session, model)
    }



    //회원가입 설정
    @GetMapping("/join")
    fun join(session: HttpSession, model: Model): String {
        return "join"
    }

    //회원가입 설정
    @ResponseBody
    @RequestMapping(value = [("/join-duplicateChk")], method = [(RequestMethod.POST)])
    fun joinduplicateChk(@RequestParam("userid") userid : String): JSONObject {
        return service.joinDulpicateChk(userid)
    }


}


