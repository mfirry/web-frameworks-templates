organization := "example"

name := """unfiltered"""

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.10.4"

crossScalaVersions := Seq("2.10.4", "2.11.4")

libraryDependencies ++= Seq(
  //"org.json4s" %% "json4s-native" % "3.2.9",
  "net.databinder" %% "unfiltered-filter" % "0.8.3",
  "net.databinder" %% "unfiltered-jetty" % "0.8.3",
  "net.databinder" %% "unfiltered-json4s" % "0.8.3",
  "net.databinder" %% "unfiltered-specs2" % "0.8.3" % "test"
)

initialCommands := "import example._; import unfiltered.request._; import unfiltered.response._"
