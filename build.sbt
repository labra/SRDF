import sbt._
import sbt.Keys._
import ScoverageSbtPlugin._

lazy val root = project.in(file(".")).
  aggregate(srdfJS, srdfJVM).
  settings(
    publish := {},
    publishLocal := {}
  )

lazy val srdf = crossProject.
  crossType(CrossType.Pure).
  settings(
    name := "srdf",
    version := "0.0.3",
    scalaVersion := "2.11.7", 
    organization := "es.weso",
	libraryDependencies ++= Seq(
	  "org.scalatest" %%% "scalatest" % "3.0.0-M10" % "test"
	)
  ).
  jvmSettings(
    name := name.value + "-jvm",
	libraryDependencies ++= Seq(
    ),
	licenses += ("MPL-2.0", url("http://opensource.org/licenses/MPL-2.0"))
  ).
  jsSettings(
    name := name.value + "-js",
	libraryDependencies ++= Seq(
    ),
	licenses += ("MPL-2.0", url("http://opensource.org/licenses/MPL-2.0"))
  )
  
lazy val srdfJVM = srdf.jvm
lazy val srdfJS = srdf.js  
	 
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

ghpages.settings

git.remoteRepo := "git@github.com:labra/SRDF.git"

