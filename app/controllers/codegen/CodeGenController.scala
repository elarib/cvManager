package controllers.codegen

import javax.inject.Inject
import play.api.mvc.{ Controller, Action }
import slick.driver.MySQLDriver.api._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{ Failure, Success }

/**
 * Created by elarib on 10/21/16.
 */
class CodeGenController @Inject() (environment: play.api.Environment, configuration: play.api.Configuration) extends Controller {

  val db = Database.forConfig("db")

  def generateTableScala() = Action {

    val slickDriver = "slick.driver.MySQLDriver"
    val jdbcDriver = configuration.underlying.getString("db.driver")
    val url = configuration.underlying.getString("db.url")
    val outputFolder = environment.rootPath.getPath + "/app"
    val pkg = "models.slick"
    val user = configuration.underlying.getString("db.properties.user")
    val password = configuration.underlying.getString("db.properties.password")

    slick.codegen.SourceCodeGenerator.main(
      Array(slickDriver, jdbcDriver, url, outputFolder, pkg, user, password)
    )

    Ok("Setup Done")
  }

  //	def generateTableDB() = Action.async {
  //
  ////		db.run(DBIO.seq(
  ////			Tables.schema.create,
  ////			Tables.StdAccountStatus += new StdAccountStatusRow(accountStatusId = 0, accountStatusDescription = Some("VALID")),
  ////			Tables.StdAccountStatus += new StdAccountStatusRow(accountStatusId = 0, accountStatusDescription = Some("WATING-VALIDATION")),
  ////			Tables.StdLanguage += new StdLanguageRow(languageId = 0, description = Some("ENGLISH")),
  ////			Tables.StdLanguage += new StdLanguageRow(languageId = 0, description = Some("FRENCH")),
  ////			Tables.StdUniversity += new StdUniversityRow(universityId = 0, universityName = Some("University of Ottawa"), universityEmailDomain = Some("uottawa.ca")),
  ////			Tables.StdUniversity += new StdUniversityRow(universityId = 0, universityName = Some("Carleton University"), universityEmailDomain = Some("carleton.ca")),
  ////			Tables.StdUniversity += new StdUniversityRow(universityId = 0, universityName = Some("Algonquin College"), universityEmailDomain = Some("algonquincollege.com"))
  ////		).asTry).map(_ match {
  ////			case Success(s) =>
  ////				Ok("Good! Database was  intilitiazed succefuly")
  ////			case Failure(e) => NotAcceptable("Error ! : Database was NOT intilitiazed because  " + e.getMessage)
  ////
  ////		})
  //	}
}
