import cats.effect.IO
import munit.CatsEffectSuite

class MessengerSpec extends CatsEffectSuite {

  val messenger = Messenger.impl[IO]

  test("message should return the same message") {
    val inputMessage = Messenger.Message("Test message")

    for {
      result <- messenger.message(inputMessage)
    } yield {
      assertEquals(result, inputMessage)
      assertEquals(result.message, "Test message")
    }
  }

  test("message should handle empty string") {
    val inputMessage = Messenger.Message("")

    for {
      result <- messenger.message(inputMessage)
    } yield {
      assertEquals(result.message, "")
    }
  }

  test("message should handle special characters") {
    val inputMessage = Messenger.Message("Hello, 世界! 🌍")

    for {
      result <- messenger.message(inputMessage)
    } yield {
      assertEquals(result.message, "Hello, 世界! 🌍")
    }
  }
}
