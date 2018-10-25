import java.net.InetSocketAddress

import com.twitter.io.Buf
import com.twitter.finagle.Service
import com.twitter.finagle.Http
import com.twitter.finagle.http.{Response, Request}

import com.twitter.util.Await

import io.finch._

import io.circe._
import io.circe.generic.auto._
import io.circe.syntax._

import cats.effect.IO

object WebServer extends App with Endpoint.Module[IO] {

  case class Message(message: String)

  import io.finch.circe._

  val list = get(zero) {
    Ok(List(1, 2, 3).asJson)
  }

  val json: Endpoint[IO, Json] = get("json") {
    Ok(Message("Hello, World!").asJson)
  }

  val plaintext: Endpoint[IO, Buf] = get("plaintext") {
    Ok(Buf.Utf8("Hello, World!"))
  }

  val sayHi: Endpoint[IO, Buf] = get("say-hi" :: param[String]("whom")) { whom: String =>
    Ok(Buf.Utf8(s"Hello $whom"))
  }

  val api: Service[Request, Response] =
    Bootstrap.configure(includeDateHeader = true, includeServerHeader = true)
      .serve[Application.Json](json)
      .serve[Text.Plain](plaintext)
      .serve[Application.Json](list)
      .serve[Text.Plain](sayHi)
      .toService

  Await.ready(
      Http.serve("localhost:9000", api)
  )
}
