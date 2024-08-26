package com.steamchk.ekeprl.www.common.service

import com.steamchk.ekeprl.www.common.mapper.CommonMapper
import jakarta.servlet.http.HttpSession
import org.json.simple.JSONObject
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.ui.Model

@Service

class CommonService {

    @Autowired
    private lateinit var mapper : CommonMapper


    //로그인 페이지 반환
    fun webLoginPage(session: HttpSession, model: Model) : String {

        var result = "/login"

        return result
    }

    //회원가입 중복 조회
    fun joinDulpicateChk(userid : String) : JSONObject {

        var jsono = JSONObject()
        try {
            val dulpiChk = mapper.joinduplicateChl(userid)
            if (dulpiChk > 0) {
                // 중복된 값이 있는 경우
                jsono.put("status", "error")
                jsono.put("message", "사용중인 ID입니다.")
            } else {
                // 중복된 값이 없는 경우
                jsono.put("status", "success")
                jsono.put("message", "사용 가능한 ID입니다.")
            }
        } catch (e: Exception) {
            // 예외 처리
            jsono.put("status", "error")
            jsono.put("message", "An error occurred: ${e.message}")
        }

        return jsono

    }




}