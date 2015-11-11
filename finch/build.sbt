name := """finch-seed"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.7"

Revolver.settings

mainClass in (Compile, run) := Some("WebServer")

libraryDependencies ++= Seq(
    "com.github.finagle" %% "finch-core" % "0.9.0",
    "com.github.finagle" %% "finch-circe" % "0.9.0",
    "io.circe" %% "circe-core" % "0.2.0",
    "io.circe" %% "circe-generic" % "0.2.0",
    "io.circe" %% "circe-jawn" % "0.2.0"
)

resolvers += Resolver.sonatypeRepo("snapshots")
