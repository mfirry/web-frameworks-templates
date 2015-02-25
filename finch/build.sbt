name := """finch-seed"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.5"

mainClass in (Compile, run) := Some("WebServer")

crossScalaVersions := Seq("2.9.2", "2.11.5")

libraryDependencies ++= Seq(
	  "com.github.finagle" %% "finch-core" % "0.5.0",
    "com.github.finagle" %% "finch-json" % "0.5.0"
)
