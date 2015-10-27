import sbt._
import sbt.Keys._
import ScoverageSbtPlugin._

lazy val root = project.in(file(".")).
  aggregate(SRDFJS, SRDFJVM).
  settings(
    publish := {},
    publishLocal := {}
  )

lazy val SRDF = crossProject.
  crossType(CrossType.Pure).
  settings(
    name := "SRDF",
    version := "0.0.1",
    scalaVersion := "2.11.7", 
    organization := "es.weso",
	libraryDependencies ++= Seq(
	  "org.scalatest" %%% "scalatest" % "3.0.0-M10" % "test"
	)
  ).
  jvmSettings(
	libraryDependencies ++= Seq(
    ),
	licenses += ("MPL-2.0", url("http://opensource.org/licenses/MPL-2.0"))
  ).
  jsSettings(
	libraryDependencies ++= Seq(
    ),
	licenses += ("MPL-2.0", url("http://opensource.org/licenses/MPL-2.0"))
  )
  
lazy val SRDFJVM = SRDF.jvm
lazy val SRDFJS = SRDF.js  
	 
publishMavenStyle := true

bintrayRepository in bintray := "weso-releases"

bintrayOrganization in bintray := Some("weso")

licenses += ("MPL-2.0", url("http://opensource.org/licenses/MPL-2.0"))

resolvers += "Bintray" at "http://dl.bintray.com/weso/weso-releases"

EclipseKeys.useProjectId := true

// Publish site info
site.settings

site.publishSite

site.includeScaladoc()


