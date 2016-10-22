package controllers

import javax.inject.Inject

import models.User
import models.slick.Tables.UserRow
import play.api.libs.json.Json
import utils.BCryptPasswordHasher
import views.forms.{ SignInForm, SignUpForm }
import play.api.i18n.{ I18nSupport, MessagesApi }
import play.api.libs.mailer.MailerClient
import play.api.mvc.{ Action, Controller }
import play.api.{ Configuration, Logger }
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{ Failure, Success }

/**
 * Created by elarib on 10/21/16.
 */
/**
 * The sign up controller.
 */

class SignUpController @Inject() (
  val mailerClient: MailerClient,
  val messagesApi: MessagesApi,
  val configuration: Configuration
)
    extends Controller with I18nSupport {

  def getView = Action {
    println("Cool")

    Ok(views.html.signInUp())
  }

  /**
   * Registers a new user.
   *
   * @return The result to display.
   */
  def post = Action.async(parse.json) {
    implicit request =>

      SignUpForm.form.bindFromRequest.fold(
        formWithErrors => {
          println("###" + request.body.toString())
          Future.successful(BadRequest(formWithErrors.errorsAsJson))
        },
        goodForm => {
          User.findByEmail(goodForm.email).flatMap {
            case Some(user) =>
              Future.successful(BadRequest(Json.obj("message" -> "user.exists")))

            case None =>
              // There is NO User with the same email, so we're cool now
              val authInfo = BCryptPasswordHasher.hash(goodForm.password._1)

              val newUser = UserRow(
                id = 0,
                email = Some(goodForm.email),
                password = BCryptPasswordHasher.hash(goodForm.password._1),
                firstName = Some(goodForm.firstName),
                lastName = Some(goodForm.lastName),
                age = goodForm.age,
                description = Some(goodForm.description)
              )

              for {
                user <- User.create(newUser)
              } yield user match {
                case Success(s) =>
                  // Send the Activation Link

                  Ok(Json.obj("message" -> "User was added succefully"))

                case Failure(f) =>
                  BadRequest(Json.obj("message" -> f.getMessage))
              }
          }

        }
      )
  }

  //	def sendTheActivationLink(student: StdStudentRow) = {
  //
  //		new EmailService(mailerClient).sendEmail(subject = "Confirmation Link", senderName = "Globuni Admin", senderEmail = "dev@globuni.com", receiverName = student.firstName.get + " " + student.lastName.get, receiverEmail = student.emailAddress, contentHTML = s"""<signInField><body>Welcome to Globuni</br> Please confirm you account, click  : <a href="${configuration.underlying.getString("urlFrontend")}/confirm-account/${student.activationKey.get}">here</a> </body></signInField>""")
  //
  //	}

}
