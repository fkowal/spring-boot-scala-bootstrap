package com.github.fkowal

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootApplication
class ScalaDemoApplication

object ScalaDemoApplication extends App {
		SpringApplication.run(classOf[ScalaDemoApplication], args: _*)
}
