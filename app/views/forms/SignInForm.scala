package views.forms

import play.api.data.Form
import play.api.data.Forms._

/**
 * Created by elarib on 10/21/16.
 */
/**
 * The form which handles the sign In process.
 */
object SignInForm {

  /**
   * A play framework form.
   */
  val form = Form(
    mapping(
      "email" -> email,
      "password" -> nonEmptyText
    )(Data.apply)(Data.unapply)
  )

  /**
   * The form data.
   *
   * @param email The email of the user.
   * @param password The password of the user.
   */
  case class Data(
    email: String,
    password: String
  )
}

