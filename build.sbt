import sbt.Keys.scalaVersion

name := BuildConfig.artifactName

organization := "me.biomunky"

scalaVersion := "2.12.2"

crossPaths := false

parallelExecution in Test := true

resolvers ++= Seq(
  "artifactory" at "http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases",
  "sbt-plugin-releases" at "http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/",
  "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
  "Restlet Repository" at "http://maven.restlet.org",
  "conjars.org" at "http://conjars.org/repo"
)


val json4sVersion = "3.5.2"

libraryDependencies ++= Seq(
  "com.typesafe" % "config" % "1.3.0",
  "com.typesafe.scala-logging" %% "scala-logging" % "3.5.0",
  "ch.qos.logback" % "logback-classic" % "1.1.7",
  "org.json4s"   %% "json4s-native"     % json4sVersion,
  "org.json4s"   %% "json4s-jackson"    % json4sVersion,
  "com.darwinsys" % "hirondelle-date4j" % "1.5.1",
  "org.scalaj" %% "scalaj-http" % "2.3.0",
  "org.jsoup" % "jsoup" % "1.8.3",
  "org.specs2" %% "specs2-core" % "3.8.9" % "test",
  "org.mockito" % "mockito-core" % "1.9.5" % "test"
)

