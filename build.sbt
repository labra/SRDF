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
  crossType(CrossType.Full).
  settings(
    name := "srdf",
    version := "0.0.5",
    scalaVersion := "2.11.7", 
    organization := "es.weso",
	libraryDependencies ++= Seq(
	  "org.scalatest" %%% "scalatest" % "3.0.0-M15" % "test"
	)
  ).
  settings(publishSettings:_*).
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

lazy val publishSettings = Seq(
  homepage := Some(url("https://github.com/labra/SRDF")),
  licenses := Seq("MIT" -> url("http://opensource.org/licenses/MIT")),
  scmInfo := Some(ScmInfo(url("https://github.com/labra/SRDF"), "scm:git:git@github.com:labra/SRDF.git")),
  autoAPIMappings := true,
  apiURL := Some(url("http://labra.github.io/SRDF/latest/api/")),
  pomExtra := (
    <developers>
      <developer>
        <id>labra</id>
        <name>Jose Emilio Labra</name>
        <url>https://github.com/labra/</url>
      </developer>
    </developers>
  ),
  scalacOptions in (Compile,doc) ++= Seq(
    "-Xfatal-warnings",
    "-doc-source-url", scmInfo.value.get.browseUrl + "/tree/master€{FILE_PATH}.scala",
    "-sourcepath", baseDirectory.in(LocalRootProject).value.getAbsolutePath,
    "-diagrams"
  )
)
