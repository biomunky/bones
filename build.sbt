name := "bones"

version := "0.1"

scalaVersion := "2.10.4"

scalacOptions := Seq("-deprecation", "-unchecked", "-Xlint", "-feature", "-encoding", "utf8")

resolvers ++= Seq(
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/",
  "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
  "Restlet Repository" at "http://maven.restlet.org",
  "google-api-services" at "http://mavenrepo.google-api-java-client.googlecode.com/hg",
  "Spray Repository" at "http://repo.spray.cc/",
  "spray" at "http://repo.spray.io/"
)


libraryDependencies ++= Seq(
  "io.spray" %%  "spray-json" % "1.2.5",
  "org.specs2" %% "specs2" % "2.3.4" % "test",
  "org.mockito" % "mockito-core" % "1.9.5" % "test",
  "com.typesafe" % "config" % "1.2.1",
  "io.argonaut" %% "argonaut" % "6.0.4"            
)