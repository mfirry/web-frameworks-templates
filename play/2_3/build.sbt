name := """play"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  ws,
  "com.google.protobuf" % "protobuf-java" % "2.6.1",
  "net.sandrogrzicic" %% "scalabuff-runtime" % "1.4.0"
)


