name := """akka-http-example"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.11"

val akkaHttpVersion = "10.1.0-RC1"

libraryDependencies ++= Seq(
  "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
  "com.typesafe.akka" %% "akka-stream"          % "2.5.8"
)

resolvers += "Akka Snapshot Repository" at "http://repo.akka.io/snapshots/"
