package com.github.fkowal.infrastructure.scala

import org.springframework.context.annotation.Configuration
import org.springframework.web.method.support.HandlerMethodReturnValueHandler
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter

@Configuration
class SpringScalaConfig extends WebMvcConfigurerAdapter {
  override def addReturnValueHandlers(returnValueHandlers: java.util.List[HandlerMethodReturnValueHandler]): Unit = {
    val handler = new ScalaFutureReturnValueHandler()
    returnValueHandlers.add(0, handler)
  }
}
