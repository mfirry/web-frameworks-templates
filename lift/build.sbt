name := "Lift 3.0 starter template"

version := "0.1.0"

organization := "net.liftweb"

scalaVersion := "2.11.7"

resolvers ++= Seq("snapshots"     at "https://oss.sonatype.org/content/repositories/snapshots",
                "releases"        at "https://oss.sonatype.org/content/repositories/releases"
                )

seq(webSettings :_*)

unmanagedResourceDirectories in Test <+= (baseDirectory) { _ / "src/main/webapp" }

scalacOptions ++= Seq("-deprecation", "-unchecked")

libraryDependencies ++= {
  val liftVersion = "3.0-RC4"
  Seq(
    "net.liftweb"       %% "lift-webkit"        % liftVersion        % "compile",
    "org.eclipse.jetty" % "jetty-webapp"        % "8.1.17.v20150415"  % "container,test",
    "org.eclipse.jetty" % "jetty-plus"          % "8.1.17.v20150415"  % "container,test", // For Jetty Config
    "org.eclipse.jetty.orbit" % "javax.servlet" % "3.0.0.v201112011016" % "container,test" artifacts Artifact("javax.servlet", "jar", "jar"),
    "ch.qos.logback"    % "logback-classic"     % "1.1.3",
    "org.specs2"        %% "specs2-core"        % "3.6.4"           % "test"
  )
}

scalacOptions in Test ++= Seq("-Yrangepos")
