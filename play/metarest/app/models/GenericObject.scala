package models

import com.github.pathikrit.metarest.annotations.{ResourceWithPlayJson => Resource, get, put, post, patch}

@Resource case class GenericObject(
  @get  message: String
)