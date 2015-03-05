import org.http4s._
import org.http4s.server._
import org.http4s.dsl._
import _root_.argonaut._, Argonaut._
import org.http4s.argonaut._

import org.http4s.server.blaze.BlazeBuilder

object WebServer extends App {

  val service = HttpService {
    case GET -> Root =>
      Ok((List(1, 2, 3)).asJson)
    case GET -> Root / str =>
      Ok(str.asJson)
  }

  BlazeBuilder.bindHttp(8080)
    .mountService(service, "/")
    .run
    .awaitShutdown()
}