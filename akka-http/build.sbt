name := """akka-http-example"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.8"

val akkaHttpVersion = "10.0.10"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion
)

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
