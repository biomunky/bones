object Resolvers {
  lazy val releases = "http://my.maven.repo/"
  lazy val Snapshots = "http://my.maven.repo/SNAPSHOTS"
}

object BuildConfig {
  val scalacOptions = Seq("-deprecation", "-unchecked", "-feature", "-encoding", "uft8")
  val envBuildNumber: String = env("BUILD_NUMBER")
  val buildNumber: String = if (envBuildNumber.nonEmpty) envBuildNumber else "0.0.1-SNAPSHOT"
  val buildWhen: String = (new java.util.Date).toString
  val buildMachine: String = java.net.InetAddress.getLocalHost.getHostName
  val artifactName = "bones"

  def env(key: String, default: String = ""): String = Option(System.getenv get key) getOrElse default
}
