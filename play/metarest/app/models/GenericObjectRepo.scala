package models

trait GenericObjectRepo {
  def get(message: String): GenericObject.Get
}