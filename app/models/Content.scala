package models

import models.User._
import spray.json._

import models.slick.Tables
import models.slick.Tables._
import play.api.libs.json.Json
import scala.concurrent.ExecutionContext.Implicits.global
import _root_.slick.driver.MySQLDriver.api._
import models.slick.DB._

/**
 * Created by elarib on 10/23/16.
 */

object Component extends DefaultJsonProtocol {

  //  implicit val userFormat = jsonFormat6(models.UserRow)
  //  implicit val componentFormat = jsonFormat3(ComponentRow)
  implicit val objectifFormat = jsonFormat3(ObjectifRow)
  implicit val workExperienceFormat = jsonFormat7(WorkExperienceRow)
  implicit val educationFormat = jsonFormat6(EducationRow)
  implicit val competencesFormat = jsonFormat3(CompetenceRow)
  implicit val competencesEltFormat = jsonFormat4(CompetenceEltRow)

  //  val components = Tables.Component
  val objectifs = Tables.Objectif
  val workexperiences = Tables.WorkExperience
  val educations = Tables.Education
  val competences = Tables.Competence
  val competenceElts = Tables.CompetenceElt

  def findByUserId(userId: Long) = {
    val query = for {
      //      userComponent <- components.filter(_.id === userId)
      userObjectif <- objectifs.filter(_.userId === userId).result.headOption
      userWorkExperences <- workexperiences.filter(_.userId === userId).result
      userEducations <- educations.filter(_.userId === userId).result
      userCompetences <- (competences.filter(_.userId === userId) join competenceElts on (_.id === _.competenceId))
        .map(cpt => (cpt._1.name, cpt._2.name, cpt._2.detail)).result
    } yield (userObjectif, userWorkExperences, userEducations, userCompetences)

    db.run(query.asTry)
  }

  def findByObjectifByUserId(userId: Long) = {
    val userObjectif = objectifs.filter(_.userId === userId)
    db.run(userObjectif.result.asTry)
  }

  def updateObjectifById(id: Long, newObjectif: String) = {
    val q = for { obj <- objectifs if obj.id === id } yield obj.content
    val updateAction = q.update(Some(newObjectif))
    db.run(updateAction.asTry)
  }

  def updateEducationById(id: Long, newDescription: String, newPlace: String, newYearFrom: Int, newYearTo: Int) = {
    val query = educations.filter(_.id === id)
      .map(u => (u.description, u.place, u.yearFrom, u.yearTo))
      .update(Some(newDescription), Some(newPlace), newYearFrom, newYearTo)
    db.run(query.asTry)
  }


  def updateWorkById(id: Long, newDescription: String, newPlace: String, newYearFrom: Int, newYearTo: Int) = {
    val query = workexperiences.filter(_.id === id)
      .map(u => (u.description, u.place, u.yearFrom, u.yearTo))
      .update(Some(newDescription), Some(newPlace), newYearFrom, newYearTo)
    db.run(query.asTry)
  }

}
