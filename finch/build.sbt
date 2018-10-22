name := """finch-seed"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.12.6"

// Revolver.settings

mainClass in (Compile, run) := Some("WebServer")

libraryDependencies ++= Seq(
  "com.github.finagle" %% "finchx-core" % "0.25.0",
  "com.github.finagle" %% "finchx-circe" % "0.25.0",
  "io.circe" %% "circe-core" % "0.10.0",
  "io.circe" %% "circe-generic" % "0.10.0",
  "io.circe" %% "circe-jawn" % "0.10.0"
)

// resolvers += Resolver.sonatypeRepo("snapshots")
