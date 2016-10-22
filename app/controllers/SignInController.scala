package controllers

import javax.inject.Inject
import utils.JwtHelper

import scala.concurrent.ExecutionContext.Implicits.global
import models.{ User }
import play.api.Configuration
import play.api.i18n.{ I18nSupport, MessagesApi }
import play.api.libs.json.Json
import play.api.libs.mailer.MailerClient
import play.api.mvc.{ Cookie, Action, Controller }
import views.forms.{ SignInForm, SignUpForm }
import pdi.jwt._
import scala.concurrent.Future
import scala.util.{ Failure, Success }

/**
 * Created by elarib on 10/21/16.
 */
/**
 * The sign up controller.
 */

class SignInController @Inject() (
  val mailerClient: MailerClient,
  val messagesApi: MessagesApi,
  val configuration: Configuration
)
    extends Controller with I18nSupport {

  /**
   * Registers a new user.
   *
   * @return The result to display.
   */
  def post = Action.async(parse.json) {
    implicit request =>

      SignInForm.form.bindFromRequest.fold(
        formWithErrors => {
          println("###" + request.body.toString)
          Future.successful(BadRequest(formWithErrors.errorsAsJson))
        },
        goodForm => User.findByCredentials(goodForm.email, goodForm.password).flatMap {
          case Success(s) =>
            s match {
              case Some(user) =>

                println(user.toString)

                Future(Ok(Json.obj("message" -> "connected"))
                  .withCookies(Cookie("user", JwtHelper.generateJwtFromData(User(id = user.id, firstName = user.firstName.get, lastName = user.lastName.get, email = user.email.get, description = user.description.get, age = user.age)))))

              // .addingToJwtSession("user", User(id = user.id, firstName = user.firstName.get, lastName = user.lastName.get, email = user.email.get, description = user.description.get, age = user.age)))
              case _ =>
                // update the unsuccessfulLogin List

                Future(BadRequest(Json.obj("message" -> "No User was found with this credentials", "status" -> "CREDENTIALS-ERROR")))
            }

          case Failure(e) => Future(BadRequest(Json.obj("message" -> "The was some problem with the DB", "status" -> "DB-ERROR")))

        }
      )
  }

  //	def sendTheActivationLink(student: StdStudentRow) = {
  //
  //		new EmailService(mailerClient).sendEmail(subject = "Confirmation Link", senderName = "Globuni Admin", senderEmail = "dev@globuni.com", receiverName = student.firstName.get + " " + student.lastName.get, receiverEmail = student.emailAddress, contentHTML = s"""<signInField><body>Welcome to Globuni</br> Please confirm you account, click  : <a href="${configuration.underlying.getString("urlFrontend")}/confirm-account/${student.activationKey.get}">here</a> </body></signInField>""")
  //
  //	}

}
