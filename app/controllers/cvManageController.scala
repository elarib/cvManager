package controllers

import javax.inject.Inject
import models._
import models.slick.Tables
import models.Component._

import models.slick.Tables.ObjectifRow
import play.api.libs.json
import spray.json._
import scala.concurrent.ExecutionContext.Implicits.global
import play.api.Configuration
import play.api.i18n.{ I18nSupport, MessagesApi }
import play.api.libs.json.{ Json, JsObject }
import play.api.libs.mailer.MailerClient
import play.api.mvc.{ Cookie, Action, Controller }
import utils.{ JwtHelper, Secured }

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

          val user = Map(
            "id" -> (request.user.id).toJson,
            "firstName" -> (request.user.firstName).toJson,
            "lastName" -> (request.user.lastName).toJson,
            "email" -> (request.user.email).toJson,
            "description" -> (request.user.description).toJson,
            "age" -> (request.user.age).toJson
          )

          val objectif = result._1.toJson
          val workexperiences = result._2.toJson
          val educations = result._3.toJson
          //          val competences = result._4.map(_._1).toSet.map((e: Option[String]) => Map(e.getOrElse("") -> result._4.filter(_._1 == e).map(elt => Map("name" -> elt._3, "detail" -> elt._4))))
          //          val competences = result._4.map(cpmt => Map("name" -> cpmt._1, "idElt" -> cpmt._2, "nameElt" -> cpmt._3, "detailElt" -> cpmt._4)).toJson
          val competences = result._4.map(cpmt => new CompetenceDetails(idCmpt = cpmt._1, nameCmpt = cpmt._2.getOrElse(""), idElt = cpmt._3, nameElt = cpmt._4.getOrElse(""), detailElt = cpmt._5.getOrElse("")))

          //          val competences = result._4.map(cpmt => new CompetenceDetails(idCmpt = 1, nameCmpt = cpmt._1, idElt = cpmt._2, nameElt = cpmt._3, detailElt = cpmt._4.getOrElse("")))
          //          val competences = result._4.map(cpmt => new CompetenceDetails2(idCmpt = 1, nameCmpt = cpmt._1.getOrElse(""), idElt = cpmt._2, nameElt = cpmt._3.getOrElse(""), detailElt = cpmt._4.getOrElse("")))

          val list = Map(
            "user" -> user.toJson,
            "objectif" -> objectif,
            "educations" -> educations,
            "workexperiences" -> workexperiences,
            "competences" -> competences.map(_.nameCmpt).toSet.map((e: String) =>
              competences.filter(_.nameCmpt == e).map {
                cmpt => (new CompetenceDetails2(cmpt.idCmpt, cmpt.nameCmpt, (new CompetenceElt2(cmpt.idElt, cmpt.nameElt, cmpt.detailElt))))
              }).toJson

          ).toJson

          Ok(list.prettyPrint)

        case Failure(f) =>
          BadRequest(f.getMessage)
      })

  }

  def updateUserInfos = Authenticated.async(parse.json) {
    implicit request =>
      val userId = (request.body \ "id").getOrElse(json.JsNumber(0)).as[Long];
      val email = (request.body \ "email").getOrElse(json.JsString("")).as[String]
      val description = (request.body \ "description").getOrElse(json.JsString("")).as[String]
      val age = (request.body \ "age").getOrElse(json.JsNumber(0)).as[Int]
      val firstName = (request.body \ "firstName").getOrElse(json.JsString("")).as[String]
      val lastName = (request.body \ "lastName").getOrElse(json.JsString("")).as[String]

      User.updateUserInfoById(userId, email, description, age, firstName, lastName).map(_ match {
        case Success(e) =>
          //update Cookies JWT Token too
          Ok(e + "")
            .withCookies(Cookie("user", JwtHelper.generateJwtFromData(User(id = userId, firstName = firstName, lastName = lastName, email = email, description = description, age = age))))

        case Failure(f) =>
          BadRequest(f.getMessage)
      })

  }

  def updateObjectif = Authenticated.async(parse.json) {
    implicit request =>
      val objectifId = (request.body \ "id").getOrElse(json.JsNumber(0)).as[Long];
      val newObjectif = (request.body \ "content").getOrElse(json.JsString("")).as[String]

      updateObjectifById(objectifId, newObjectif).map(_ match {
        case Success(e) =>

          Ok(e + "")

        case Failure(f) =>
          BadRequest(f.getMessage)
      })

  }

  def updateEducation = Authenticated.async(parse.json) {
    implicit request =>
      val id = (request.body \ "id").getOrElse(json.JsNumber(0)).as[Long];
      val description = (request.body \ "description").getOrElse(json.JsString("")).as[String]
      val place = (request.body \ "place").getOrElse(json.JsString("")).as[String]
      val yearFrom = (request.body \ "yearFrom").getOrElse(json.JsNumber(0)).as[Int]
      val yearTo = (request.body \ "yearTo").getOrElse(json.JsNumber(0)).as[Int]

      Component.updateEducationById(id, description, place, yearFrom, yearTo).map(_ match {
        case Success(e) =>
          //update Cookies JWT Token too
          Ok(e + "")

        case Failure(f) =>
          BadRequest(f.getMessage)
      })

  }

  def updateWork = Authenticated.async(parse.json) {
    implicit request =>
      val id = (request.body \ "id").getOrElse(json.JsNumber(0)).as[Long];
      val description = (request.body \ "description").getOrElse(json.JsString("")).as[String]
      val place = (request.body \ "place").getOrElse(json.JsString("")).as[String]
      val yearFrom = (request.body \ "yearFrom").getOrElse(json.JsNumber(0)).as[Int]
      val yearTo = (request.body \ "yearTo").getOrElse(json.JsNumber(0)).as[Int]

      Component.updateWorkById(id, description, place, yearFrom, yearTo).map(_ match {
        case Success(e) =>
          //update Cookies JWT Token too
          Ok(e + "")

        case Failure(f) =>
          BadRequest(f.getMessage)
      })

  }

  def updateCompetence = Authenticated.async(parse.json) {
    implicit request =>
      val id = (request.body \ "id").getOrElse(json.JsNumber(0)).as[Long];
      val name = (request.body \ "name").getOrElse(json.JsString("")).as[String]

      Component.updateCmptById(id, name).map(_ match {
        case Success(e) =>
          //update Cookies JWT Token too
          Ok(e + "")

        case Failure(f) =>
          BadRequest(f.getMessage)
      })

  }

  def updateCompetenceElt = Authenticated.async(parse.json) {
    implicit request =>
      val id = (request.body \ "id").getOrElse(json.JsNumber(0)).as[Long];
      val name = (request.body \ "name").getOrElse(json.JsString("")).as[String]
      val detail = (request.body \ "detail").getOrElse(json.JsString("")).as[String]

      Component.updateCmptEltById(id, name, detail).map(_ match {
        case Success(e) =>
          //update Cookies JWT Token too
          Ok(e + "")

        case Failure(f) =>
          BadRequest(f.getMessage)
      })

  }

}
