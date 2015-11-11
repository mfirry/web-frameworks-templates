import java.net.InetSocketAddress

import io.finch._
import com.twitter.finagle.Http
import com.twitter.finagle.http.{Response, Request}

import com.twitter.util.Await

import io.circe._
import io.circe.generic.auto._

import io.circe.syntax._
import io.finch.response.EncodeResponse

import com.twitter.io.Buf

object WebServer extends App {

  import io.finch.circe._
  implicit val encodeMap: EncodeResponse[Map[String, String]] =
    EncodeResponse("text/plain")(map =>
      Buf.Utf8(map.toSeq.map(kv => "\"" + kv._1 + "\":\"" + kv._2 + "\"").mkString(", "))
    )

  val json = get("json") {
    case class Message(message: String)
    Ok(Message("Hello, World!").asJson)
  }

  val plaintext = get("plaintext") {
    Ok("Hello, World!").withContentType(Some("text/plain"))
  }

  val list = get("list") {
    Ok(List(1, 2, 3).asJson)
  }

  val api = (json :+: plaintext :+: list)

  Await.ready(
    Http.serve("localhost:9000", api.toService)
  )
}
