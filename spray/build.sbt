organization  := "spray-example"

version       := "0.0.1-SNAPSHOt"

scalaVersion  := "2.11.5"

mainClass in (Compile, run) := Some("WebServer")

crossScalaVersions := Seq("2.9.2", "2.11.5")

libraryDependencies ++= {
  val akkaV = "2.3.6"
  val sprayV = "1.3.2"
  Seq(
    "io.spray"            %%  "spray-can"     % sprayV,
    "io.spray"            %%  "spray-routing" % sprayV,
    "org.json4s"          %% "json4s-jackson" % "3.2.11",
    "com.typesafe.akka"   %%  "akka-actor"    % akkaV
  )
}

