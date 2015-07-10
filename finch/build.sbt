name := """finch-seed"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.6"

Revolver.settings

mainClass in (Compile, run) := Some("WebServer")

libraryDependencies ++= Seq(
    "com.github.finagle" %% "finch-core" % "0.8.0-SNAPSHOT" changing(),
    "com.github.finagle" %% "finch-argonaut" % "0.8.0-SNAPSHOT" changing()
)

resolvers += Resolver.sonatypeRepo("snapshots")
