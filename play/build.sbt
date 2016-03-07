name := """play"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .disablePlugins(PlayLayoutPlugin)

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  specs2 % Test
)
