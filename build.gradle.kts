plugins {
    kotlin("jvm") version "1.9.24"
    kotlin("plugin.spring") version "1.9.24"
    id("org.springframework.boot") version "3.3.2"
    id("io.spring.dependency-management") version "1.1.6"
}

group = "com.steamchk"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_17 // Java 17을 설정합니다.


repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    compileOnly("org.projectlombok:lombok")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    annotationProcessor("org.projectlombok:lombok")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    implementation("org.springframework.boot:spring-boot-starter-thymeleaf")
    implementation("nz.net.ultraq.thymeleaf:thymeleaf-layout-dialect") // Thymeleaf 의존성 추가

    implementation(kotlin("stdlib"))

    // Tomcat 제외
    implementation("org.springframework.boot:spring-boot-starter-web") {
        exclude(group = "org.springframework.boot", module = "spring-boot-starter-tomcat")
    }

    // Undertow 의존성
    implementation("org.springframework.boot:spring-boot-starter-undertow")

    // DB 연결(마리아)
    implementation("org.mariadb.jdbc:mariadb-java-client:3.2.0")
    // Spring Boot JDBC 스타터
    implementation("org.springframework.boot:spring-boot-starter-jdbc")

    //mybatis연결(@Mapper도 영향)
    implementation("org.mybatis.spring.boot:mybatis-spring-boot-starter:3.0.3")

    //json
    implementation("com.googlecode.json-simple:json-simple:1.1.1")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
