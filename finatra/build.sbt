name := """finatra"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.7"

libraryDependencies ++= Seq(
  "com.twitter.finatra" %% "finatra-http" % "2.1.1"
)

resolvers += "Twitter" at "http://maven.twttr.com"
