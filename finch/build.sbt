name := """finch-seed"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.8"

Revolver.settings

mainClass in (Compile, run) := Some("WebServer")

libraryDependencies ++= Seq(
  "com.github.finagle" %% "finch-core" % "0.15.0",
  "com.github.finagle" %% "finch-circe" % "0.15.0",
  "io.circe" %% "circe-core" % "0.8.0",
  "io.circe" %% "circe-generic" % "0.8.0",
  "io.circe" %% "circe-jawn" % "0.8.0"
)

resolvers += Resolver.sonatypeRepo("snapshots")
