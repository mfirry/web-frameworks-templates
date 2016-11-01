organization  := "spray-example"

version       := "0.0.1-SNAPSHOT"

scalaVersion  := "2.11.6"

mainClass in (Compile, run) := Some("WebServer")

libraryDependencies ++= {
  val akkaV = "2.3.9"
  val sprayV = "1.3.4"
  Seq(
    "io.spray"            %%  "spray-can"       % sprayV,
    "io.spray"            %%  "spray-routing"   % sprayV,
    "io.spray"            %%  "spray-json"      % "1.3.2",
    "com.typesafe.akka"   %%  "akka-actor"      % akkaV
  )
}
