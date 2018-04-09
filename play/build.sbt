name := """play"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayScala)
  disablePlugins(PlayLayoutPlugin)

PlayKeys.playMonitoredFiles ++= (sourceDirectories in (Compile, TwirlKeys.compileTemplates)).value

scalaVersion := "2.12.2"

libraryDependencies ++= Seq(
  specs2 % Test,
  guice,
  "com.typesafe.play" %% "play-json" % "2.6.3"
)

resolvers += Resolver.sonatypeRepo("snapshots")
