package controllers

import javax.inject.Inject
import models.slick.Tables
import models.Component._

import models.slick.Tables.ObjectifRow
import play.api.libs.json
import spray.json._
import models.Content._
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.Configuration
import play.api.i18n.{ I18nSupport, MessagesApi }
import play.api.libs.json.{ Json, JsObject }
import play.api.libs.mailer.MailerClient
import play.api.mvc.{ Action, Controller }
import utils.Secured

import scala.concurrent.Future
import scala.util.{ Failure, Success }

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

      models.Component.findByUserId(request.user.id).map(_ match {
        case Success(result) =>

          val objectif = result._1.toJson
          val workexperiences = result._2.toJson
          val educations = result._3.toJson
          val competences = result._4.toJson
          //          val competenceElts = list._5

          //          Ok(Json.obj(
          //            "user" -> request.user,
          //            //            "objectif" -> objectif.getOrElse(ObjectifRow(0, Some(""), request.user.id)).toJson.compactPrint
          //            "objectif" -> objectif.get.toJson
          //          ))
          //          objectif.get.toJson.prettyPrint
          println(educations)
          val list = Map(
            "objectif" -> objectif,
            "educations" -> educations,
            "workexperiences" -> workexperiences,
            "competences" -> competences

          ).toJson

          Ok(list.prettyPrint)

        case Failure(f) =>
          BadRequest(f.getMessage)
      })

  }

}
