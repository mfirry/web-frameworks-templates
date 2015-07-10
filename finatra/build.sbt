name := """finatra"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.twitter.finatra" %% "finatra-http" % "2.0.0.M2"
)

resolvers += "Twitter" at "http://maven.twttr.com"