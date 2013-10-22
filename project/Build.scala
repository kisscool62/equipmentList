import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "equipmentList"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    javaCore,
    javaJdbc,
    javaEbean,
    "org.xhtmlrenderer" % "core-renderer" % "R8",
    "net.sf.jtidy" % "jtidy" % "r938"
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    resolvers += Resolver.url("Violas Play Modules", url("http://www.joergviola.de/releases/"))(Resolver.ivyStylePatterns)
  )

}
