name := """colossus-example"""

version := "0.1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.tumblr"  %% "colossus"       % "0.11.0-M1",
  "io.circe"    %% "circe-core"     % "0.9.0-M2",
  "io.circe"    %% "circe-generic"  % "0.9.0-M2"
)
