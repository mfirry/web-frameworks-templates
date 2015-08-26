import java.net.InetSocketAddress

import io.finch._
import io.finch.route._
import io.finch.response._

import com.twitter.finagle.Service
import com.twitter.finagle.Httpx
import com.twitter.finagle.httpx.{Response, Request}

import com.twitter.util.Await

import java.util.Calendar

object WebServer extends App {
  val ok: ResponseBuilder = Ok.withHeaders("Server" -> "finch", "Date" -> Calendar.getInstance().getTime().toString)

  val json = get("json") {
    import io.circe._
    import io.circe.generic.auto._
    import io.circe.jawn._
    import io.circe.syntax._
    import io.finch.response.EncodeResponse
    import io.circe.{Decoder, Encoder, Json}
    import io.finch.circe._

    case class Message(message: String)
    Ok(Message("Hello, World!").asJson)
  }

  val plaintext = get("plaintext") { "Hello, World!" }

  val list = get("list") {
    import io.circe._
    import io.circe.generic.auto._
    import io.circe.jawn._
    import io.circe.syntax._
    import io.finch.response.EncodeResponse
    import io.circe.{Decoder, Encoder, Json}
    import io.finch.circe._
    Ok( List(1, 2, 3).asJson )
  }

  val api: Service[Request, Response] = (plaintext :+: list :+: json).toService

  Await.ready(
    Httpx.serve("localhost:9000", api)
  )
}
