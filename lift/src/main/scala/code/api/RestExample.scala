package code.api

import net.liftweb.http.PlainTextResponse
import net.liftweb.http.rest.{RestContinuation, RestHelper}
import net.liftweb.json.JsonAST._
import net.liftweb.json.JsonDSL._

/**
  * Created by Riccardo Sirigu on 20/04/17.
  */
object RestExample extends RestHelper{

  serve{
    case Nil Get _ =>
      JArray(List(1,2,3))

    case str :: Nil Get _ =>
      PlainTextResponse(str)
  }

}
