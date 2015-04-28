package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._


import play.api.libs.iteratee.Enumerator

object Application extends Controller {

  def index = Action {
    val list = List(1, 2, 3)
    Ok(Json.toJson(list))
  }

  def getString(string: String) = Action {
    Ok(Json.toJson(string))
  }

  //sends a protobuf thing over the wire
  def f(name: String = "marco") = Action {
    val p = com.example.tutorial.Person(name, 12, Some("mfirry@gmail.com"))
    Result(
      header = ResponseHeader(200, Map(CONTENT_TYPE -> "application/x-protobuf")),
      body = Enumerator(p.toByteArray)
    )
  }

  //consumes a protobuf endpoint
  def p(name: String) = Action.async {
    import play.api.Play.current
    import play.api.libs.ws._
    import play.api.libs.ws.ning.NingAsyncHttpClientConfigBuilder
    import scala.concurrent.Future

    implicit val context = play.api.libs.concurrent.Execution.Implicits.defaultContext

    WS.url(s"http://localhost:9000/f/$name").get().map {
      response =>
        println(response.allHeaders)
        println(response.body)
        val v = com.example.tutorial.Person.defaultInstance.mergeFrom(response.body.getBytes)
        println(v.getClass)
        Ok(v.name + " " + v.email + " " + v.id)
    }

  }

}