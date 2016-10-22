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
