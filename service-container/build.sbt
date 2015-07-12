name := """service-container-example"""

version := "0.0.1"

scalaVersion := "2.11.6"

mainClass in (Compile, run) := Some("WebServer")

libraryDependencies ++= Seq(
  "com.github.vonnagy" %% "service-container" % "1.0.2"
)
