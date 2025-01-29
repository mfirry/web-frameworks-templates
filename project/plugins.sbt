ThisBuild / libraryDependencySchemes += "org.scala-lang.modules" %% "scala-xml" % VersionScheme.Always

resolvers += "Typesafe repository" at "https://repo.typesafe.com/typesafe/releases/"

addSbtPlugin("org.playframework" % "sbt-plugin" % "3.0.6")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.9")

addSbtPlugin("org.scalameta" % "sbt-scalafmt" % "2.4.6")

addSbtPlugin("org.playframework.twirl" % "sbt-twirl" % "2.0.7")
addSbtPlugin("org.scalatra.sbt" % "sbt-scalatra" % "1.0.3")

addSbtPlugin("com.eed3si9n" % "sbt-assembly" % "0.14.10")
