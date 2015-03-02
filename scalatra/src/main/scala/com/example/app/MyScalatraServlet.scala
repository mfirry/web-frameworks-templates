package com.example.app

import org.scalatra._
import org.json4s.{DefaultFormats, Formats}
import org.scalatra.json._

class MyScalatraServlet extends ScalatraServlet with JacksonJsonSupport {
  protected implicit val jsonFormats: Formats = DefaultFormats

  before() {
    contentType = formats("json")
  }

  get("/") {
    List(1, 2, 3)
  }

  get("/:string") {
    params("string")
  }

}
