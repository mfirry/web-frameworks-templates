package app

import java.nio.charset.StandardCharsets.UTF_8

import io.eezo.http.{Body, Response, api}

object Plaintext {

  def plaintext(request: api.Request): Response =
    Response(
      200,
      Seq("Content-Type" -> "text/plain; charset=utf-8"),
      Body.Bytes("Hello, World!".getBytes(UTF_8))
    )
}
