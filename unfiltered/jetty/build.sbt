organization := "unfiltered"

name := """unfiltered-jetty"""

version := "0.1.0-SNAPSHOT"

scalaVersion := "2.11.9"

libraryDependencies ++= Seq(
  "net.databinder" %% "unfiltered-filter" % "0.8.4",
  "net.databinder" %% "unfiltered-jetty"  % "0.8.4",
  "net.databinder" %% "unfiltered-json4s" % "0.8.4",
  "net.databinder" %% "unfiltered-specs2" % "0.8.4" % "test"
)

initialCommands := "import example._; import unfiltered.request._; import unfiltered.response._"
