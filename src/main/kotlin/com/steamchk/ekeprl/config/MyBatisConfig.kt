package com.steamchk.ekeprl.config

import org.apache.ibatis.session.SqlSessionFactory
import org.mybatis.spring.SqlSessionFactoryBean
import org.mybatis.spring.annotation.MapperScan
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import javax.sql.DataSource

@Configuration
@MapperScan(basePackages = ["com.steamchk.ekeprl"]) // 매퍼가 위치한 패키지 지정
class MyBatisConfig {

    @Bean
    fun sqlSessionFactory(dataSource: DataSource): SqlSessionFactory? {
        val sessionFactory = SqlSessionFactoryBean()
        sessionFactory.setDataSource(dataSource)

        // XML 매퍼 파일이 Kotlin 소스 파일과 동일한 경로에 있을 경우
        sessionFactory.setMapperLocations(
            *PathMatchingResourcePatternResolver()
                .getResources("classpath*:com/steamchk/ekeprl/www/common/mapper/*.xml")
        )

        return sessionFactory.`object`
    }
}