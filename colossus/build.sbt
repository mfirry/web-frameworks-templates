name := """colossus-example"""

version := "0.1.0"

scalaVersion := "2.11.8"

libraryDependencies ++= Seq(
  "com.tumblr" %% "colossus" % "0.7.0",
  "io.circe" %% "circe-core" % "0.3.0",
  "io.circe" %% "circe-generic" % "0.3.0"
)
