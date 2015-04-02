name := """finch-seed"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.5"

Revolver.settings

mainClass in (Compile, run) := Some("WebServer")

libraryDependencies ++= Seq(
	  "com.github.finagle" %% "finch-core" % "0.6.0",
    "com.github.finagle" %% "finch-json" % "0.6.0"
)

resolvers += Resolver.sonatypeRepo("snapshots")
