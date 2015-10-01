name := """angular-seed-play-java"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "org.postgresql" % "postgresql" % "9.4-1202-jdbc42",
  "org.webjars" % "angularjs" % "1.4.6",
  "org.webjars" % "requirejs" % "2.1.11-1",
  "org.mongodb" % "mongodb-driver-async" % "3.0.4"
)     

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
pipelineStages := Seq(rjs, digest, gzip)


fork in run := true