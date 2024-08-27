package com.steamchk.ekeprl.www.common.mapper

import org.apache.ibatis.annotations.Mapper

//Mapper어노테이션은 build.gradle의 mybatis의존성을 추가해주면된다.
@Mapper
interface CommonMapper {

    fun joinduplicateChk(userid : String) : Int

}