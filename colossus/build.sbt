name := """colossus-example"""

version := "0.1.0"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.tumblr" %% "colossus" % "0.6.8",
  "io.circe" %% "circe-core" % "0.2.1",
  "io.circe" %% "circe-generic" % "0.2.1"
)
