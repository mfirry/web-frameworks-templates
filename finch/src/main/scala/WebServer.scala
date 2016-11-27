import java.net.InetSocketAddress

import io.finch._
import com.twitter.finagle.Http
import com.twitter.finagle.http.{Response, Request}

import com.twitter.util.Await

import io.circe._
import io.circe.generic.auto._

import io.circe.syntax._


object WebServer extends App {

  case class Message(message: String)

  import io.finch.circe._

  val json: Endpoint[Message] = get("json") {
    Ok(Message("Hello, World!"))
  }

  val plaintext = get("plaintext") {
    Ok("Hello, World!")
  }

  val hello = get(/ :: string) { string: String =>
    Ok(s"Hello, ${string}!")
  }

  val list: Endpoint[List[Int]] = get("list") {
    Ok(List(1, 2, 3))
  }

  val api = (json :+: plaintext :+: list :+: hello)

  Await.ready(
      Http.serve("localhost:9000", api.toService)
  )
}
