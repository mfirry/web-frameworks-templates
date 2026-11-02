package app

import io.eezo.http.{Response, api}
import io.eezo.json.upickle.given

/** `Index.scala` at the root of `app/` mounts `GET /`. */
object Index {

  def index(request: api.Request): Response =
    Response.Ok(List(1, 2, 3))
}
