name := """addhub"""

version := "1.0-SNAPSHOT"

lazy val root = (project in file(".")).enablePlugins(PlayJava, PlayEbean, SbtWeb)

scalaVersion := "2.11.6"

libraryDependencies ++= Seq(
  "com.typesafe.play" % "play-cache_2.11" % "2.4.2",
  "org.postgresql" % "postgresql" % "9.4-1202-jdbc42",
  "org.webjars" % "angularjs" % "1.4.6",
  "org.webjars" % "requirejs" % "2.1.11-1",
  "org.webjars" % "jquery" % "2.1.4",
  "org.webjars" % "bootstrap" % "3.3.5",
  //  "org.mongodb" % "mongodb-driver-async" % "3.0.4",
  "org.mongodb" % "mongo-java-driver" % "3.0.4",
  "org.apache.commons" % "commons-lang3" % "3.4",
  "commons-collections" % "commons-collections" % "3.2.1",
  "com.restfb" % "restfb" % "1.16.0",
  "org.twitter4j" % "twitter4j-core" % "4.0.4",
  "org.twitter4j" % "twitter4j-media-support" % "4.0.2",

  "com.belerweb" % "weibo4j-oauth2" % "2.1.1-beta2-3",
  "org.jongo" % "jongo" % "1.2",
  "org.pac4j" % "play-pac4j-java" % "2.0.0",
  "org.pac4j" % "pac4j-http" % "1.8.0",
  "org.pac4j" % "pac4j-oauth" % "1.8.0",
  "org.pac4j" % "pac4j-mongo" % "1.8.0"
)

//RjsKeys.mainModule := "app/main"

// Play provides two styles of routers, one expects its actions to be injected, the
// other, legacy style, accesses its actions statically.
routesGenerator := InjectedRoutesGenerator
//pipelineStages := Seq(rjs, digest, gzip)
pipelineStages := Seq( digest, gzip)

