name := "fintrospect-example-app"

scalaVersion := "2.11.8"

mainClass in(Test, run) := Some("FintrospectServer")

libraryDependencies ++= Seq(
  "io.fintrospect" %% "fintrospect-core" % "13.13.0",
  "io.fintrospect" %% "fintrospect-circe" % "13.13.0"
)
