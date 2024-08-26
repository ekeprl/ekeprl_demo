package com.steamchk.ekeprl.www


import com.steamchk.ekeprl.www.common.service.CommonService
import jakarta.servlet.http.HttpSession
import org.json.simple.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

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

    @GetMapping("/test-db")
    fun testDbConnection(model: Model): String {
        // 간단한 쿼리를 실행해 봅니다.
        val result = jdbcTemplate.queryForObject("SELECT 1", Int::class.java)

        // 결과를 모델에 추가합니다.
        model.addAttribute("result", result)

        // "test-db.html" 뷰를 반환합니다.
        return "test-db"
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
    @GetMapping("/join-duplicateChk")
    fun joinduplicateChk(userid : String): JSONObject {
        return service.joinDulpicateChk(userid)
    }


}


