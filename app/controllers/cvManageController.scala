package controllers

import javax.inject.Inject


import models.Objectif
import play.api.Configuration
import play.api.i18n.{ I18nSupport, MessagesApi }
import play.api.libs.json.{ Json, JsObject }
import play.api.libs.mailer.MailerClient
import play.api.mvc.{ Action, Controller }
import utils.Secured

import scala.concurrent.Future

/**
 * Created by elarib on 10/22/16.
 */
class cvManageController @Inject() (
  val mailerClient: MailerClient,
  val messagesApi: MessagesApi,
  val configuration: Configuration
)
    extends Controller with I18nSupport with Secured {

  def cv = Authenticated {
    Ok(views.html.home())
  }

  def getUserInfo = Authenticated.async(parse.anyContent) {
    implicit request =>
      Future.successful(Ok(Json.obj(
	      "user" -> request.user,
	      "objectif" -> Objectif.findByUserId(request.user.id)


      )))
  }

}
