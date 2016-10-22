package models

import models.slick.Tables
import models.slick.Tables.{ComponentRow, ObjectifRow}
import play.api.libs.json.Json
import scala.concurrent.ExecutionContext.Implicits.global
import _root_.slick.driver.MySQLDriver.api._
import models.slick.DB._

/**
	* Created by elarib on 10/23/16.
	*/



object Component {

	implicit val componentFormat = Json.format[ComponentRow]

	val components = Tables.Component
	val objectifs = Tables.Objectif
	val skills = Tables.Skill
	val competence = Tables.Skill
	val skills = Tables.Skill



	def findByUserId(userId: Long) = {
		val query = for{
			userComponent <- components.filter(_.id === userId)
			userObjectif <- objectifs.filter()
		} yield userComponent

		db.run(query.result.headOption.asTry)
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



