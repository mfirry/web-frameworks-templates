package controllers

import play.api._
import play.api.mvc._
import play.api.libs.json._

object Application extends Controller {

  def index = Action {
    val list = List(1, 2, 3)
    Ok(Json.toJson(list))
  }

  def getString(string: String) = Action {
    Ok(Json.toJson(string))
  }

}