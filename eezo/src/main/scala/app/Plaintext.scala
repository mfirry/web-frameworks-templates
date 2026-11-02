package app

import java.nio.charset.StandardCharsets.UTF_8

import io.eezo.http.{Body, Response, api}

/** `Plaintext.scala` mounts `GET /plaintext`. The handler takes an `api.Request`, so no browser
  * session travels with the answer, and the response names its own content type.
  */
object Plaintext {

  def plaintext(request: api.Request): Response =
    Response(
      200,
      Seq("Content-Type" -> "text/plain; charset=utf-8"),
      Body.Bytes("Hello, World!".getBytes(UTF_8))
    )
}
