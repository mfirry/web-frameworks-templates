package yolo.models

import io.eezo.db.Schema

object AppSchema extends Schema {
  val names = table[Name].index(_.id).unique(_.name)
}
