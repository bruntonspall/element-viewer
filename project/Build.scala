import sbt._
import Keys._
import sbtappengine.Plugin.appengineSettings
import com.typesafe.sbt.SbtScalariform.scalariformSettings
import twirl.sbt.TwirlPlugin._

object elementViewerBuild  extends Build {

  lazy val basicSettings = seq(
    version               := "1.0.0",
    organization          := "com.gu.contentapi",
    description           := "",
    scalaVersion          := "2.9.1",
    scalacOptions         := Seq("-deprecation", "-encoding", "utf8")
  )


  // configure prompt to show current project
  override lazy val settings = super.settings ++ basicSettings :+ {
    shellPrompt := { s => Project.extract(s).currentProject.id + " > " }
  }

  lazy val root = Project(id="elementViewer", base=file("."))
    .settings(
      resolvers ++= Seq(
        "objectivy repo" at "http://maven.objectify-appengine.googlecode.com/git/")
    )
    .settings(
      libraryDependencies ++= Seq(
        "com.googlecode.objectify" % "objectify" % "4.0a3",
        "org.scalatra" %% "scalatra" % "2.0.4",
        "javax.persistence" % "persistence-api" % "1.0",
        "org.skife.com.typesafe.config" % "typesafe-config" % "0.3.0",
        "com.google.appengine" % "appengine-api-1.0-sdk" % "1.6.5",
        "ch.qos.logback" % "logback-classic" % "0.9.26",
        "com.weiglewilczek.slf4s" %% "slf4s" % "1.0.7",
        "cc.spray" %%  "spray-json" % "1.1.1",
        "commons-codec" %  "commons-codec" % "1.6",
        "net.liftweb" %% "lift-json" % "2.4",
        "net.liftweb" %% "lift-json-ext" % "2.4",
        "org.scalatest" %% "scalatest" % "1.6.1" % "test",
        "javax.servlet" % "servlet-api" % "2.3" % "provided",
        "org.mortbay.jetty" % "jetty" % "6.1.22" % "container")
    )
    .settings(appengineSettings: _*)
    .settings(scalariformSettings: _*)
    .settings(Twirl.settings: _*)
    .settings(
      Twirl.twirlImports := Seq("com.gu.contentapi.model._")
    )
}
