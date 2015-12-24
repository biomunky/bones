import sbt._


object Resolvers {
  lazy val releases = "http://maven.wwsga.me/"
  lazy val Snapshots = "http://maven.wwsga.me/SNAPSHOTS"
}

object BuildConfig {
  val scalaVersion = "2.11.7"
  val scalacOptions = Seq("-deprecation", "-unchecked", "-feature", "-encoding", "uft8")
  val envBuildNumber = env("BUILD_NUMBER")
  val buildNumber = if (envBuildNumber.nonEmpty) envBuildNumber else "0.0.1-SNAPSHOT"
  val buildWhen = (new java.util.Date).toString
  val buildMachine = java.net.InetAddress.getLocalHost.getHostName
  val name = "bones"
  val organisation = "me.biomunky"

  def env(key: String, default: String = ""): String = Option(System.getenv get key) getOrElse default
}