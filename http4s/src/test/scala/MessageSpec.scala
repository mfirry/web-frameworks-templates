import munit.FunSuite
import io.circe._, io.circe.syntax._
import io.circe.parser._

class MessageSpec extends FunSuite {

  test("Message should encode to correct JSON") {
    val message = Messenger.Message("Hello, World!")
    val expectedJson = parse("""{"message": "Hello, World!"}""").toOption.get

    assertEquals(message.asJson, expectedJson)
  }

  test("Message should encode empty string correctly") {
    val message = Messenger.Message("")
    val expectedJson = parse("""{"message": ""}""").toOption.get

    assertEquals(message.asJson, expectedJson)
  }

  test("Message should handle JSON escaping") {
    val message = Messenger.Message("""Special "quotes" and \backslashes""")
    val json = message.asJson

    // Verify it's valid JSON and contains the message
    assert(json.hcursor.downField("message").as[String].isRight)
  }
}
