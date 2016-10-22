package utils

/**
 * Created by elarib on 10/22/16.
 */

import play.libs

import scala.concurrent.Future
import play.api.libs.concurrent.Execution.Implicits._

import play.api.mvc._
import play.api.mvc.Results._
import play.api.libs.json._

import pdi.jwt._

import models.User

import scala.util.{ Failure, Success }

class AuthenticatedRequest[A](val user: User, request: Request[A]) extends WrappedRequest[A](request)

trait Secured {
  def Authenticated = AuthenticatedAction
  //  def Admin = AdminAction
}

object AuthenticatedAction extends ActionBuilder[AuthenticatedRequest] {
  def invokeBlock[A](request: Request[A], block: AuthenticatedRequest[A] => Future[Result]) = {

    request.cookies.get("user") match {
      case Some(cookie) =>

        JwtHelper.decodeJwtData(cookie.value) match {
          case Success(e) =>

            ((e \ "user").get).as[User] match {
              case user =>

                block(new AuthenticatedRequest(user, request)).map(_.refreshJwtSession(request))

              case _ =>
                Future.successful(Redirect("/login"))
            }

          case Failure(e) =>
            Future.successful(Redirect("/login"))

        }
      case None =>
        Future.successful(Redirect("/login"))

    }

  }
}

//object StudentOwnAction extends ActionBuilder[AuthenticatedRequest] {
//  def invokeBlock[A](request: Request[A], block: AuthenticatedRequest[A] => Future[Result]) =
//    request.jwtSession.getAs[User]("user") match {
//      case Some(user) => block(new AuthenticatedRequest(user, request)).map(_.refreshJwtSession(request))
//      case _ => Future.successful(Unauthorized)
//    }
//}

//object AdminAction extends ActionBuilder[AuthenticatedRequest] {
//  def invokeBlock[A](request: Request[A], block: AuthenticatedRequest[A] => Future[Result]) =
//    request.jwtSession.getAs[User]("user") match {
//      case Some(user) if user.isAdmin => block(new AuthenticatedRequest(user, request)).map(_.refreshJwtSession(request))
//      case Some(_) => Future.successful(Forbidden.refreshJwtSession(request))
//      case _ => Future.successful(Unauthorized)
//    }
//}
