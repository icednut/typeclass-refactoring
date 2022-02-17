ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "2.13.8"

lazy val root = (project in file("."))
  .settings(
    name := "typeclass-refactoring",
    idePackagePrefix := Some("io.icednut.study"),
    libraryDependencies ++= Seq(
      "org.kohsuke" % "github-api" % "1.301",
      "org.scalatest" %% "scalatest" % "3.2.11" % "test"
    )
  )
