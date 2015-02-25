name := """akka-http"""

version := "1.0"

scalaVersion := "2.11.1"

libraryDependencies ++= Seq(
  "org.scalatest" %% "scalatest" % "2.1.6" % "test",
  "com.typesafe.akka" %% "akka-stream-experimental" % "1.0-M3",
  "com.typesafe.akka" %% "akka-http-experimental" % "1.0-M3",
  "com.typesafe.akka" %% "akka-http-core-experimental" % "1.0-M3",
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "1.0-M3",
  "io.spray" %%  "spray-json" % "1.3.1",
  "com.typesafe.akka" %% "akka-actor" % "2.3.9",
  "com.typesafe" % "config" % "1.2.1",
  "net.ceedubs" %% "ficus" % "1.1.2"
)