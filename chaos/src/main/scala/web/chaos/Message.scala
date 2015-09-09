package web.chaos

import org.hibernate.validator.constraints.{ NotEmpty, Range }

class Message {
  @NotEmpty
  var message: String = ""

  override def toString = {
    s"Message($message)"
  }
}
