package com.github.fkowal.infrastructure.jackson

import com.fasterxml.jackson.module.scala.DefaultScalaModule
import org.springframework.context.annotation.{Bean, Configuration}

@Configuration
class JacksonConfig {

  /**
    * @see org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration
    * takes care of registering the ScalaModule
    * in the created ObjectMapper Bean
    *
    * this enabled Rest, Swagger, Mongo to use scala case classes
    */
  @Bean def scalaModule = DefaultScalaModule
}
