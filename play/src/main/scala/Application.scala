package controllers

import javax.inject.Inject

import play.api.mvc._
import play.api.libs.json._

class Application @Inject() (action: DefaultActionBuilder)
    extends ControllerHelpers {

  def index =
    action {
      val list = List(1, 2, 3)
      Ok(Json.toJson(list))
    }

  def getString(string: String) =
    action {
      Ok(Json.toJson(string))
    }

  def json =
    action {
      Ok(Json.obj("message" -> "Hello, World!"))
    }

  def plaintext =
    action {
      Ok("Hello, World!")
    }

}
