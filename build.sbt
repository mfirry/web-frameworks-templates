name := """web-frameworks-templates"""

version := "0.1.1"

val scalaV = "3.9.0"

val akkaHttpVersion = "10.7.1"
val http4sVersion = "0.23.38"
val unfilteredVersion = "0.13.0-M6"
val ScalatraVersion = "3.2.1"
val eezoVersion = "0.4.2"

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
  libraryDependencies += "dev.zio" %% "zio-http" % "3.11.6"
)

// eezo targets JDK 25: its jars are compiled with `-release 25`, so the build needs a JDK 25 or
// newer. The plugin generates the route table from the files under `src/main/scala/app`.
lazy val eezo = (project in file("eezo"))
  .enablePlugins(EezoPlugin)
  .settings(
    scalaVersion := scalaV,
    scalacOptions ++= Seq("-release", "25", "-deprecation", "-feature", "-unchecked"),
    run / fork := true,
    libraryDependencies ++= Seq(
      "io.eezo" %% "eezo-http" % eezoVersion,
      "io.eezo" %% "eezo-upickle" % eezoVersion
    )
  )

lazy val root = (project
  .in(file("."))
  .aggregate(
    `zio-http`,
    eezo,
    unfiltered,
    http4s,
    play,
    scalatra,
    cask
  ))

enablePlugins(ScalatraPlugin)
