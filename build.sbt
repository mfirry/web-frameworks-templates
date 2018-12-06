name := """web-frameworks-templates"""

version := "0.1.1"

val scalaV = "2.12.7"

val akkaHttpVersion = "10.1.5"
val analogwebVersion = "0.10.1"
val http4sVersion = "0.18.20"
val unfilteredVersion = "0.9.1"

lazy val `akka-http` = (project in file("akka-http")).settings(
      scalaVersion := scalaV,
      libraryDependencies ++= Seq(
        "com.typesafe.akka" %% "akka-http"            % akkaHttpVersion,
        "com.typesafe.akka" %% "akka-http-spray-json" % akkaHttpVersion,
        "com.typesafe.akka" %% "akka-stream"          % "2.5.11"
      ))

lazy val analogweb = (project in file("analogweb")).settings(
      scalaVersion := scalaV,
      libraryDependencies ++= Seq(
        "org.analogweb" %% "analogweb-scala" % analogwebVersion,
        "org.analogweb" %% "analogweb-circe" % analogwebVersion,
        "org.analogweb" %  "analogweb-netty" % analogwebVersion
      ))

lazy val colossus = (project in file("colossus")).settings(
      scalaVersion := scalaV,
      libraryDependencies ++= Seq(
        "com.tumblr"  %% "colossus"       % "0.11.0",
        "io.circe"    %% "circe-core"     % "0.9.0-M2",
        "io.circe"    %% "circe-generic"  % "0.9.0-M2"
      ))

lazy val finatra = (project in file("finatra")).settings(
      scalaVersion := scalaV,
      libraryDependencies ++= Seq(
        "com.twitter" %% "finatra-http" % "18.10.0"
      ))

lazy val finch = (project in file("finch")).settings(
      scalaVersion := scalaV,
      libraryDependencies ++= Seq(
        "com.github.finagle" %% "finchx-core" % "0.26.1",
        "com.github.finagle" %% "finchx-circe" % "0.26.1",
        "io.circe" %% "circe-core" % "0.10.0",
        "io.circe" %% "circe-generic" % "0.10.0",
        "io.circe" %% "circe-jawn" % "0.10.0"
      ))

lazy val fintrospect = (project in file("fintrospect")).settings(
      scalaVersion := scalaV,
      libraryDependencies ++= Seq(
        "io.fintrospect" %% "fintrospect-core" % "14.22.0",
        "io.fintrospect" %% "fintrospect-circe" % "14.22.0"
      ))

lazy val http4s = (project in file("http4s")).settings(
      scalaVersion := scalaV,
      libraryDependencies ++= Seq(
        "org.http4s" %% "http4s-blaze-server" % http4sVersion,
        "org.http4s" %% "http4s-dsl" % http4sVersion,
        "org.http4s" %% "http4s-circe" % http4sVersion,
        "org.typelevel" %% "cats-effect" % "0.5",
        "io.circe" %% "circe-generic" % "0.9.3",
        "io.circe" %% "circe-core" % "0.9.3"
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
    libraryDependencies ++= Seq(
      guice,
      "com.typesafe.play" %% "play-json" % "2.6.10"
    )
  )
  .enablePlugins(PlayScala)
  .disablePlugins(PlayLayoutPlugin)

// lazy val scalatra = (project in file("scalatra")).settings(
//       scalaVersion := scalaV,
//       libraryDependencies ++= Seq())

lazy val `service-container` = (project in file("service-container")).settings(
      scalaVersion := scalaV,
      libraryDependencies ++= Seq("com.github.vonnagy" %% "service-container" % "2.0.7"))

lazy val unfiltered = (project in file("unfiltered")).settings(
      scalaVersion := scalaV,
      libraryDependencies ++= Seq(
        "ws.unfiltered" %% "unfiltered-filter"        % unfilteredVersion,
        "ws.unfiltered" %% "unfiltered-netty-server"  % unfilteredVersion,
        "ws.unfiltered" %% "unfiltered-json4s"        % unfilteredVersion
      ))

lazy val root = (project.in(file(".")).
  aggregate(`akka-http`, analogweb, colossus, finatra, finch, fintrospect, http4s, play, `service-container`, unfiltered))