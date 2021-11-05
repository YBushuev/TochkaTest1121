package io.bushuev.emailvalidator

import cats.effect.Sync
import emailvalidator4j.{EmailValidator => EmailValidator4J}

import scala.util.control.NoStackTrace

object EmailValidator {

  def validate[F[_]: Sync](email: String, strategy: Strategy = Strategy.WarningsAllowed): F[Boolean] = {

    val validator = new EmailValidator4J()
    val isValid   = validator.isValid(email)

    strategy match {
      case Strategy.WarningsNotAllowed => Sync[F].delay(!validator.hasWarnings && isValid)
      case Strategy.WarningsAllowed    => Sync[F].delay(isValid)
    }

  }

}
sealed trait Strategy extends Product with Serializable

object Strategy {

  case object WarningsNotAllowed extends Strategy

  case object WarningsAllowed extends Strategy

}

final case class EmailValidatorError(warnings: Seq[emailvalidator4j.parser.Warnings])
    extends Throwable
    with NoStackTrace {

  override def getMessage: String = warnings.map(_.getMessage).mkString(";")

}
