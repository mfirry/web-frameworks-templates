package example

import utest._
import requests.Response
import upickle.default._
import io.undertow.Undertow

object MinimalRoutesTest extends TestSuite {
  def withServer[T](example: cask.main.Main)(f: String => T): T = {
    val server = Undertow.builder
      .addHttpListener(8081, "localhost")
      .setHandler(example.defaultHandler)
      .build
    server.start()
    val res =
      try f("http://localhost:8081")
      finally server.stop()
    res
  }

  val tests = Tests {
    
    test("GET / should return JSON array [1,2,3]") - withServer(MinimalRoutes) { host =>
      val response: Response = requests.get(s"${host}/")
      
      response.statusCode ==> 200
      response.headers("content-type") ==> Seq("application/json")
      
      val parsed = read[List[Int]](response.text())
      parsed ==> List(1, 2, 3)
    }
  
    test("GET /plaintext should return plain text")  - withServer(MinimalRoutes) { host =>
      val response: Response = requests.get(s"${host}/plaintext")
      
      response.statusCode ==> 200
      response.text() ==> "Hello World!"
    }
  }

  //   test("GET /json should return JSON message") {
  //     val response: Response = requests.get(s"${server.host}/json")
      
  //     response.statusCode ==> 200
  //     response.headers("content-type") ==> Seq("application/json")
      
  //     val parsed = read[Message](response.text())
  //     parsed ==> Message("Hello World!")
  //   }

  //   test("non-existent route should return 404") {
  //     val response: Response = requests.get(
  //       s"${server.host}/nonexistent",
  //       check = false  // Don't throw on 4xx/5xx status codes
  //     )
      
  //     response.statusCode ==> 404
  //   }

  //   test("wrong HTTP method should return 405") {
  //     val response: Response = requests.post(
  //       s"${server.host}/",
  //       check = false
  //     )
      
  //     response.statusCode ==> 405
  //   }

  //   test("response headers are set correctly") {
  //     val response: Response = requests.get(s"${server.host}/json")
      
  //     assert(response.headers.contains("content-type"))
  //     response.headers("content-type").head.contains("application/json") ==> true
  //   }
  // }
}