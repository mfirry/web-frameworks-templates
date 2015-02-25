name := """finatra"""

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.10.4"

libraryDependencies ++= Seq(
  "com.twitter" %% "finatra" % "1.6.0"
)

resolvers += "Twitter" at "http://maven.twttr.com"