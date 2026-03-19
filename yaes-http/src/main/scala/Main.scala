package example

import in.rcard.yaes.*
import in.rcard.yaes.Log.given
import in.rcard.yaes.http.server.*
import in.rcard.yaes.http.circe.given
import io.circe.generic.auto.*
import scala.concurrent.duration.*
import scala.concurrent.ExecutionContext.Implicits.global

case class Message(message: String)

@main def main(): Unit =
  Sync.runBlocking(Duration.Inf) {
    Shutdown.run {
      Log.run() {
        val server = YaesServer.route(
          GET(p"/") { req =>
            Response.ok(List(1, 2, 3))
          },
          GET(p"/json") { req =>
            Response.ok(Message("Hello, World!"))
          },
          GET(p"/plaintext") { req =>
            Response.ok("Hello, World!")
          }
        )
        server.run(port = 8080)
      }
    }
  }.get
