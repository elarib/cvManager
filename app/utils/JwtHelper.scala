package utils

import models.{ User }
import pdi.jwt.{ Jwt, JwtClaim, JwtJson, JwtAlgorithm }
import play.api.libs.json.{ JsObject, Json }

/**
 * Created by elarib on 10/22/16.
 */
object JwtHelper {
  /*Key to be used in jwt signature algorithm */
  private val key: String = "ELARIB@XhubÉÀ&-"
  /*verification algorithm */
  private val algo = JwtAlgorithm.HS256

  /**
   * generate a data with a given data and experation duration
   *
   * @param data       data to be added in the token
   * @param expireAfter data expires after this duration
   * @return token
   */
  def generateJwtFromData(data: User, expireAfter: Long = 24 * 3600): String = {

    val token = JwtJson.encode(Json.obj("user" -> Json.toJson(data)), key, algo)
    token.toString()
  }

  def decodeJwtData(data: String) = {

    JwtJson.decodeJson(data, key, Seq(algo))

  }
  /**
   * evaluate a given data
   *
   * @param data jwt data to be validated
   * @return
   */

  def checkJwtData(data: String) = {
    Jwt.decode(data, key, Seq(JwtAlgorithm.HS256))
  }
}
