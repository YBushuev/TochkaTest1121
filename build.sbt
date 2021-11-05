name := "TestTask"

Global / cancelable := true
Global / onChangedBuildSource := ReloadOnSourceChanges
ThisBuild / Test / fork := true
ThisBuild / organization := "io.bushuev"
ThisBuild / scalaVersion := "2.13.7"
ThisBuild / scalafmtOnCompile := true
ThisBuild / turbo := true
ThisBuild / version := "0.1"

lazy val commonSettings = Seq(
  libraryDependencies += "org.typelevel" %% "cats-effect"     % "3.2.9",
  libraryDependencies += "org.typelevel" %% "cats-core"       % "2.6.1",
  libraryDependencies += "ch.qos.logback" % "logback-classic" % "1.2.6" % Runtime,
  libraryDependencies += "org.typelevel" %% "log4cats-core"   % "2.1.1", // Only if you want to Support Any Backend
  libraryDependencies += "org.typelevel" %% "log4cats-slf4j"  % "2.1.1" // Direct Slf4j Support - Recommended
)

lazy val taskOne = project
  .in(file("taskOne"))
  .settings(commonSettings:_*)

lazy val taskTwo = project
  .in(file("taskTwo"))
  .settings(commonSettings:_*)

lazy val taskThee = project
  .in(file("taskThree"))
  .settings(commonSettings:_*)
