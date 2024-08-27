package com.steamchk.ekeprl.www.common.service

import com.steamchk.ekeprl.www.common.mapper.CommonMapper
import com.steamchk.ekeprl.www.common.model.JoinReq
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
            val dulpiChk = mapper.joinduplicateChk(userid)
            if (dulpiChk > 0) {
                // 중복된 값이 있는 경우
                jsono.put("RESULT", "ERR")
                jsono.put("MESSAGE", "$userid 는 사용중인 ID입니다.")
            } else {
                // 중복된 값이 없는 경우
                jsono.put("RESULT", "OK")
                jsono.put("MESSAGE", "사용 가능한 ID입니다.")
            }
        } catch (e: Exception) {
            // 예외 처리
            jsono.put("RESULT", "error")
            jsono.put("MESSAGE", "An error occurred: ${e.message}")
        }

        return jsono

    }




}