name := """akka-http-example"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.8"

val akkaHttpVersion = "2.4.11"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http-experimental" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % akkaHttpVersion
)

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
