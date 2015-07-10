import java.net.InetSocketAddress

import argonaut._, Argonaut._

import io.finch._
import io.finch.argonaut._
import io.finch.route._
import io.finch.response._

import com.twitter.finagle.Service
import com.twitter.finagle.Httpx

import com.twitter.util.Await

object WebServer extends App {

  val json: Router[HttpResponse] =
    Get / "json" /> Ok(Json("message" -> jString("Hello, World!")))

  val list: Router[HttpResponse] =
    Get / "list" /> Ok(List(1, 2, 3).asJson)

  val api: Service[HttpRequest, HttpResponse] = (list :+: json).toService

  Await.ready(
    Httpx.serve("localhost:9000", api)
  )
}
