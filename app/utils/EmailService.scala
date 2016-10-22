package utils

/**
 * Created by elarib on 10/21/16.
 */
import play.api.{ BuiltInComponentsFromContext, ApplicationLoader }
import play.api.ApplicationLoader.Context
import play.api.inject.guice.GuiceApplicationBuilder
import javax.inject.Inject
import play.api.libs.mailer._
import java.io.File
import org.apache.commons.mail.EmailAttachment

class EmailService @Inject() (val mailerClient: MailerClient) {

  def sendEmail(subject: String, senderName: String, senderEmail: String, receiverName: String, receiverEmail: String, contentHTML: String) = {

    val email = Email(
      subject,
      s"""$senderName <$senderEmail>""",
      Seq(s"""$receiverName <$receiverEmail>"""),
      bodyHtml = Some(s"""$contentHTML""")
    )
    mailerClient.send(email)
  }

}

