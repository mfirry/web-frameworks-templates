package code
package snippet

import net.liftweb.util.Helpers._
import java.util.Date

class HelloWorld {
  lazy val date = new Date()

  // replace the contents of the element with id "time" with the date
  def howdy = "#time *" #> date.toString

}