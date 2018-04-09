name := """finch-seed"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.11"

Revolver.settings

mainClass in (Compile, run) := Some("WebServer")

libraryDependencies ++= Seq(
  "com.github.finagle" %% "finch-core" % "0.16.0-M5",
  "com.github.finagle" %% "finch-circe" % "0.16.0-M5",
  "io.circe" %% "circe-core" % "0.9.0-M2",
  "io.circe" %% "circe-generic" % "0.9.0-M2",
  "io.circe" %% "circe-jawn" % "0.9.0-M2"
)

resolvers += Resolver.sonatypeRepo("snapshots")
