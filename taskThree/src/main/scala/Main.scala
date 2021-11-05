import cats.effect.{ExitCode, IO, IOApp}
import io.bushuev.emailvalidator.EmailValidator

object Main extends IOApp {

  override def run(args: List[String]): IO[ExitCode] =
    for {
      _   <- IO.consoleForIO.println("Enter email:")
      str <- IO.consoleForIO.readLine
      res <- EmailValidator.validate[IO](str)
      _   <- IO.consoleForIO.println(if (res) s"[$str] is valid." else s"[$str] is invalid")
    } yield ExitCode.Success

}
