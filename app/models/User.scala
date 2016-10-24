package models

import _root_.slick.driver.MySQLDriver.api._
import models.slick.DB._
import play.api.libs.json.Json
import utils.BCryptPasswordHasher
import scala.concurrent.ExecutionContext.Implicits.global
import models.slick.Tables

/**
 * Created by elarib on 10/21/16.
 */
object User extends {

  implicit val userFormat = Json.format[User]

  val users = Tables.User

  def findAll() = db.run(users.result)

  def create(user: Tables.UserRow) = {

    val userIdQuery = users returning users.map(_.id) += user

    db.run(userIdQuery.asTry)

  }

  def findById(id: Long) = {

    val query = users.filter(_.id === id)
    db.run(query.result.headOption.asTry)

  }

  def findByEmail(email: String) = {

    val query = users.filter(_.email === email)
    db.run(query.result.headOption)

  }

  def findByCredentials(email: String, password: String) = {

    val query = users.filter(user => user.email === email && user.password === BCryptPasswordHasher.hash(password).get)

    db.run(
      query.result.headOption.asTry
    )

  }

  def updateUserInfoById(userId: Long, email: String, description: String, age: Int, firstName: String, lastName: String) = {
    val query = users.filter(_.id === userId)
      .map(u => (u.email, u.description, u.age, u.firstName, u.lastName))
      .update(Some(email), Some(description), age, Some(firstName), Some(lastName))
    db.run(query.asTry)
  }

}
case class User(id: Long, firstName: String, lastName: String, email: String, description: String, age: Int) {
  //def isAdmin: Boolean = (role.toLowerCase == "admin")
}

