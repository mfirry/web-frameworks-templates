package code.api

import code.model.User
import net.liftweb.http.PlainTextResponse
import net.liftweb.http.rest.{RestContinuation, RestHelper}
import net.liftweb.json.JsonAST._
import net.liftweb.json.JsonDSL._

/**
  * Created by Riccardo Sirigu on 20/04/17.
  */
object RestExample extends RestHelper{

  serve{
    case "hello" :: Nil JsonGet _ =>
      JString("Hello from Lift")

    case "list" :: Nil JsonGet _ =>
      JArray(List(1,2,3))

    case "echo" :: str :: Nil Get _ =>
      PlainTextResponse(str)

    case "users" :: Nil JsonGet _ =>
      User(412, "Riccardo"): JValue

    case "async" :: Nil JsonGet _ =>
      val mainThreadName = Thread.currentThread.getName
      RestContinuation.async{ reply =>
        val backgroundThreadName = Thread.currentThread.getName
        //Long running computation executing in another thread
        Thread.sleep(4000)
        reply {
          ("MainThread" -> mainThreadName) ~
          ("BackgroundThread" -> backgroundThreadName) ~
          ("Result" -> 42)
        }
      }
  }

}
