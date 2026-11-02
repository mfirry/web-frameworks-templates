package app

import io.eezo.http.{Response, api}
import io.eezo.json.upickle.given

case class Message(message: String)

object Json {

  def json(request: api.Request): Response =
    Response.Ok(Message("Hello, World!"))
}
