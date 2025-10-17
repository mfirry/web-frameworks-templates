name := """web-frameworks-templates"""

version := "0.1.1"

val scalaV = "3.3.7"

val akkaHttpVersion = "10.7.1"
val http4sVersion = "0.23.30"
val unfilteredVersion = "0.10.4"
val ScalatraVersion = "3.1.2"

lazy val `akka-http` = (project in file("akka-http")).settings(
  scalaVersion := scalaV,
  scalacOptions ++= Seq("-deprecation", "-feature"),
  resolvers += "Akka library repository".at("https://repo.akka.io/maven"),
  libraryDependencies ++= Seq(
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-stream" % "2.9.3",
    "com.typesafe.akka" %% "akka-actor-typed" % "2.9.3"
  )
)

lazy val cask = (project in file("cask")).settings(
  scalaVersion := scalaV,
  libraryDependencies ++= Seq(
    "com.lihaoyi" %% "cask" % "0.10.2"
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
    "org.json4s" %% "json4s-jackson" % "4.1.0-M8",
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
  libraryDependencies += "dev.zio" %% "zio-http" % "3.3.3"
)

lazy val root = (project
  .in(file("."))
  .aggregate(
    `zio-http`,
    `akka-http`,
    unfiltered,
    http4s,
    play,
    scalatra,
    cask
  ))

enablePlugins(ScalatraPlugin)
