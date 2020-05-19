package example

import upickle.default.{ReadWriter => RW, macroRW}

case class Message(message: String)

object Message {
  implicit val rw: RW[Message] = macroRW
}
