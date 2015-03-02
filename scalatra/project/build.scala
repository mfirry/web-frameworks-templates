import sbt._
import Keys._
import org.scalatra.sbt._
import org.scalatra.sbt.PluginKeys._
import com.mojolly.scalate.ScalatePlugin._
import ScalateKeys._

object MyScalatraWebAppBuild extends Build {
  val Organization = "com.example"
  val Name = "My Scalatra Web App"
  val Version = "0.1.0-SNAPSHOT"
  val ScalaVersion = "2.11.1"
  val ScalatraVersion = "2.3.0"

  lazy val project = Project (
    "my-scalatra-web-app",
    file("."),
    settings = ScalatraPlugin.scalatraWithJRebel ++ scalateSettings ++ Seq(
      organization := Organization,
      name := Name,
      version := Version,
      scalaVersion := ScalaVersion,
      resolvers += Classpaths.typesafeReleases,
      libraryDependencies ++= Seq(
        "org.scalatra" %% "scalatra" % ScalatraVersion,
        "org.scalatra" %% "scalatra-json" % ScalatraVersion,
        "org.json4s"   %% "json4s-jackson" % "3.2.11",
        "org.scalatra" %% "scalatra-specs2" % ScalatraVersion % "test",
        "ch.qos.logback" % "logback-classic" % "1.1.2" % "runtime",
        "org.eclipse.jetty" % "jetty-webapp" % "9.1.5.v20140505" % "container",
        "org.eclipse.jetty" % "jetty-plus" % "9.1.5.v20140505" % "container",
        "javax.servlet" % "javax.servlet-api" % "3.1.0"
      ),
      scalateTemplateConfig in Compile <<= (sourceDirectory in Compile){ base =>
        Seq(
          TemplateConfig(
            base / "webapp" / "WEB-INF" / "templates",
            Seq.empty,  /* default imports should be added here */
            Seq(
              Binding("context", "_root_.org.scalatra.scalate.ScalatraRenderContext", importMembers = true, isImplicit = true)
            ),  /* add extra bindings here */
            Some("templates")
          )
        )
      }
    )
  )
}
