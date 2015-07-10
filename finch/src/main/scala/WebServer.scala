import java.net.InetSocketAddress

import argonaut._, Argonaut._

import io.finch._
import io.finch.{ Endpoint => _, _ }
import io.finch.argonaut._

import io.finch.route._
import io.finch.route.Endpoint
import io.finch.response._

import com.twitter.finagle.Service
import com.twitter.finagle.Httpx

import com.twitter.util.Await

object WebServer extends App {

  val json: Endpoint[HttpRequest, HttpResponse] =
    Get / "json" /> Ok(Json("message" -> jString("Hello, World!"))).toFuture

  val list: Endpoint[HttpRequest, HttpResponse] =
    Get / "list" /> Ok(Json.array(jNumber(1), jNumber(2), jNumber(3))).toFuture

  val api: Service[HttpRequest, HttpResponse] = list | json

  Await.ready(
    Httpx.serve(":9000", api)
  )
}