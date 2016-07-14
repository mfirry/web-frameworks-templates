package com.example

import io.peregrine._

object WebApp extends PeregrineApp {
  get("/plaintext") { req =>
    "Hello, World!"
  }
  get("/json") { req =>
    json(Map("message" -> "Hello, World!"))
  }
  get("/list") { req =>
    json(List(1, 2, 3))
  }
}
