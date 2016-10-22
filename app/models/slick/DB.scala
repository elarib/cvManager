package models.slick

/**
 * Created by elarib on 10/21/16.
 */
import slick.driver.MySQLDriver.api._

object DB {

  val db = Database.forConfig("db")
}
