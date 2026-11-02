package app

import io.eezo.http.{Response, api}
import io.eezo.json.upickle.given

case class Message(message: String)

/** `Json.scala` mounts `GET /json`. A handler taking an `api.Request` is an API route, and
  * `Response.Ok(value)` writes the value as JSON through the upickle adapter.
  */
object Json {

  def json(request: api.Request): Response =
    Response.Ok(Message("Hello, World!"))
}
