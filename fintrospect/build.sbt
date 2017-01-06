name := "fintrospect-example-app"

scalaVersion := "2.11.8"

mainClass in(Test, run) := Some("FintrospectServer")

libraryDependencies ++= Seq(
  "io.fintrospect" %% "fintrospect-core" % "14.0.0",
  "io.fintrospect" %% "fintrospect-circe" % "14.0.0"
)
