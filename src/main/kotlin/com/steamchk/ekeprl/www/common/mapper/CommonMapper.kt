package com.steamchk.ekeprl.www.common.mapper

import com.steamchk.ekeprl.www.common.model.LoginModel
import org.apache.ibatis.annotations.Mapper

//Mapper어노테이션은 build.gradle의 mybatis의존성을 추가해주면된다.
@Mapper
interface CommonMapper {

    // 로그인 확인
    fun loginChk(userid: String, userpw: String) : HashMap<String, String>

    //회원정보 가져오기
    fun getuserinfo(userid: String) : LoginModel?

    //아이디 중복체크
    fun joinduplicateChk(userid : String) : Int

    //회원등록
    fun joinsubmit(userid: String, userpw: String, useremail: String)

}