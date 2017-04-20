package code.model

import net.liftweb.json.{DefaultFormats, Extraction}
import net.liftweb.json.JsonAST.JValue

/**
  * Created by Riccardo Sirigu on 20/04/17.
  */
case class User(id: Long, name: String)

object User {
  private implicit val formats = DefaultFormats
  implicit def toJson(user: User): JValue = {
    Extraction.decompose(user)
  }
}

