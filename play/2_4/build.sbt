name := """play"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala, PlayAkkaHttpServer)
  .disablePlugins(PlayLayoutPlugin)
  .disablePlugins(PlayNettyServer)

scalaVersion := "2.11.7"
