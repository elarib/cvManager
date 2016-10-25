package utils

import java.security.MessageDigest

/**
 * Created by elarib on 10/21/16.
 */
object Generator {

  def generateToken(email: String) =
    {
      // The generated token will be :     userEmail#userId#itsRegistrationTimestamp
      val token = email + "#"
      val algorithm: MessageDigest = MessageDigest.getInstance("SHA-256")
      val defaultBytes: Array[Byte] = token.getBytes
      algorithm.reset
      algorithm.update(defaultBytes)
      val messageDigest: Array[Byte] = algorithm.digest
      getHexString(messageDigest)

    }

  def getHexString(messageDigest: Array[Byte]): String = {
    val hexString: StringBuffer = new StringBuffer
    messageDigest foreach { digest =>
      val hex = Integer.toHexString(0xFF & digest)
      if (hex.length == 1) hexString.append('0') else hexString.append(hex)
    }

    hexString.toString
  }

}
object Md5 {

  def hash(s: String): String = {
    val md5 = MessageDigest.getInstance("MD5").digest(s.getBytes)
    asString(md5)
  }

  val hexDigits = "0123456789abcdef".toCharArray

  def asString(bytes: Array[Byte]) = {
    bytes.foldLeft("") { case (agg, b) => agg + hexDigits((b >> 4) & 0xf) + hexDigits(b & 0xf) }
  }

}