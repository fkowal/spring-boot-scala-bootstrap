package com.github.fkowal.domain

import org.springframework.data.repository.CrudRepository

trait UserRepository extends CrudRepository[User, String] {
}
