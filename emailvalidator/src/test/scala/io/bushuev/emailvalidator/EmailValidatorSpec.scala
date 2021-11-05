package io.bushuev.emailvalidator

import cats.effect.IO
import cats.implicits._
import munit.CatsEffectSuite

class EmailValidatorSpec extends CatsEffectSuite {

  test("All Invalid emails should not be validated ") {
    (Emails.invalidEmails ::: Emails.invalidEmailListWiki)
      .traverse(EmailValidator.validate[IO](_, Strategy.WarningsAllowed))
      .map(_.forall(res => !res))
      .assertEquals(true)
  }
  test("All Valid emails should be validated") {
    (Emails.validEmails ::: Emails.validWiki)
      .traverse(EmailValidator.validate[IO](_, Strategy.WarningsAllowed))
      .map(_.forall(res => res))
      .assertEquals(true)
  }
  test("All Valid emails with warnings should not be validated when Warnings not Allowed ") {
    (Emails.withWarnings)
      .traverse(EmailValidator.validate[IO](_, Strategy.WarningsNotAllowed))
      .map(_.forall(res => !res))
      .assertEquals(true)
  }

}
