package app

import io.eezo.db.*
import io.eezo.http.Request
import io.eezo.http.Response
import io.eezo.http.{Guarded, Response, api}
import io.eezo.db.Scopes.read
import io.eezo.json.upickle.given

case class Count(names: Int)

object Hello {

  def count(request: api.Request): Response = {
    val thread = Thread.currentThread()

    Response.Ok(Count(read(Table[yolo.models.Name].all()).size))
  }
}
