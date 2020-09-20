resolvers ++= Seq(
  "Artima Maven Repository" at "https://repo.artima.com/releases"
)

lazy val root = (project in file(".")).aggregate(server, client)

lazy val server = (project in file("server"))
  .settings(commonSettings)
  .settings(
    scalaJSProjects := Seq(client),
    pipelineStages in Assets := Seq(scalaJSPipeline),
    pipelineStages := Seq(digest, gzip),
    // triggers scalaJSPipeline when using compile or continuous compilation
    compile in Compile := ((compile in Compile) dependsOn scalaJSPipeline).value,
    libraryDependencies ++= Seq(
      "com.vmunier" %% "scalajs-scripts" % "1.1.4",
      guice,
      specs2 % Test
    )
  )
  .enablePlugins(PlayScala)
  .enablePlugins(WebScalaJSBundlerPlugin)
  .dependsOn(sharedJvm)

lazy val client = (project in file("client"))
  .enablePlugins(ScalaJSPlugin)
  .enablePlugins(ScalaJSBundlerPlugin)
  .enablePlugins(JSDependenciesPlugin)
  .settings(commonSettings)
  .settings(
    scalaJSUseMainModuleInitializer := true,
    scalaJSUseMainModuleInitializer in Test := false,
    libraryDependencies ++= Seq(
      "org.scala-js" %%% "scalajs-dom" % "1.0.0",
      "com.github.japgolly.scalajs-react" %%% "core" % "1.7.5",
      "com.github.japgolly.scalajs-react" %%% "extra" % "1.7.5",
      "com.github.japgolly.scalacss" %%% "core" % "0.6.1",
      "com.github.japgolly.scalacss" %%% "ext-react" % "0.6.1",
      "com.lihaoyi" %%% "utest" % "0.7.5" % "test"
    ),
    npmDependencies in Compile ++= Seq(
      "react" -> "16.13.1",
      "react-dom" -> "16.13.1"
    ),
    requireJsDomEnv in Test := true
  )
  .dependsOn(sharedJs)

lazy val shared = crossProject(JSPlatform, JVMPlatform)
  .crossType(CrossType.Pure)
  .in(file("shared"))
  .settings(commonSettings)
  .jsConfigure(_.enablePlugins(ScalaJSWeb))
lazy val sharedJvm = shared.jvm
lazy val sharedJs = shared.js

lazy val commonSettings =
  Seq(scalaVersion := "2.13.1", organization := "net.redjamjar")

// loads the Play server project at sbt startup
onLoad in Global := (Command
  .process("project server", _: State)) compose (onLoad in Global).value
