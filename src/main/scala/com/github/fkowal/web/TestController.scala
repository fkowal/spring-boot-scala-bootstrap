package com.github.fkowal.web

import com.github.fkowal.domain.{User, UserRepository}
import io.swagger.annotations.ApiOperation
import org.springframework.web.bind.annotation.{RequestMapping, RequestMethod, RestController}

import scala.concurrent.Future

@RestController
@RequestMapping(value = Array("/scala"))
class TestController(repo: UserRepository) {

  @RequestMapping(value = Array("/list"), method = Array(RequestMethod.GET))
  @ApiOperation(value = "listSample", response = classOf[User], responseContainer = "List")
  def get(): List[User] =
      List(User("id", name = "name", age = 123))

  @RequestMapping(value = Array("/future"), method = Array(RequestMethod.GET))
  @ApiOperation(value = "future", response = classOf[User])
  def future(): Future[User] = Future.successful(User("future", "maciej", 35))
}
