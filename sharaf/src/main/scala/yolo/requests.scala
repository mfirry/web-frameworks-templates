package yolo

import ba.sake.querson.QueryStringRW
import ba.sake.tupson.JsonRW
import ba.sake.validson.*

case class CreateProductReq private (name: String, quantity: Int) derives JsonRW

object CreateProductReq:
  def of(name: String, quantity: Int): CreateProductReq =
    CreateProductReq(name, quantity).validateOrThrow

  given Validator[CreateProductReq] = Validator
    .derived[CreateProductReq]
    .notBlank(_.name)
    .nonNegative(_.quantity)

// query params
case class ProductsQuery(name: Set[String], minQuantity: Option[Int]) derives QueryStringRW
