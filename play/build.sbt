name := """play"""

version := "1.0-SNAPSHOT"

scalacOptions ++= List("-Ybackend:GenBCode", "-Ydelambdafy:method", "-target:jvm-1.8")

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  .disablePlugins(PlayLayoutPlugin)

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  specs2 % Test,
  guice,
  "com.typesafe.play" %% "play-json" % "2.6.3"
)
