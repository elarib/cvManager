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
      userCompetences <- competences.filter(_.userId === userId).result
      //      userCompetencesElt <- competenceElts.filter(_.competenceId === )
    } yield (userObjectif, userWorkExperences, userEducations, userCompetences)

    db.run(query.asTry)
  }

  def findByObjectifByUserId(userId: Long) = {
    val userObjectif = objectifs.filter(_.userId === userId)
    db.run(userObjectif.result.asTry)
  }
}

object Content {

  //	implicit val objectifFormat = Json.format[ObjectifRow]

  //	val component

  //	val objectifs = Tables.Objectif
  //	val skills = Tables.Skill
  //	val competence = Tables.Skill
  //	val skills = Tables.Skill
  //
  //	def findByUserId(userId: Long) = {
  //		val query = for{
  //			userObjectif <- objectifs.filter(_.id === userId)
  //			userSkill <-  userSkills.filter(_.id === userId)
  //		} yield (userObjectif,userSkill)
  //
  //		db.run(query.result.headOption.asTry)
  //	}

}

object Objectif {

  //	def findByUserId(userId: Long) = {
  //		val query = objectifs.filter(_.id === userId)
  //		db.run(query.result.headOption.asTry)
  //	}

}

object Skill {

  object Competence {

  }
  object ElementCompetence {

  }

}

object Education {

}

object WorkExperience {

}

object UserInfo {

}

object Role {

}

