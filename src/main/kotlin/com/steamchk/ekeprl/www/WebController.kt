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

    //로그인 페이지
    @RequestMapping(value = [("/login")])
    @Throws(Exception::class)
    fun login(session: HttpSession, model: Model): String {
        /*return service.webLoginPage(session, model)*/
        return "login"
    }

    //로그인 페이지
    @RequestMapping(value = [("/login-try")] , method = [(RequestMethod.POST)])
    @Throws(Exception::class)
    fun logintry(userid: String, userpw: String): String {
        return service.logtry(userid, userpw)

    }


    //회원가입 설정
    @RequestMapping(value = [("/join")], method = [(RequestMethod.POST)])
    fun join(session: HttpSession, model: Model): String {
        return "join"

    }

    //회원가입 - 아이디중복체크
    @ResponseBody
    @RequestMapping(value = [("/join-duplicateChk")], method = [(RequestMethod.POST)])
    fun joinduplicateChk(@RequestParam("userid") userid : String): JSONObject {
        return service.joinDulpicateChk(userid)
    }

    //회원등록
    @ResponseBody
    @RequestMapping(value = [("/join-submit")], method = [(RequestMethod.POST)])
    @Throws(Exception::class)
    fun joinsubmit(@RequestParam("userid") userid : String,
                   @RequestParam("userpw") userpw : String,
                   @RequestParam("email") email : String): JSONObject {
        return service.joinsubmit(userid, userpw, email)
    }



}


