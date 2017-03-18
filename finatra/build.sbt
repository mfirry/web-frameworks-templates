name := """finatra"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.twitter" %% "finatra-http" % "2.9.0"
)

resolvers += "Twitter" at "http://maven.twttr.com"
