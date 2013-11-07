import sbt._
import Keys._

object CacheableBuild extends Build {
  
  object Versions {
    val scala = "2.10.3"
  }

  lazy val root = Project(
    id = "cacheable",
    base = file("."),
    settings = standardSettings) aggregate(core, guava)

  lazy val core = Project(
    id = "cacheable-core",
    base = file("core"),
    settings = standardSettings ++ Seq(
      libraryDependencies ++= Seq(
        "org.scala-lang" % "scala-reflect" % Versions.scala
      )
    )
  )

  lazy val guava = Project(
    id = "cacheable-guava",
    base = file("guava"),
    settings = standardSettings ++ Seq(
      libraryDependencies ++= Seq(
        "com.google.guava" % "guava" % "15.0",
        "com.google.code.findbugs" % "jsr305" % "1.3.9"
      )
    )
  ) dependsOn(core)

  lazy val standardSettings = Defaults.defaultSettings ++ Seq(
    organization := "com.github.cb372",
    version      := "0.1-SNAPSHOT",
    scalaVersion := Versions.scala,
    scalacOptions ++= Seq("-unchecked", "-deprecation"),
    libraryDependencies ++= Seq(
      "org.scalatest" %% "scalatest" % "2.0" % "test"
      //"org.scalamock" %% "scalamock-scalatest-support" % "3.0.1" % "test"
    ),
    parallelExecution in Test := false
  )
}

