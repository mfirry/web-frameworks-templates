package yolo

import ba.sake.tupson.JsonRW

import java.util.UUID

case class ProductRes(id: UUID, name: String, quantity: Int) derives JsonRW