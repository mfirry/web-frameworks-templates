name := "vertx-example"

version := "1.0"

scalaVersion := "2.12.1"

libraryDependencies += "io.vertx" %% "vertx-web-scala" % "3.4.2"

packageOptions in (Compile, packageBin) ++= Seq(
  Package.ManifestAttributes("Main-Class" -> "io.vertx.core.Launcher"),
  Package.ManifestAttributes("Main-Verticle" -> "scala:com.example.Hello")
)

assemblyMergeStrategy in assembly := {
  case "META-INF/io.netty.versions.properties" => MergeStrategy.first
  case "codegen.json" => MergeStrategy.first
  case item => (assemblyMergeStrategy in assembly).value(item)
}
