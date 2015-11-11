name := """akka-http-example"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http-core-experimental" % "2.0-M1",
  "com.typesafe.akka" %% "akka-stream-experimental" % "2.0-M1",
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "2.0-M1"
)

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"


