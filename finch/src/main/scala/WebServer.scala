import java.net.InetSocketAddress

import com.twitter.io.Buf
import com.twitter.finagle.Service
import com.twitter.finagle.Http
import com.twitter.finagle.http.{Response, Request}

import com.twitter.util.Await

import io.finch._
import io.finch.syntax._

import io.circe._
import io.circe.generic.auto._
import io.circe.syntax._


object WebServer extends App {

  case class Message(message: String)

  import io.finch.circe._

  val list: Endpoint[Json] = get("/") {
    Ok(List(1, 2, 3).asJson)
  }

  val hello: Endpoint[Buf] = get("/" :: param("string")) { string: String =>
    Ok(Buf.Utf8("${string}"))
  }

  val json: Endpoint[Json] = get("json") {
    Ok(Message("Hello, World!").asJson)
  }

  val plaintext: Endpoint[Buf] = get("plaintext") {
    Ok(Buf.Utf8("Hello, World!"))
  }

  val api: Service[Request, Response] =
    Bootstrap.configure(includeDateHeader = true, includeServerHeader = true)
      .serve[Application.Json](list)
      .serve[Text.Plain](hello)
      .serve[Application.Json](json)
      .serve[Text.Plain](plaintext)
      .toService

  Await.ready(
      Http.serve("localhost:9000", api)
  )
}
