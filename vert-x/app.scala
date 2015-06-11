val server = vertx.createHttpServer
val routeMatcher = RouteMatcher()

routeMatcher.get("/", { req: HttpServerRequest =>
  req.response.headers.addBinding("Content-Type", "application/json; charset=UTF-8")
  req.response.end(Json.arr(1, 2, 3).toString)
})

routeMatcher.get("/string/:string", { req: HttpServerRequest =>
  req.response.headers.addBinding("Content-Type", "application/json; charset=UTF-8")
  req.response.end(Json.arr(req.params()("string").head).toString)
})

routeMatcher.get("/json", { req: HttpServerRequest =>
  req.response.headers.addBinding("Content-Type", "application/json; charset=UTF-8")
  req.response.end(Json.obj("message" -> "hello, world").toString)
})

routeMatcher.get("/plaintext", { req: HttpServerRequest =>
  req.response.headers.addBinding("Content-Type", "text/plain; charset=UTF-8")
  req.response.end("hello, world")
})

server.requestHandler(routeMatcher).listen(8080, "localhost")
