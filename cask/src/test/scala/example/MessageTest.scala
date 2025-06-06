package example

import utest._
import upickle.default._

object MessageTest extends TestSuite {
  
  val tests = Tests {
    
    test("Message serialization") {
      val message = Message("Hello World!")
      val json = write(message)
      
      json ==> """{"message":"Hello World!"}"""
    }

    test("Message deserialization") {
      val json = """{"message":"Hello World!"}"""
      val message = read[Message](json)
      
      message ==> Message("Hello World!")
    }

    test("Message round-trip serialization") {
      val original = Message("Test message with special chars: àáâãäå")
      val json = write(original)
      val deserialized = read[Message](json)
      
      deserialized ==> original
    }

    test("empty message handling") {
      val message = Message("")
      val json = write(message)
      val deserialized = read[Message](json)
      
      deserialized ==> message
      deserialized.message ==> ""
    }

    test("message with quotes and escaping") {
      val message = Message("""Hello "World" with 'quotes'""")
      val json = write(message)
      val deserialized = read[Message](json)
      
      deserialized ==> message
    }
  }
}