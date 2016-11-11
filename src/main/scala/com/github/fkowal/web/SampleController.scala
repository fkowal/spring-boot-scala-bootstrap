package com.github.fkowal.web

import com.github.fkowal.domain.{User, UserRepository}
import org.slf4j.{Logger, LoggerFactory}
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation._

@RestController
@RequestMapping(value = Array("/user"))
class SampleController(repo: UserRepository) {
  val logger: Logger = LoggerFactory.getLogger(classOf[SampleController])

  @RequestMapping(method = Array(RequestMethod.GET))
  def sample: UserDto = UserDto("example", 100)

  @RequestMapping(value = Array("/{id}"), method = Array(RequestMethod.GET))
  def get(@PathVariable id: String): User =
    repo.findOne(id)

  @RequestMapping(method = Array(RequestMethod.POST))
  @ResponseStatus(HttpStatus.CREATED)
  def post(@RequestBody userDto: UserDto): Unit = {
    val user = User(null, userDto.name, userDto.age)
    val saved = repo.save(user)
    logger.info(s"Created user $user")
  }
}
