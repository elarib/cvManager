package views.forms

import play.api.data.Form
import play.api.data.Forms._
import play.api.data.validation.{ ValidationError, Invalid, Valid, Constraint }
import play.api.libs.json._
import play.api.libs.functional.syntax._

/**
 * Created by elarib on 10/21/16.
 */
/**
 * The form which handles the sign up process.
 */
object SignUpForm {

  /**
   * A play framework form.
   */
  val form = Form(
    mapping(
      "firstName" -> nonEmptyText,
      "lastName" -> nonEmptyText,
      "email" -> email,
      "password" -> tuple(
        "main" -> text(minLength = 8),
        "confirm" -> text
      ).verifying("Password don't match", password => password._1 == password._2),
      "description" -> nonEmptyText,
      "age" -> number
    )(Data.apply)(Data.unapply)
  )

  /**
   * The form data
   */
  case class Data(

    firstName: String,
    lastName: String,
    email: String,
    password: (String, String),
    description: String,
    age: Int
  )

  case class Password(
    password: String,
    passwordConfirmation: String
  )

}
