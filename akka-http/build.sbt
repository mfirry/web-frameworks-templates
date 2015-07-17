name := """akka-http-example"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http-core-experimental" % "1.0",
  "com.typesafe.akka" %% "akka-stream-experimental" % "1.0",
  "com.typesafe.akka" % "akka-http-spray-json-experimental_2.11" % "1.0"
)

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"


