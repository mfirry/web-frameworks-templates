name := """playi-metarest"""

version := "1.0-SNAPSHOT"

scalaVersion := "2.11.1"

lazy val root = (project in file(".")).enablePlugins(PlayScala)

resolvers += Resolver.bintrayRepo("pathikrit", "maven")

libraryDependencies += "com.github.pathikrit" %% "metarest" % "1.0.0"

libraryDependencies ++= Seq(
  "com.kifi" %% "json-annotation" % "0.2",
  "com.typesafe.play" %% "play-json" % "2.3.8" // No need to add this if you are already using Play 2.1+
)

addCompilerPlugin("org.scalamacros" % "paradise" % "2.1.0-M5" cross CrossVersion.full)
