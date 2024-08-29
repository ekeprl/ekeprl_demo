package com.steamchk.ekeprl.www.common.service

import com.steamchk.ekeprl.www.common.mapper.CommonMapper
import jakarta.servlet.http.HttpSession
import org.apache.ibatis.annotations.Param
import org.json.simple.JSONObject
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import org.springframework.ui.Model
import kotlin.jvm.Throws

@Service

class CommonService {

    private val logger = LoggerFactory.getLogger(this::class.java)

    @Autowired
    private lateinit var mapper : CommonMapper


    //로그인 페이지 반환
    fun webLoginPage(session: HttpSession, model: Model) : String {

        var result = "/login"

        return result
    }


    //로그인 시도
    fun logtry(@Param("userid") userid: String,@Param("userpw") userpw: String) : String {
        var result = "redirect:/login"

        try {
            val userinfo = mapper.getuserinfo(userid)

            if(userinfo === null) {
               logger.error("존재하지 않는 아이디입니다. 다시 입력해주세요.")
            }else if(userinfo.userpw != userpw){
               logger.error("잘못된 비밀번호입니다. 다시 입력해주세요.")
            }else{
                result = "redirect:/main"
            }
        }catch (e : Exception) {
            logger.error("ERROR", e)
        }
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

    //회원등록
    @Throws(Exception :: class)
    @Transactional(rollbackFor = [(Exception::class)])
    fun joinsubmit(userid: String, userpw: String, email:String) : JSONObject{

        var jsono = JSONObject()
        try {

            mapper.joinsubmit(userid, userpw, email)

            jsono["RESULT"] = "OK"
            jsono["MESSAGE"] = "회원가입이 완료되었습니다."

        } catch (e : Exception){
            logger.error("ERROR : " + e)
            jsono["RESULT"] = "ERROR"
            jsono["MESSAGE"] = "회원가입에 실패했습니다."
        }
        return  jsono

    }


}