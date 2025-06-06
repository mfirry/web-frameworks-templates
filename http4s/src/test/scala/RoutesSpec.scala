import cats.effect.IO
import munit.CatsEffectSuite
import org.http4s._
import org.http4s.implicits._
import org.http4s.circe._
// import io.circe.literal._
import io.circe._, io.circe.syntax._
import munit.Assertions._

class RoutesSpec extends CatsEffectSuite {

  // Test fixture setup
  val messengerAlg = Messenger.impl[IO]
  val routes = Routes.myRoutes[IO](messengerAlg)

  test("GET / should return JSON array [1,2,3]") {

    val request = Request[IO](Method.GET, uri"/")

    for {
      response <- routes.orNotFound.run(request)
      body <- response.as[Json]
    } yield {
      assertEquals(response.status, Status.Ok)
      assertEquals(body, "[1,2,3]".asJson)
    }
  }

  test("GET /plaintext should return plain text response") {
    val request = Request[IO](Method.GET, uri"/plaintext")

    for {
      response <- routes.orNotFound.run(request)
      body <- response.as[String]
    } yield {
      assertEquals(response.status, Status.Ok)
      assertEquals(body, "Hello, World!")
    }
  }

  test("GET /json should return JSON message") {
    val request = Request[IO](Method.GET, uri"/json")

    for {
      response <- routes.orNotFound.run(request)
      body <- response.as[Json]
    } yield {
      assertEquals(response.status, Status.Ok)
      assertEquals(body, """{"message": "Hello, World!"}""".asJson)
    }
  }

  test("GET /nonexistent should return 404") {
    val request = Request[IO](Method.GET, uri"/nonexistent")

    for {
      response <- routes.orNotFound.run(request)
    } yield {
      assertEquals(response.status, Status.NotFound)
    }
  }

  test("POST / should return 405 Method Not Allowed") {
    val request = Request[IO](Method.POST, uri"/")

    for {
      response <- routes.orNotFound.run(request)
    } yield {
      assertEquals(response.status, Status.MethodNotAllowed)
    }
  }
}
