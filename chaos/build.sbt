name := """chaos-seed"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.5"

resolvers += "Mesosphere Public Repo" at "http://downloads.mesosphere.io/maven"

libraryDependencies ++= Seq(
  "mesosphere" % "chaos" % "0.5.2",
  "com.sun.jersey" % "jersey-bundle" % "1.17.1"
)
