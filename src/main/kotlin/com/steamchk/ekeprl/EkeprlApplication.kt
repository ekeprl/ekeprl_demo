package com.steamchk.ekeprl

import com.steamchk.ekeprl.www.WebController
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.context.annotation.ComponentScan


@SpringBootApplication
@ComponentScan(basePackages = ["com.steamchk.ekeprl.www"])
class EkeprlApplication

fun main(args: Array<String>) {
    /*val context: ConfigurableApplicationContext = runApplication<EkeprlApplication>(*args)*/

    runApplication<EkeprlApplication>(*args)
}


