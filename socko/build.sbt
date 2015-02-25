name := """socko-example"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.5"

mainClass in (Compile, run) := Some("WebServer") 

crossScalaVersions := Seq("2.9.2", "2.11.5")

libraryDependencies ++= Seq(
	  "org.mashupbots.socko" %% "socko-webserver" % "0.6.0",
	  "org.json4s" %% "json4s-native" % "3.2.10"
)
