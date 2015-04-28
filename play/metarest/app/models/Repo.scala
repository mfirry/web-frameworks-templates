package models

object Repo extends GenericObjectRepo {
  def get(msg: String) = GenericObject.Get(msg)
}