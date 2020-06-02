name := "messagepackKafkaSerde"

version := "0.1"

scalaVersion := "2.13.1"

assemblyJarName in assembly := name.value + "-" + version.value + ".jar"

resolvers ++= Seq(
    "clojars"           at "https://clojars.org/repo/"
)

libraryDependencies ++= Seq(
    "org.msgpack"         %  "msgpack-core"        % "0.8.17" % Compile,
    "org.apache.kafka"    %% "kafka"               % "2.5.0"  % Compile,
    "org.apache.kafka"    %  "kafka-clients"       % "2.5.0"  % Compile
)

assemblyMergeStrategy in assembly := {
    case x if Assembly.isConfigFile(x) =>
        MergeStrategy.concat
    case PathList(ps @ _*) if Assembly.isReadme(ps.last) || Assembly.isLicenseFile(ps.last) =>
        MergeStrategy.rename
    case PathList("META-INF", xs @ _*) =>
        xs map {_.toLowerCase} match {
            case "manifest.mf" :: Nil | "index.list" :: Nil | "dependencies" :: Nil =>
                MergeStrategy.discard
            case ps @ x :: `xs` if ps.last.endsWith(".sf") || ps.last.endsWith(".dsa") =>
                MergeStrategy.discard
            case "plexus" :: `xs` =>
                MergeStrategy.discard
            case "services" :: `xs` =>
                MergeStrategy.filterDistinctLines
            case "spring.schemas" :: Nil | "spring.handlers" :: Nil =>
                MergeStrategy.filterDistinctLines
            case _ => MergeStrategy.first
        }
    case _ => MergeStrategy.first}