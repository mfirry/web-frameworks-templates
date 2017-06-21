package com.example

import io.vertx.lang.scala.ScalaVerticle
import io.vertx.lang.scala.json.Json
import io.vertx.scala.ext.web.Router

import scala.concurrent.Future

final class Hello extends ScalaVerticle {

  override def startFuture(): Future[Unit] = {

    val router = Router.router(vertx)

    router.get("/")
      .handler(_.response().
        putHeader("content-type", "application/json; charset=UTF-8").
        end(Json.arr(1, 2, 3).toString))

    router.get("/string/:string")
      .handler(request => request.response().
        putHeader("content-type", "application/json; charset=UTF-8").
        end(Json.arr(request.pathParam("string").head).toString))

    router.get("/json")
      .handler(_.response().
        putHeader("content-type", "application/json; charset=UTF-8").
        end(Json.obj("message" -> "hello, world").toString))

    router.get("/plaintext")
      .handler(_.response().
        putHeader("content-type", "text/plain; charset=UTF-8").
        end("hello, world"))

    vertx.
      createHttpServer().
      requestHandler(router.accept _).
      listenFuture(8080, "0.0.0.0").
      map(_ => ())


  }

}
