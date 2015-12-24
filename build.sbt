import BuildConfig._
import aether.MavenCoordinates
import aether.Aether.createArtifact
import sbt.Package.ManifestAttributes
import sbtrelease.Git

name := BuildConfig.artifactName

organization := "me.biomunky"

crossPaths := false

parallelExecution in Test := true

resolvers ++= Seq(
  "artifactory" at "http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases",
  "sbt-plugin-releases" at "http://scalasbt.artifactoryonline.com/scalasbt/sbt-plugin-releases/",
  "Typesafe Repository" at "http://repo.typesafe.com/typesafe/releases/",
  "Sonatype OSS Releases" at "http://oss.sonatype.org/content/repositories/releases/",
  "Sonatype OSS Snapshots" at "http://oss.sonatype.org/content/repositories/snapshots/",
  "Restlet Repository" at "http://maven.restlet.org",
  "google-api-services" at "http://mavenrepo.google-api-java-client.googlecode.com/hg",
  "Spray Repository" at "http://repo.spray.cc/",
  "spray" at "http://repo.spray.io/",
  "conjars.org" at "http://conjars.org/repo"
)

val sparkVersion = "1.5.1"

val json4sVersion = "3.2.10"

libraryDependencies ++= Seq(
  "org.apache.spark" %% "spark-core" % sparkVersion % "provided",
  "org.apache.spark" %% "spark-streaming" % sparkVersion % "provided",
  "org.apache.spark" % "spark-streaming-kinesis-asl_2.10" % sparkVersion,
  "org.elasticsearch" % "elasticsearch-spark_2.10" % "2.1.0.Beta4",
  "com.typesafe" % "config" % "1.3.0",
  "org.json4s" %% "json4s-native" % json4sVersion,
  "org.json4s" %% "json4s-jackson" % json4sVersion,
  "com.darwinsys" % "hirondelle-date4j" % "1.5.1",
  "org.specs2" %% "specs2" % "2.3.4" % "test",
  "org.mockito" % "mockito-core" % "1.9.5" % "test"
)

val meta = """META.INF(.)*""".r

assemblyMergeStrategy in assembly <<= (mergeStrategy in assembly) { (old) =>
  {
    case PathList("javax", "servlet", xs @ _*) => MergeStrategy.last
    case PathList("javax", "activation", xs @ _*) => MergeStrategy.last
    case PathList("org", "apache", xs @ _*) => MergeStrategy.last
    case PathList("com", "esotericsoftware", xs @ _*) => MergeStrategy.last
    case PathList("plugin.properties") => MergeStrategy.last
    case PathList("com", "google", xs @ _*) => MergeStrategy.last
    case meta(_) => MergeStrategy.discard
    case x => old(x)
  }
}

testFrameworks += new TestFramework("utest.runner.JvmFramework")

runMain in Compile <<= Defaults.runMainTask(fullClasspath in Compile,  runner in (Compile, run))

run in Compile <<= Defaults.runTask(fullClasspath in Compile, mainClass in  (Compile, run), runner in (Compile, run))

assembleArtifact in assemblyPackageScala := false

test in assembly := {}

releaseSettings

credentials += Credentials("Sonatype Nexus Repository Manager", "localhost", "username", "password")

publishArtifact in Test := false

publishTo := {
  val nexus = "http://your.nexus.box"
  if (isSnapshot.value)
    Some("snapshots" at nexus + "nexus/content/repositories/snapshots")
  else
    Some("releases" at nexus + "nexus/content/repositories/releases")
}

artifact in (Compile, assembly) := {
  val art = (artifact in (Compile, assembly)).value
  art.copy(`classifier` = Some("assembly"))
}

addArtifact(artifact in (Compile, assembly), assembly)

aetherPublishSettings

publishMavenStyle := true

assemblyJarName in assembly := s"${BuildConfig.artifactName}-${BuildConfig.buildNumber}.jar"
