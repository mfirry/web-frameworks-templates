name := """web-frameworks-templates"""

version := "0.1.1"

val scalaV = "2.13.7"

val akkaHttpVersion = "10.2.6"
val analogwebVersion = "0.12.0"
val http4sVersion = "0.23.4"
val unfilteredVersion = "0.10.3"
val ScalatraVersion = "2.7.0"
val finchVersion = "0.32.1"

lazy val `akka-http` = (project in file("akka-http")).settings(
      scalaVersion := scalaV,
      scalacOptions ++= Seq("-deprecation", "-feature"),
      libraryDependencies ++= Seq(
        "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
        "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
        "com.typesafe.akka" %% "akka-stream"          % "2.6.16"
      ))

lazy val analogweb = (project in file("analogweb")).settings(
      scalaVersion := scalaV,
      libraryDependencies ++= Seq(
        "org.analogweb" %% "analogweb-scala" % analogwebVersion,
        "org.analogweb" %% "analogweb-circe" % analogwebVersion
      ))

lazy val cask = (project in file("cask")).settings(
  scalaVersion := scalaV,
  libraryDependencies ++= Seq(
    "com.lihaoyi" %% "cask"     % "0.7.12",
    "com.lihaoyi" %% "upickle"  % "1.2.0"
  ))

// lazy val colossus = (project in file("colossus")).settings(
//       scalaVersion := scalaV,
//       libraryDependencies ++= Seq(
//         "com.tumblr"  %% "colossus"       % "0.11.0",
//         "io.circe"    %% "circe-core"     % "0.9.0-M2",
//         "io.circe"    %% "circe-generic"  % "0.9.0-M2"
//       ))

lazy val finatra = (project in file("finatra")).settings(
      scalaVersion := scalaV,
      libraryDependencies ++= Seq(
        "com.twitter" %% "finatra-http-server" % "21.9.0"
      ))

lazy val finch = (project in file("finch")).settings(
      scalaVersion := scalaV,
      libraryDependencies ++= Seq(
        "com.github.finagle" %% "finchx-core" % finchVersion,
        "com.github.finagle" %% "finchx-circe" % finchVersion,
        "io.circe" %% "circe-core" % "0.14.1",
        "io.circe" %% "circe-generic" % "0.14.1",
        "io.circe" %% "circe-jawn" % "0.14.1"
      ))

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
        "org.http4s"  %% "http4s-blaze-server"  % http4sVersion,
        "org.http4s"  %% "http4s-circe"         % http4sVersion,
        "org.http4s"  %% "http4s-dsl"           % http4sVersion
))

// lazy val lift = (project in file("lift")).settings(
//       scalaVersion := scalaV,
//       libraryDependencies ++= Seq())

// lazy val peregrine = (project in file("peregrine")).settings(
//   scalaVersion := scalaV,
//   resolvers += "Twitter" at "http://maven.twttr.com",
//   libraryDependencies += "com.github.dvarelap" %% "peregrine" % "1.2.2")

lazy val play = (project in file("play"))
  .settings(
    scalaVersion := scalaV,
    scalacOptions += s"-Wconf:src=${target.value}/.*:s",
    libraryDependencies ++= Seq(
      guice,
      "com.typesafe.play" %% "play-json" % "2.8.0"
    )
  )
  .enablePlugins(PlayScala)
  .disablePlugins(PlayLayoutPlugin)

lazy val scalatra = (project in file("scalatra")).settings(
      scalaVersion := scalaV,
      libraryDependencies ++= Seq(
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "org.scalatra" %% "scalatra-json" % ScalatraVersion,
        "org.json4s"   %% "json4s-jackson" % "3.6.9",
        "ch.qos.logback" % "logback-classic" % "1.2.3" % "runtime",
        "org.eclipse.jetty" % "jetty-webapp" % "9.4.19.v20190610" % "container;compile",
        "javax.servlet" % "javax.servlet-api" % "3.1.0" % "provided"
      ))

lazy val unfiltered = (project in file("unfiltered")).settings(
      scalaVersion := scalaV,
      libraryDependencies ++= Seq(
        "ws.unfiltered" %% "unfiltered-filter"        % unfilteredVersion,
        "ws.unfiltered" %% "unfiltered-netty-server"  % unfilteredVersion,
        "ws.unfiltered" %% "unfiltered-json4s"        % unfilteredVersion
      ))

// lazy val scalene = (project in file("scalene")).settings(
//       scalacOptions += "-target:jvm-1.9",
//       scalaVersion := scalaV,
//       libraryDependencies ++= Seq(
//         "io.scalene" %% "scalene" % "0.1.0",
//         "io.scalene" %% "scalene-routing" % "0.1.0"

//       ))

lazy val `zio-http` = (project in file("zio-http")).settings(
  scalaVersion := scalaV,
  libraryDependencies += "io.d11" %% "zhttp" % "1.0.0.0-RC17"
)

lazy val uzhttp = (project in file("uzhttp")).settings(
      scalaVersion := scalaV,
      libraryDependencies ++= Seq(
        "org.polynote" %% "uzhttp" % "0.2.8"
      ))

lazy val root = (project.in(file(".")).
  aggregate(`zio-http`, `akka-http`, analogweb, unfiltered, finatra, http4s, play, scalatra, finch, cask, uzhttp))

enablePlugins(ScalatraPlugin)
