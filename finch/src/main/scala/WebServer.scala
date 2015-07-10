import java.net.InetSocketAddress

import argonaut._, Argonaut._

import io.finch._
import io.finch.route._
import io.finch.response._

import com.twitter.finagle.Service
import com.twitter.finagle.Httpx

import com.twitter.util.Await

import java.util.Calendar

object WebServer extends App {
  val ok: ResponseBuilder = Ok.withHeaders("Server" -> "finch", "Date" -> Calendar.getInstance().getTime().toString)

  val json: Router[HttpResponse] = {
    import io.finch.argonaut._
    Get / "json" /> ok(Json("message" -> jString("Hello, World!")))
  }

  val list: Router[HttpResponse] = {
    import io.finch.argonaut._
    Get / "list" /> ok(List(1, 2, 3).asJson)
  }

  val plaintext: Router[HttpResponse] =
    Get / "plaintext" /> ok("Hello, World!")

  val api: Service[HttpRequest, HttpResponse] = (list :+: json :+: plaintext).toService

  Await.ready(
    Httpx.serve("localhost:9000", api)
  )
}
