package com.example.app

import org.json4s.{DefaultFormats, Formats}
import org.scalatra._
import org.scalatra.json._

class MyScalatraServlet extends ScalatraServlet with JacksonJsonSupport {
  protected implicit lazy val jsonFormats: org.json4s.Formats = org.json4s.DefaultFormats

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
