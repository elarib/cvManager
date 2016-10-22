package utils

import javax.inject.Inject

import org.mindrot.jbcrypt.BCrypt

/**
 * Created by elarib on 10/21/16.
 */
class BCryptPasswordHasher @Inject() (configuration: play.api.Configuration)

/**
 * The companion object.
 */
object BCryptPasswordHasher {

  /**
   * The ID of the hasher.
   */
  val ID = "bcrypt"

  val logRounds: Int = 10

  /**
   * Hashes a password.
   *
   * This implementation does not return the salt separately because it is embedded in the hashed password.
   * Other implementations might need to return it so it gets saved in the backing store.
   *
   * @param plainPassword The password to hash.
   * @return A PasswordInfo containing the hashed password.
   */
  def hash(plainPassword: String) = {
    val bcryptSalt = "$2a$10$ofuRCNtzxBeT/QPCgN3hGe"

    Some(BCrypt.hashpw(plainPassword, bcryptSalt))
  }

  /**
   * Checks if a password matches the hashed version.
   *
   * @param hashedPassword The password retrieved from the DB.
   * @param suppliedPlainPassword The password supplied by the user trying to log in.
   * @return True if the password matches, false otherwise.
   */
  def matches(suppliedPlainPassword: String, hashedPassword: String) = {
    BCrypt.checkpw(suppliedPlainPassword, hashedPassword)
  }
}
