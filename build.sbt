ThisBuild / version := "0.1.1"
ThisBuild / scalaVersion := "2.13.8"
ThisBuild / githubWorkflowPublishTargetBranches := Nil

val akkaHttpVersion = "10.4.0"
val http4sVersion = "0.23.13"
val unfilteredVersion = "0.10.4"
val scalatraVersion = "2.8.2"
val finchVersion = "0.34.1"

lazy val `akka-http` = (project in file("akka-http")).settings(
  libraryDependencies ++= List(
    "com.typesafe.akka" %% "akka-http" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
    "com.typesafe.akka" %% "akka-stream" % "2.7.0"
  )
)

lazy val cask = (project in file("cask")).settings(
  libraryDependencies ++= List(
    "com.lihaoyi" %% "cask" % "0.8.3",
    "com.lihaoyi" %% "upickle" % "2.0.0"
  )
)

lazy val finatra = (project in file("finatra")).settings(
  libraryDependencies += "com.twitter" %% "finatra-http-server" % "22.7.0"
)

lazy val finch = (project in file("finch")).settings(
  libraryDependencies ++= List(
    "com.github.finagle" %% "finch-core" % finchVersion,
    "com.github.finagle" %% "finch-circe" % finchVersion,
    "io.circe" %% "circe-core" % "0.14.1",
    "io.circe" %% "circe-generic" % "0.14.1",
    "io.circe" %% "circe-jawn" % "0.14.1"
  )
)

lazy val http4s = (project in file("http4s")).settings(
  scalafmtOnCompile := true,
  libraryDependencies ++= List(
    "org.http4s" %% "http4s-blaze-server" % http4sVersion,
    "org.http4s" %% "http4s-circe" % http4sVersion,
    "org.http4s" %% "http4s-dsl" % http4sVersion
  )
)

lazy val play = (project in file("play"))
  .settings(
    scalacOptions += s"-Wconf:src=${target.value}/.*:s",
    libraryDependencies ++= List(
      guice,
      "com.typesafe.play" %% "play-json" % "2.9.3"
    )
  )
  .enablePlugins(PlayScala)
  .disablePlugins(PlayLayoutPlugin)

lazy val scalatra = (project in file("scalatra"))
  .settings(
    libraryDependencies ++= List(
      "org.scalatra" %% "scalatra" % scalatraVersion,
      "org.scalatra" %% "scalatra-json" % scalatraVersion,
      "org.json4s" %% "json4s-jackson" % "4.0.1",
      "ch.qos.logback" % "logback-classic" % "1.2.11" % "runtime",
      "org.eclipse.jetty" % "jetty-webapp" % "9.4.48.v20220622" % "container;compile",
      "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided"
    )
  )
  .enablePlugins(ScalatraPlugin)

lazy val unfiltered = (project in file("unfiltered")).settings(
  libraryDependencies ++= List(
    "ws.unfiltered" %% "unfiltered-filter" % unfilteredVersion,
    "ws.unfiltered" %% "unfiltered-netty-server" % unfilteredVersion,
    "ws.unfiltered" %% "unfiltered-json4s" % unfilteredVersion
  )
)

lazy val `zio-http` = (project in file("zio-http")).settings(
  libraryDependencies += "io.d11" %% "zhttp" % "2.0.0-RC2"
)

lazy val uzhttp = (project in file("uzhttp")).settings(
  libraryDependencies += "org.polynote" %% "uzhttp" % "0.2.8"
)

lazy val root = project
  .in(file("."))
  .settings(
    name := "web-frameworks-templates"
  )
  .aggregate(
    `zio-http`,
    `akka-http`,
    unfiltered,
    finatra,
    http4s,
    play,
    scalatra,
    cask,
    uzhttp
  )
