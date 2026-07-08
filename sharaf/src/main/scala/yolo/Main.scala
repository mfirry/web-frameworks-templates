package yolo

import java.nio.file.Files
import java.util.UUID
import ba.sake.sharaf.*
import ba.sake.sharaf.undertow.*
import ba.sake.tupson.toJson

@main def main: Unit =
  val module = JsonApiModule(8181)
  module.server.start()
  println(s"Started HTTP server at ${module.baseUrl}")

class JsonApiModule(port: Int) {

  val baseUrl = s"http://localhost:${port}"

  // don't do this at home!
  private var db = Seq.empty[ProductRes]

  private val routes = Routes:
    case GET -> Path("products", param[UUID](id)) =>
      val productOpt = db.find(_.id == id)
      Response.withBodyOpt(productOpt, s"Product with id=$id")

    case GET -> Path("products") =>
      val query = Request.current.queryParamsValidated[ProductsQuery]
      val products =
        if query.name.isEmpty then db
        else db.filter(c => query.name.contains(c.name) && query.minQuantity.map(c.quantity >= _).getOrElse(true))
      Response.withBody(products.toList)

    case POST -> Path("products") =>
      val req = Request.current.bodyJsonValidated[CreateProductReq]
      val res = ProductRes(UUID.randomUUID(), req.name, req.quantity)
      db = db.appended(res)
      Response.withBody(res)

    case GET -> Path("products.json") =>
      val tmpFile = Files.createTempFile("product", ".json")
      tmpFile.toFile.deleteOnExit()
      Files.writeString(tmpFile, db.toJson)
      Response.withBody(tmpFile)

  val server = UndertowSharafServer("localhost", port, routes, exceptionMapper = ExceptionMapper.json)
}
