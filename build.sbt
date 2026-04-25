name := """web-frameworks-templates"""

version := "0.1.1"

val scalaV = "3.3.7"

val akkaHttpVersion = "10.7.1"
val http4sVersion = "0.23.34"
val unfilteredVersion = "0.13.0-M4"
val ScalatraVersion = "3.1.2"

scalaVersion := scalaV

lazy val cask = (project in file("cask")).settings(
  scalaVersion := scalaV,
  libraryDependencies ++= Seq(
    "com.lihaoyi" %% "cask" % "0.11.3"
  )
)

lazy val http4s = (project in file("http4s")).settings(
  scalaVersion := scalaV,
  scalafmtOnCompile := true,
  scalacOptions ++= Seq(
    "-deprecation",
    "-language:higherKinds",
    "-language:postfixOps",
    "-feature"
  ),
  libraryDependencies ++= Seq(
    "org.http4s" %% "http4s-ember-server" % http4sVersion,
    "org.http4s" %% "http4s-circe" % http4sVersion,
    "org.http4s" %% "http4s-dsl" % http4sVersion
  )
)

lazy val play = (project in file("play"))
  .settings(
    scalaVersion := scalaV,
    scalacOptions += s"-Wconf:src=${target.value}/.*:s",
    libraryDependencies ++= Seq(
      guice
    )
  )
  .enablePlugins(PlayScala)
  .disablePlugins(PlayLayoutPlugin)

lazy val scalatra = (project in file("scalatra")).settings(
  scalaVersion := scalaV,
  libraryDependencies ++= Seq(
    "org.scalatra" %% "scalatra-javax" % ScalatraVersion,
    "org.scalatra" %% "scalatra-json-javax" % ScalatraVersion,
    "io.github.json4s" %% "json4s-jackson" % "4.1.0",
    "ch.qos.logback" % "logback-classic" % "1.2.11" % "runtime",
    "org.eclipse.jetty" % "jetty-webapp" % "11.0.15" % "container;compile",
    "javax.servlet" % "javax.servlet-api" % "4.0.1" % "provided"
  )
)

lazy val unfiltered = (project in file("unfiltered")).settings(
  scalaVersion := scalaV,
  libraryDependencies ++= Seq(
    "ws.unfiltered" %% "unfiltered-filter" % unfilteredVersion,
    "ws.unfiltered" %% "unfiltered-netty-server" % unfilteredVersion,
    "ws.unfiltered" %% "unfiltered-json4s" % unfilteredVersion
  )
)

lazy val `zio-http` = (project in file("zio-http")).settings(
  scalaVersion := scalaV,
  libraryDependencies += "dev.zio" %% "zio-http" % "3.11.0"
)

lazy val root = (project
  .in(file("."))
  .aggregate(
    `zio-http`,
    unfiltered,
    http4s,
    play,
    scalatra,
    cask
  ))

enablePlugins(ScalatraPlugin)
