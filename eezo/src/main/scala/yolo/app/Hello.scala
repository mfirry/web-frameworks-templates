package app

import io.eezo.core.html.*
import io.eezo.http.Request
import io.eezo.http.Response

object Hello {

  def hello(request: Request): Response = {
    val thread = Thread.currentThread()

    Response.Ok()
  }
}
