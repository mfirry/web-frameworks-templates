name := "http4s-example"

version := "0.0.1-SNAPSHOT"

scalaVersion := "2.11.11"

val http4sVersion = "0.18.7"

libraryDependencies ++= Seq(
	"org.http4s" %% "http4s-blaze-server" % http4sVersion,
	"org.http4s" %% "http4s-dsl" % http4sVersion,
	"org.http4s" %% "http4s-argonaut" % http4sVersion,
  "org.typelevel" %% "cats-effect" % "0.5"
)

resolvers += "Bintray" at "http://dl.bintray.com/pchiusano/maven/"

resolvers += "Scalaz Bintray Repo" at "http://dl.bintray.com/scalaz/releases"
