reference : https://www.javainuse.com/messaging/kafka/intro

--------------------------------------------------------------------------------------------------------------------------------
Apache Kafka is an open-source stream-processing software platform developed using Java. 
Apache Kafka is a high throughput distributed messaging system for handling real-time data feeds.
--------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------
Apache Kafka Topic
Apache Kafka is a messaging system where messages are sent by producers and these messages are consumed by one or more consumers. 
Producers send the messages to Apache Kafka Topics. 
From the topics these messages are then consumed by the consumers. 
Topics have unique names which are used by producers and consumers for sending/consuming messages.
Topics are the base abstraction of where data lives within Kafka. 
They can be considered similar to the concept of table in a database. 
Each topic is backed by logs which are partitioned and distributed.

Producers --> Topic A --> Consumers
--------------------------------------------------------------------------------------------------------------------------------


--------------------------------------------------------------------------------------------------------------------------------
Apache Kafka Broker
The physical/virtual machines or servers where topics reside are called brokers. 
Kafka Broker is a software process that runs on machine. 
Broker has access to resources such as file systems where logs are maintained. 
A single topic can have multiple partitions running on different brokers.

              Broker
Producers --> Topic --> Consumers
--------------------------------------------------------------------------------------------------------------------------------


--------------------------------------------------------------------------------------------------------------------------------
We can add more brokers to scale the Kafka Application.

            Kafka Cluster
              Broker
Producers --> Topic --> Consumers
--------------------------------------------------------------------------------------------------------------------------------


--------------------------------------------------------------------------------------------------------------------------------
Apache Zookeeper
To manage the cluster we make use of Apache Zookeeper. 
Apache Zookeeper is a coordination service for distributed application that enables synchronization across a cluster. 
Zookeeper can be viewed as centralized repository where distributed applications can put data and get data out of it. 
It is used to keep the distributed system functioning together as a single unit, 
using its synchronization, serialization and coordination goals, selecting leader node.

             Zoo Keeper
            Kafka Cluster
              Broker
Producers --> Topic --> Consumers
--------------------------------------------------------------------------------------------------------------------------------


--------------------------------------------------------------------------------------------------------------------------------
instruction page : https://www.javainuse.com/misc/apache-kafka-hello-world
download page : https://kafka.apache.org/downloads

click on kafka_2.13-3.3.2.tgz under Binary downloads to download :

Binary downloads:
Scala 2.13  - kafka_2.13-3.3.2.tgz (asc, sha512)

after download, right click on the file then Open with Zip Extractor (twice)
C:\Users\donat\Downloads\kafka_2.13-3.3.2
    bin
    config
    libs
    licenses
    site-docs
    LICENSE
    NOTICE
--------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------
Apache Kafka Hello World 

reference : https://www.javainuse.com/misc/apache-kafka-hello-world

We will now start Apache Kafka-
This Kafka installation comes with an inbuilt zookeeper. 
Zookeeper is mainly used to track status of nodes present in Kafka cluster and also to keep track of Kafka topics, messages, etc.
Open a command prompt and start the Zookeeper-

C:\Users\donat\Downloads\kafka_2.13-3.3.2>.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
upon running error encountered :
C:\Users\donat\Downloads\kafka_2.13-3.3.2>.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
The input line is too long.
The syntax of the command is incorrect.

solution: https://stackoverflow.com/questions/16821784/input-line-is-too-long-error-in-bat-file
This usually happens due to long path. 
I have resolved this issue by replacing base path of Kafka from C:\Program Files<Kafka_path> to C:\Kafka

after cut from Downloads then paste to C:, the directory is now C:\kafka_2.13-3.3.2

C:\kafka_2.13-3.3.2>.\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
[2023-01-24 19:33:38,043] INFO Reading configuration from: .\config\zookeeper.properties (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2023-01-24 19:33:38,047] WARN \tmp\zookeeper is relative. Prepend .\ to indicate that you're sure! (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2023-01-24 19:33:38,051] INFO clientPortAddress is 0.0.0.0:2181 (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2023-01-24 19:33:38,051] INFO secureClientPort is not set (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2023-01-24 19:33:38,051] INFO observerMasterPort is not set (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2023-01-24 19:33:38,052] INFO metricsProvider.className is org.apache.zookeeper.metrics.impl.DefaultMetricsProvider (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2023-01-24 19:33:38,053] INFO autopurge.snapRetainCount set to 3 (org.apache.zookeeper.server.DatadirCleanupManager)
[2023-01-24 19:33:38,053] INFO autopurge.purgeInterval set to 0 (org.apache.zookeeper.server.DatadirCleanupManager)
[2023-01-24 19:33:38,053] INFO Purge task is not scheduled. (org.apache.zookeeper.server.DatadirCleanupManager)
[2023-01-24 19:33:38,053] WARN Either no config or no quorum defined in config, running in standalone mode (org.apache.zookeeper.server.quorum.QuorumPeerMain)
[2023-01-24 19:33:38,054] INFO Log4j 1.2 jmx support not found; jmx disabled. (org.apache.zookeeper.jmx.ManagedUtil)
[2023-01-24 19:33:38,055] INFO Reading configuration from: .\config\zookeeper.properties (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2023-01-24 19:33:38,056] WARN \tmp\zookeeper is relative. Prepend .\ to indicate that you're sure! (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2023-01-24 19:33:38,056] INFO clientPortAddress is 0.0.0.0:2181 (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2023-01-24 19:33:38,056] INFO secureClientPort is not set (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2023-01-24 19:33:38,056] INFO observerMasterPort is not set (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2023-01-24 19:33:38,056] INFO metricsProvider.className is org.apache.zookeeper.metrics.impl.DefaultMetricsProvider (org.apache.zookeeper.server.quorum.QuorumPeerConfig)
[2023-01-24 19:33:38,056] INFO Starting server (org.apache.zookeeper.server.ZooKeeperServerMain)
[2023-01-24 19:33:38,066] INFO ServerMetrics initialized with provider org.apache.zookeeper.metrics.impl.DefaultMetricsProvider@6293abcc (org.apache.zookeeper.server.ServerMetrics)
[2023-01-24 19:33:38,068] INFO zookeeper.snapshot.trust.empty : false (org.apache.zookeeper.server.persistence.FileTxnSnapLog)
[2023-01-24 19:33:38,093] INFO  (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:38,093] INFO   ______                  _                                           (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:38,094] INFO  |___  /                 | |                                          (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:38,094] INFO     / /    ___     ___   | | __   ___    ___   _ __     ___   _ __    (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:38,095] INFO    / /    / _ \   / _ \  | |/ /  / _ \  / _ \ | '_ \   / _ \ | '__| (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:38,095] INFO   / /__  | (_) | | (_) | |   <  |  __/ |  __/ | |_) | |  __/ | |     (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:38,096] INFO  /_____|  \___/   \___/  |_|\_\  \___|  \___| | .__/   \___| |_| (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:38,096] INFO                                               | |                      (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:38,097] INFO                                               |_|                      (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:38,097] INFO  (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,772] INFO Server environment:zookeeper.version=3.6.3--6401e4ad2087061bc6b9f80dec2d69f2e3c8660a, built on 04/08/2021 16:35 GMT (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,772] INFO Server environment:host.name=DESKTOP-ADOT1IA (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,774] INFO Server environment:java.version=15.0.1 (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,774] INFO Server environment:java.vendor=Oracle Corporation (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,774] INFO Server environment:java.home=C:\Program Files\Java\jdk-15.0.1 (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,775] INFO Server environment:java.class.path=C:\kafka_2.13-3.3.2\libs\activation-1.1.1.jar;C:\kafka_2.13-3.3.2\libs\aopalliance-repackaged-2.6.1.jar;C:\kafka_2.13-3.3.2\libs\argparse4j-0.7.0.jar;C:\kafka_2.13-3.3.2\libs\audience-annotations-0.5.0.jar;C:\kafka_2.13-3.3.2\libs\commons-cli-1.4.jar;C:\kafka_2.13-3.3.2\libs\commons-lang3-3.12.0.jar;C:\kafka_2.13-3.3.2\libs\commons-lang3-3.8.1.jar;C:\kafka_2.13-3.3.2\libs\connect-api-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\connect-basic-auth-extension-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\connect-file-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\connect-json-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\connect-mirror-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\connect-mirror-client-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\connect-runtime-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\connect-transforms-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\hk2-api-2.6.1.jar;C:\kafka_2.13-3.3.2\libs\hk2-locator-2.6.1.jar;C:\kafka_2.13-3.3.2\libs\hk2-utils-2.6.1.jar;C:\kafka_2.13-3.3.2\libs\jackson-annotations-2.13.4.jar;C:\kafka_2.13-3.3.2\libs\jackson-core-2.13.4.jar;C:\kafka_2.13-3.3.2\libs\jackson-databind-2.13.4.2.jar;C:\kafka_2.13-3.3.2\libs\jackson-dataformat-csv-2.13.4.jar;C:\kafka_2.13-3.3.2\libs\jackson-datatype-jdk8-2.13.4.jar;C:\kafka_2.13-3.3.2\libs\jackson-jaxrs-base-2.13.4.jar;C:\kafka_2.13-3.3.2\libs\jackson-jaxrs-json-provider-2.13.4.jar;C:\kafka_2.13-3.3.2\libs\jackson-module-jaxb-annotations-2.13.4.jar;C:\kafka_2.13-3.3.2\libs\jackson-module-scala_2.13-2.13.4.jar;C:\kafka_2.13-3.3.2\libs\jakarta.activation-api-1.2.2.jar;C:\kafka_2.13-3.3.2\libs\jakarta.annotation-api-1.3.5.jar;C:\kafka_2.13-3.3.2\libs\jakarta.inject-2.6.1.jar;C:\kafka_2.13-3.3.2\libs\jakarta.validation-api-2.0.2.jar;C:\kafka_2.13-3.3.2\libs\jakarta.ws.rs-api-2.1.6.jar;C:\kafka_2.13-3.3.2\libs\jakarta.xml.bind-api-2.3.3.jar;C:\kafka_2.13-3.3.2\libs\javassist-3.27.0-GA.jar;C:\kafka_2.13-3.3.2\libs\javax.servlet-api-3.1.0.jar;C:\kafka_2.13-3.3.2\libs\javax.ws.rs-api-2.1.1.jar;C:\kafka_2.13-3.3.2\libs\jaxb-api-2.3.0.jar;C:\kafka_2.13-3.3.2\libs\jersey-client-2.34.jar;C:\kafka_2.13-3.3.2\libs\jersey-common-2.34.jar;C:\kafka_2.13-3.3.2\libs\jersey-container-servlet-2.34.jar;C:\kafka_2.13-3.3.2\libs\jersey-container-servlet-core-2.34.jar;C:\kafka_2.13-3.3.2\libs\jersey-hk2-2.34.jar;C:\kafka_2.13-3.3.2\libs\jersey-server-2.34.jar;C:\kafka_2.13-3.3.2\libs\jetty-client-9.4.48.v20220622.jar;C:\kafka_2.13-3.3.2\libs\jetty-continuation-9.4.48.v20220622.jar;C:\kafka_2.13-3.3.2\libs\jetty-http-9.4.48.v20220622.jar;C:\kafka_2.13-3.3.2\libs\jetty-io-9.4.48.v20220622.jar;C:\kafka_2.13-3.3.2\libs\jetty-security-9.4.48.v20220622.jar;C:\kafka_2.13-3.3.2\libs\jetty-server-9.4.48.v20220622.jar;C:\kafka_2.13-3.3.2\libs\jetty-servlet-9.4.48.v20220622.jar;C:\kafka_2.13-3.3.2\libs\jetty-servlets-9.4.48.v20220622.jar;C:\kafka_2.13-3.3.2\libs\jetty-util-9.4.48.v20220622.jar;C:\kafka_2.13-3.3.2\libs\jetty-util-ajax-9.4.48.v20220622.jar;C:\kafka_2.13-3.3.2\libs\jline-3.21.0.jar;C:\kafka_2.13-3.3.2\libs\jopt-simple-5.0.4.jar;C:\kafka_2.13-3.3.2\libs\jose4j-0.7.9.jar;C:\kafka_2.13-3.3.2\libs\kafka-clients-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-log4j-appender-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-metadata-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-raft-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-server-common-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-shell-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-storage-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-storage-api-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-streams-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-streams-examples-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-streams-scala_2.13-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-streams-test-utils-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-tools-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka_2.13-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\lz4-java-1.8.0.jar;C:\kafka_2.13-3.3.2\libs\maven-artifact-3.8.4.jar;C:\kafka_2.13-3.3.2\libs\metrics-core-2.2.0.jar;C:\kafka_2.13-3.3.2\libs\metrics-core-4.1.12.1.jar;C:\kafka_2.13-3.3.2\libs\netty-buffer-4.1.78.Final.jar;C:\kafka_2.13-3.3.2\libs\netty-codec-4.1.78.Final.jar;C:\kafka_2.13-3.3.2\libs\netty-common-4.1.78.Final.jar;C:\kafka_2.13-3.3.2\libs\netty-handler-4.1.78.Final.jar;C:\kafka_2.13-3.3.2\libs\netty-resolver-4.1.78.Final.jar;C:\kafka_2.13-3.3.2\libs\netty-transport-4.1.78.Final.jar;C:\kafka_2.13-3.3.2\libs\netty-transport-classes-epoll-4.1.78.Final.jar;C:\kafka_2.13-3.3.2\libs\netty-transport-native-epoll-4.1.78.Final.jar;C:\kafka_2.13-3.3.2\libs\netty-transport-native-unix-common-4.1.78.Final.jar;C:\kafka_2.13-3.3.2\libs\osgi-resource-locator-1.0.3.jar;C:\kafka_2.13-3.3.2\libs\paranamer-2.8.jar;C:\kafka_2.13-3.3.2\libs\plexus-utils-3.3.0.jar;C:\kafka_2.13-3.3.2\libs\reflections-0.9.12.jar;C:\kafka_2.13-3.3.2\libs\reload4j-1.2.19.jar;C:\kafka_2.13-3.3.2\libs\rocksdbjni-7.1.2.jar;C:\kafka_2.13-3.3.2\libs\scala-collection-compat_2.13-2.6.0.jar;C:\kafka_2.13-3.3.2\libs\scala-java8-compat_2.13-1.0.2.jar;C:\kafka_2.13-3.3.2\libs\scala-library-2.13.8.jar;C:\kafka_2.13-3.3.2\libs\scala-logging_2.13-3.9.4.jar;C:\kafka_2.13-3.3.2\libs\scala-reflect-2.13.8.jar;C:\kafka_2.13-3.3.2\libs\slf4j-api-1.7.36.jar;C:\kafka_2.13-3.3.2\libs\slf4j-reload4j-1.7.36.jar;C:\kafka_2.13-3.3.2\libs\snappy-java-1.1.8.4.jar;C:\kafka_2.13-3.3.2\libs\swagger-annotations-2.2.0.jar;C:\kafka_2.13-3.3.2\libs\trogdor-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\zookeeper-3.6.3.jar;C:\kafka_2.13-3.3.2\libs\zookeeper-jute-3.6.3.jar;C:\kafka_2.13-3.3.2\libs\zstd-jni-1.5.2-1.jar (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,776] INFO Server environment:java.library.path=C:\Program Files\Java\jdk-15.0.1\bin;C:\WINDOWS\Sun\Java\bin;C:\WINDOWS\system32;C:\WINDOWS;C:\ProgramData\DockerDesktop\version-bin;C:\Program Files\Docker\Docker\Resources\bin;C:\Program Files\Common Files\Oracle\Java\javapath;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem;C:\WINDOWS\System32\WindowsPowerShell\v1.0\;C:\WINDOWS\System32\OpenSSH\;C:\Program Files\Java\jdk-15.0.1\bin;C:\Program Files\dotnet\;C:\Users\donat\AppData\Local\Programs\Python\Python38\Scripts;C:\Users\donat\AppData\Roaming\nvm;C:\Program Files\nodejs;C:\Program Files\MySQL\MySQL Shell 8.0\bin\;C:\Users\donat\AppData\Local\Microsoft\WindowsApps;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.3\bin;;C:\Users\donat\AppData\Roaming\npm;C:\Users\donat\AppData\Local\Programs\Microsoft VS Code\bin;C:\Users\donat\AppData\Local\Android\Sdk\platform-tools;C:\Users\donat\.dotnet\tools;C:\Users\donat\AppData\Roaming\nvm;C:\Program Files\nodejs;. (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,776] INFO Server environment:java.io.tmpdir=C:\Users\donat\AppData\Local\Temp\ (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,777] INFO Server environment:java.compiler=<NA> (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,777] INFO Server environment:os.name=Windows 10 (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,777] INFO Server environment:os.arch=amd64 (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,778] INFO Server environment:os.version=10.0 (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,779] INFO Server environment:user.name=donat (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,779] INFO Server environment:user.home=C:\Users\donat (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,779] INFO Server environment:user.dir=C:\kafka_2.13-3.3.2 (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,780] INFO Server environment:os.memory.free=494MB (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,780] INFO Server environment:os.memory.max=512MB (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,781] INFO Server environment:os.memory.total=512MB (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,781] INFO zookeeper.enableEagerACLCheck = false (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,782] INFO zookeeper.digest.enabled = true (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,782] INFO zookeeper.closeSessionTxn.enabled = true (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,782] INFO zookeeper.flushDelay=0 (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,783] INFO zookeeper.maxWriteQueuePollTime=0 (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,783] INFO zookeeper.maxBatchSize=1000 (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,783] INFO zookeeper.intBufferStartingSizeBytes = 1024 (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,785] INFO Weighed connection throttling is disabled (org.apache.zookeeper.server.BlueThrottle)
[2023-01-24 19:33:42,786] INFO minSessionTimeout set to 6000 (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,786] INFO maxSessionTimeout set to 60000 (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,787] INFO Response cache size is initialized with value 400. (org.apache.zookeeper.server.ResponseCache)
[2023-01-24 19:33:42,787] INFO Response cache size is initialized with value 400. (org.apache.zookeeper.server.ResponseCache)
[2023-01-24 19:33:42,788] INFO zookeeper.pathStats.slotCapacity = 60 (org.apache.zookeeper.server.util.RequestPathMetricsCollector)
[2023-01-24 19:33:42,789] INFO zookeeper.pathStats.slotDuration = 15 (org.apache.zookeeper.server.util.RequestPathMetricsCollector)
[2023-01-24 19:33:42,790] INFO zookeeper.pathStats.maxDepth = 6 (org.apache.zookeeper.server.util.RequestPathMetricsCollector)
[2023-01-24 19:33:42,790] INFO zookeeper.pathStats.initialDelay = 5 (org.apache.zookeeper.server.util.RequestPathMetricsCollector)
[2023-01-24 19:33:42,791] INFO zookeeper.pathStats.delay = 5 (org.apache.zookeeper.server.util.RequestPathMetricsCollector)
[2023-01-24 19:33:42,791] INFO zookeeper.pathStats.enabled = false (org.apache.zookeeper.server.util.RequestPathMetricsCollector)
[2023-01-24 19:33:42,793] INFO The max bytes for all large requests are set to 104857600 (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,793] INFO The large request threshold is set to -1 (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,794] INFO Created server with tickTime 3000 minSessionTimeout 6000 maxSessionTimeout 60000 clientPortListenBacklog -1 datadir \tmp\zookeeper\version-2 snapdir \tmp\zookeeper\version-2 (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,811] INFO Using org.apache.zookeeper.server.NIOServerCnxnFactory as server connection factory (org.apache.zookeeper.server.ServerCnxnFactory)
[2023-01-24 19:33:42,818] WARN maxCnxns is not configured, using default value 0. (org.apache.zookeeper.server.ServerCnxnFactory)
[2023-01-24 19:33:42,819] INFO Configuring NIO connection handler with 10s sessionless connection timeout, 2 selector thread(s), 24 worker threads, and 64 kB direct buffers. (org.apache.zookeeper.server.NIOServerCnxnFactory)
[2023-01-24 19:33:42,823] INFO binding to port 0.0.0.0/0.0.0.0:2181 (org.apache.zookeeper.server.NIOServerCnxnFactory)
[2023-01-24 19:33:42,835] INFO Using org.apache.zookeeper.server.watch.WatchManager as watch manager (org.apache.zookeeper.server.watch.WatchManagerFactory)
[2023-01-24 19:33:42,835] INFO Using org.apache.zookeeper.server.watch.WatchManager as watch manager (org.apache.zookeeper.server.watch.WatchManagerFactory)
[2023-01-24 19:33:42,837] INFO zookeeper.snapshotSizeFactor = 0.33 (org.apache.zookeeper.server.ZKDatabase)
[2023-01-24 19:33:42,837] INFO zookeeper.commitLogCount=500 (org.apache.zookeeper.server.ZKDatabase)
[2023-01-24 19:33:42,843] INFO zookeeper.snapshot.compression.method = CHECKED (org.apache.zookeeper.server.persistence.SnapStream)
[2023-01-24 19:33:42,843] INFO Snapshotting: 0x0 to \tmp\zookeeper\version-2\snapshot.0 (org.apache.zookeeper.server.persistence.FileTxnSnapLog)
[2023-01-24 19:33:42,848] INFO Snapshot loaded in 10 ms, highest zxid is 0x0, digest is 1371985504 (org.apache.zookeeper.server.ZKDatabase)
[2023-01-24 19:33:42,848] INFO Snapshotting: 0x0 to \tmp\zookeeper\version-2\snapshot.0 (org.apache.zookeeper.server.persistence.FileTxnSnapLog)
[2023-01-24 19:33:42,849] INFO Snapshot taken in 1 ms (org.apache.zookeeper.server.ZooKeeperServer)
[2023-01-24 19:33:42,870] INFO zookeeper.request_throttler.shutdownTimeout = 10000 (org.apache.zookeeper.server.RequestThrottler)
[2023-01-24 19:33:42,870] INFO PrepRequestProcessor (sid:0) started, reconfigEnabled=false (org.apache.zookeeper.server.PrepRequestProcessor)
[2023-01-24 19:33:42,883] INFO Using checkIntervalMs=60000 maxPerMinute=10000 maxNeverUsedIntervalMs=0 (org.apache.zookeeper.server.ContainerManager)
[2023-01-24 19:33:42,884] INFO ZooKeeper audit is disabled. (org.apache.zookeeper.audit.ZKAuditProvider)
--------------------------------------------------------------------------------------------------------------------------------




--------------------------------------------------------------------------------------------------------------------------------
Open a new command prompt and start the Apache Kafka-

C:\kafka_2.13-3.3.2>.\bin\windows\kafka-server-start.bat .\config\server.properties
[2023-01-24 19:36:11,957] INFO Registered kafka:type=kafka.Log4jController MBean (kafka.utils.Log4jControllerRegistration$)
[2023-01-24 19:36:12,206] INFO Setting -D jdk.tls.rejectClientInitiatedRenegotiation=true to disable client-initiated TLS renegotiation (org.apache.zookeeper.common.X509Util)
[2023-01-24 19:36:12,275] INFO starting (kafka.server.KafkaServer)
[2023-01-24 19:36:12,276] INFO Connecting to zookeeper on localhost:2181 (kafka.server.KafkaServer)
[2023-01-24 19:36:12,287] INFO [ZooKeeperClient Kafka server] Initializing a new session to localhost:2181. (kafka.zookeeper.ZooKeeperClient)
[2023-01-24 19:36:16,830] INFO Client environment:zookeeper.version=3.6.3--6401e4ad2087061bc6b9f80dec2d69f2e3c8660a, built on 04/08/2021 16:35 GMT (org.apache.zookeeper.ZooKeeper)
[2023-01-24 19:36:16,830] INFO Client environment:host.name=DESKTOP-ADOT1IA (org.apache.zookeeper.ZooKeeper)
[2023-01-24 19:36:16,830] INFO Client environment:java.version=15.0.1 (org.apache.zookeeper.ZooKeeper)
[2023-01-24 19:36:16,830] INFO Client environment:java.vendor=Oracle Corporation (org.apache.zookeeper.ZooKeeper)
[2023-01-24 19:36:16,831] INFO Client environment:java.home=C:\Program Files\Java\jdk-15.0.1 (org.apache.zookeeper.ZooKeeper)
[2023-01-24 19:36:16,831] INFO Client environment:java.class.path=C:\kafka_2.13-3.3.2\libs\activation-1.1.1.jar;C:\kafka_2.13-3.3.2\libs\aopalliance-repackaged-2.6.1.jar;C:\kafka_2.13-3.3.2\libs\argparse4j-0.7.0.jar;C:\kafka_2.13-3.3.2\libs\audience-annotations-0.5.0.jar;C:\kafka_2.13-3.3.2\libs\commons-cli-1.4.jar;C:\kafka_2.13-3.3.2\libs\commons-lang3-3.12.0.jar;C:\kafka_2.13-3.3.2\libs\commons-lang3-3.8.1.jar;C:\kafka_2.13-3.3.2\libs\connect-api-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\connect-basic-auth-extension-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\connect-file-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\connect-json-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\connect-mirror-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\connect-mirror-client-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\connect-runtime-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\connect-transforms-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\hk2-api-2.6.1.jar;C:\kafka_2.13-3.3.2\libs\hk2-locator-2.6.1.jar;C:\kafka_2.13-3.3.2\libs\hk2-utils-2.6.1.jar;C:\kafka_2.13-3.3.2\libs\jackson-annotations-2.13.4.jar;C:\kafka_2.13-3.3.2\libs\jackson-core-2.13.4.jar;C:\kafka_2.13-3.3.2\libs\jackson-databind-2.13.4.2.jar;C:\kafka_2.13-3.3.2\libs\jackson-dataformat-csv-2.13.4.jar;C:\kafka_2.13-3.3.2\libs\jackson-datatype-jdk8-2.13.4.jar;C:\kafka_2.13-3.3.2\libs\jackson-jaxrs-base-2.13.4.jar;C:\kafka_2.13-3.3.2\libs\jackson-jaxrs-json-provider-2.13.4.jar;C:\kafka_2.13-3.3.2\libs\jackson-module-jaxb-annotations-2.13.4.jar;C:\kafka_2.13-3.3.2\libs\jackson-module-scala_2.13-2.13.4.jar;C:\kafka_2.13-3.3.2\libs\jakarta.activation-api-1.2.2.jar;C:\kafka_2.13-3.3.2\libs\jakarta.annotation-api-1.3.5.jar;C:\kafka_2.13-3.3.2\libs\jakarta.inject-2.6.1.jar;C:\kafka_2.13-3.3.2\libs\jakarta.validation-api-2.0.2.jar;C:\kafka_2.13-3.3.2\libs\jakarta.ws.rs-api-2.1.6.jar;C:\kafka_2.13-3.3.2\libs\jakarta.xml.bind-api-2.3.3.jar;C:\kafka_2.13-3.3.2\libs\javassist-3.27.0-GA.jar;C:\kafka_2.13-3.3.2\libs\javax.servlet-api-3.1.0.jar;C:\kafka_2.13-3.3.2\libs\javax.ws.rs-api-2.1.1.jar;C:\kafka_2.13-3.3.2\libs\jaxb-api-2.3.0.jar;C:\kafka_2.13-3.3.2\libs\jersey-client-2.34.jar;C:\kafka_2.13-3.3.2\libs\jersey-common-2.34.jar;C:\kafka_2.13-3.3.2\libs\jersey-container-servlet-2.34.jar;C:\kafka_2.13-3.3.2\libs\jersey-container-servlet-core-2.34.jar;C:\kafka_2.13-3.3.2\libs\jersey-hk2-2.34.jar;C:\kafka_2.13-3.3.2\libs\jersey-server-2.34.jar;C:\kafka_2.13-3.3.2\libs\jetty-client-9.4.48.v20220622.jar;C:\kafka_2.13-3.3.2\libs\jetty-continuation-9.4.48.v20220622.jar;C:\kafka_2.13-3.3.2\libs\jetty-http-9.4.48.v20220622.jar;C:\kafka_2.13-3.3.2\libs\jetty-io-9.4.48.v20220622.jar;C:\kafka_2.13-3.3.2\libs\jetty-security-9.4.48.v20220622.jar;C:\kafka_2.13-3.3.2\libs\jetty-server-9.4.48.v20220622.jar;C:\kafka_2.13-3.3.2\libs\jetty-servlet-9.4.48.v20220622.jar;C:\kafka_2.13-3.3.2\libs\jetty-servlets-9.4.48.v20220622.jar;C:\kafka_2.13-3.3.2\libs\jetty-util-9.4.48.v20220622.jar;C:\kafka_2.13-3.3.2\libs\jetty-util-ajax-9.4.48.v20220622.jar;C:\kafka_2.13-3.3.2\libs\jline-3.21.0.jar;C:\kafka_2.13-3.3.2\libs\jopt-simple-5.0.4.jar;C:\kafka_2.13-3.3.2\libs\jose4j-0.7.9.jar;C:\kafka_2.13-3.3.2\libs\kafka-clients-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-log4j-appender-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-metadata-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-raft-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-server-common-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-shell-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-storage-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-storage-api-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-streams-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-streams-examples-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-streams-scala_2.13-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-streams-test-utils-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka-tools-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\kafka_2.13-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\lz4-java-1.8.0.jar;C:\kafka_2.13-3.3.2\libs\maven-artifact-3.8.4.jar;C:\kafka_2.13-3.3.2\libs\metrics-core-2.2.0.jar;C:\kafka_2.13-3.3.2\libs\metrics-core-4.1.12.1.jar;C:\kafka_2.13-3.3.2\libs\netty-buffer-4.1.78.Final.jar;C:\kafka_2.13-3.3.2\libs\netty-codec-4.1.78.Final.jar;C:\kafka_2.13-3.3.2\libs\netty-common-4.1.78.Final.jar;C:\kafka_2.13-3.3.2\libs\netty-handler-4.1.78.Final.jar;C:\kafka_2.13-3.3.2\libs\netty-resolver-4.1.78.Final.jar;C:\kafka_2.13-3.3.2\libs\netty-transport-4.1.78.Final.jar;C:\kafka_2.13-3.3.2\libs\netty-transport-classes-epoll-4.1.78.Final.jar;C:\kafka_2.13-3.3.2\libs\netty-transport-native-epoll-4.1.78.Final.jar;C:\kafka_2.13-3.3.2\libs\netty-transport-native-unix-common-4.1.78.Final.jar;C:\kafka_2.13-3.3.2\libs\osgi-resource-locator-1.0.3.jar;C:\kafka_2.13-3.3.2\libs\paranamer-2.8.jar;C:\kafka_2.13-3.3.2\libs\plexus-utils-3.3.0.jar;C:\kafka_2.13-3.3.2\libs\reflections-0.9.12.jar;C:\kafka_2.13-3.3.2\libs\reload4j-1.2.19.jar;C:\kafka_2.13-3.3.2\libs\rocksdbjni-7.1.2.jar;C:\kafka_2.13-3.3.2\libs\scala-collection-compat_2.13-2.6.0.jar;C:\kafka_2.13-3.3.2\libs\scala-java8-compat_2.13-1.0.2.jar;C:\kafka_2.13-3.3.2\libs\scala-library-2.13.8.jar;C:\kafka_2.13-3.3.2\libs\scala-logging_2.13-3.9.4.jar;C:\kafka_2.13-3.3.2\libs\scala-reflect-2.13.8.jar;C:\kafka_2.13-3.3.2\libs\slf4j-api-1.7.36.jar;C:\kafka_2.13-3.3.2\libs\slf4j-reload4j-1.7.36.jar;C:\kafka_2.13-3.3.2\libs\snappy-java-1.1.8.4.jar;C:\kafka_2.13-3.3.2\libs\swagger-annotations-2.2.0.jar;C:\kafka_2.13-3.3.2\libs\trogdor-3.3.2.jar;C:\kafka_2.13-3.3.2\libs\zookeeper-3.6.3.jar;C:\kafka_2.13-3.3.2\libs\zookeeper-jute-3.6.3.jar;C:\kafka_2.13-3.3.2\libs\zstd-jni-1.5.2-1.jar (org.apache.zookeeper.ZooKeeper)
[2023-01-24 19:36:16,832] INFO Client environment:java.library.path=C:\Program Files\Java\jdk-15.0.1\bin;C:\WINDOWS\Sun\Java\bin;C:\WINDOWS\system32;C:\WINDOWS;C:\ProgramData\DockerDesktop\version-bin;C:\Program Files\Docker\Docker\Resources\bin;C:\Program Files\Common Files\Oracle\Java\javapath;C:\WINDOWS\system32;C:\WINDOWS;C:\WINDOWS\System32\Wbem;C:\WINDOWS\System32\WindowsPowerShell\v1.0\;C:\WINDOWS\System32\OpenSSH\;C:\Program Files\Java\jdk-15.0.1\bin;C:\Program Files\dotnet\;C:\Users\donat\AppData\Local\Programs\Python\Python38\Scripts;C:\Users\donat\AppData\Roaming\nvm;C:\Program Files\nodejs;C:\Program Files\MySQL\MySQL Shell 8.0\bin\;C:\Users\donat\AppData\Local\Microsoft\WindowsApps;C:\Program Files\JetBrains\IntelliJ IDEA Community Edition 2020.3\bin;;C:\Users\donat\AppData\Roaming\npm;C:\Users\donat\AppData\Local\Programs\Microsoft VS Code\bin;C:\Users\donat\AppData\Local\Android\Sdk\platform-tools;C:\Users\donat\.dotnet\tools;C:\Users\donat\AppData\Roaming\nvm;C:\Program Files\nodejs;. (org.apache.zookeeper.ZooKeeper)
[2023-01-24 19:36:16,833] INFO Client environment:java.io.tmpdir=C:\Users\donat\AppData\Local\Temp\ (org.apache.zookeeper.ZooKeeper)
[2023-01-24 19:36:16,833] INFO Client environment:java.compiler=<NA> (org.apache.zookeeper.ZooKeeper)
[2023-01-24 19:36:16,834] INFO Client environment:os.name=Windows 10 (org.apache.zookeeper.ZooKeeper)
[2023-01-24 19:36:16,834] INFO Client environment:os.arch=amd64 (org.apache.zookeeper.ZooKeeper)
[2023-01-24 19:36:16,835] INFO Client environment:os.version=10.0 (org.apache.zookeeper.ZooKeeper)
[2023-01-24 19:36:16,835] INFO Client environment:user.name=donat (org.apache.zookeeper.ZooKeeper)
[2023-01-24 19:36:16,836] INFO Client environment:user.home=C:\Users\donat (org.apache.zookeeper.ZooKeeper)
[2023-01-24 19:36:16,837] INFO Client environment:user.dir=C:\kafka_2.13-3.3.2 (org.apache.zookeeper.ZooKeeper)
[2023-01-24 19:36:16,837] INFO Client environment:os.memory.free=986MB (org.apache.zookeeper.ZooKeeper)
[2023-01-24 19:36:16,838] INFO Client environment:os.memory.max=1024MB (org.apache.zookeeper.ZooKeeper)
[2023-01-24 19:36:16,838] INFO Client environment:os.memory.total=1024MB (org.apache.zookeeper.ZooKeeper)
[2023-01-24 19:36:16,841] INFO Initiating client connection, connectString=localhost:2181 sessionTimeout=18000 watcher=kafka.zookeeper.ZooKeeperClient$ZooKeeperClientWatcher$@1f81aa00 (org.apache.zookeeper.ZooKeeper)
[2023-01-24 19:36:16,855] INFO jute.maxbuffer value is 4194304 Bytes (org.apache.zookeeper.ClientCnxnSocket)
[2023-01-24 19:36:16,859] INFO zookeeper.request.timeout value is 0. feature enabled=false (org.apache.zookeeper.ClientCnxn)
[2023-01-24 19:36:16,861] INFO [ZooKeeperClient Kafka server] Waiting until connected. (kafka.zookeeper.ZooKeeperClient)
[2023-01-24 19:36:16,861] INFO Opening socket connection to server localhost/[0:0:0:0:0:0:0:1]:2181. (org.apache.zookeeper.ClientCnxn)
[2023-01-24 19:36:16,863] INFO Socket connection established, initiating session, client: /[0:0:0:0:0:0:0:1]:56715, server: localhost/[0:0:0:0:0:0:0:1]:2181 (org.apache.zookeeper.ClientCnxn)
[2023-01-24 19:36:16,892] INFO Session establishment complete on server localhost/[0:0:0:0:0:0:0:1]:2181, session id = 0x1004489d2330000, negotiated timeout = 18000 (org.apache.zookeeper.ClientCnxn)
[2023-01-24 19:36:16,895] INFO [ZooKeeperClient Kafka server] Connected. (kafka.zookeeper.ZooKeeperClient)
[2023-01-24 19:36:17,115] INFO Cluster ID = Hj3eUexIS6qTmKwVchjqaQ (kafka.server.KafkaServer)
[2023-01-24 19:36:17,118] WARN No meta.properties file under dir C:\tmp\kafka-logs\meta.properties (kafka.server.BrokerMetadataCheckpoint)
[2023-01-24 19:36:17,155] INFO KafkaConfig values:
        advertised.listeners = null
        alter.config.policy.class.name = null
        alter.log.dirs.replication.quota.window.num = 11
        alter.log.dirs.replication.quota.window.size.seconds = 1
        authorizer.class.name =
        auto.create.topics.enable = true
        auto.leader.rebalance.enable = true
        background.threads = 10
        broker.heartbeat.interval.ms = 2000
        broker.id = 0
        broker.id.generation.enable = true
        broker.rack = null
        broker.session.timeout.ms = 9000
        client.quota.callback.class = null
        compression.type = producer
        connection.failed.authentication.delay.ms = 100
        connections.max.idle.ms = 600000
        connections.max.reauth.ms = 0
        control.plane.listener.name = null
        controlled.shutdown.enable = true
        controlled.shutdown.max.retries = 3
        controlled.shutdown.retry.backoff.ms = 5000
        controller.listener.names = null
        controller.quorum.append.linger.ms = 25
        controller.quorum.election.backoff.max.ms = 1000
        controller.quorum.election.timeout.ms = 1000
        controller.quorum.fetch.timeout.ms = 2000
        controller.quorum.request.timeout.ms = 2000
        controller.quorum.retry.backoff.ms = 20
        controller.quorum.voters = []
        controller.quota.window.num = 11
        controller.quota.window.size.seconds = 1
        controller.socket.timeout.ms = 30000
        create.topic.policy.class.name = null
        default.replication.factor = 1
        delegation.token.expiry.check.interval.ms = 3600000
        delegation.token.expiry.time.ms = 86400000
        delegation.token.master.key = null
        delegation.token.max.lifetime.ms = 604800000
        delegation.token.secret.key = null
        delete.records.purgatory.purge.interval.requests = 1
        delete.topic.enable = true
        early.start.listeners = null
        fetch.max.bytes = 57671680
        fetch.purgatory.purge.interval.requests = 1000
        group.initial.rebalance.delay.ms = 0
        group.max.session.timeout.ms = 1800000
        group.max.size = 2147483647
        group.min.session.timeout.ms = 6000
        initial.broker.registration.timeout.ms = 60000
        inter.broker.listener.name = null
        inter.broker.protocol.version = 3.3-IV3
        kafka.metrics.polling.interval.secs = 10
        kafka.metrics.reporters = []
        leader.imbalance.check.interval.seconds = 300
        leader.imbalance.per.broker.percentage = 10
        listener.security.protocol.map = PLAINTEXT:PLAINTEXT,SSL:SSL,SASL_PLAINTEXT:SASL_PLAINTEXT,SASL_SSL:SASL_SSL
        listeners = PLAINTEXT://:9092
        log.cleaner.backoff.ms = 15000
        log.cleaner.dedupe.buffer.size = 134217728
        log.cleaner.delete.retention.ms = 86400000
        log.cleaner.enable = true
        log.cleaner.io.buffer.load.factor = 0.9
        log.cleaner.io.buffer.size = 524288
        log.cleaner.io.max.bytes.per.second = 1.7976931348623157E308
        log.cleaner.max.compaction.lag.ms = 9223372036854775807
        log.cleaner.min.cleanable.ratio = 0.5
        log.cleaner.min.compaction.lag.ms = 0
        log.cleaner.threads = 1
        log.cleanup.policy = [delete]
        log.dir = /tmp/kafka-logs
        log.dirs = /tmp/kafka-logs
        log.flush.interval.messages = 9223372036854775807
        log.flush.interval.ms = null
        log.flush.offset.checkpoint.interval.ms = 60000
        log.flush.scheduler.interval.ms = 9223372036854775807
        log.flush.start.offset.checkpoint.interval.ms = 60000
        log.index.interval.bytes = 4096
        log.index.size.max.bytes = 10485760
        log.message.downconversion.enable = true
        log.message.format.version = 3.0-IV1
        log.message.timestamp.difference.max.ms = 9223372036854775807
        log.message.timestamp.type = CreateTime
        log.preallocate = false
        log.retention.bytes = -1
        log.retention.check.interval.ms = 300000
        log.retention.hours = 168
        log.retention.minutes = null
        log.retention.ms = null
        log.roll.hours = 168
        log.roll.jitter.hours = 0
        log.roll.jitter.ms = null
        log.roll.ms = null
        log.segment.bytes = 1073741824
        log.segment.delete.delay.ms = 60000
        max.connection.creation.rate = 2147483647
        max.connections = 2147483647
        max.connections.per.ip = 2147483647
        max.connections.per.ip.overrides =
        max.incremental.fetch.session.cache.slots = 1000
        message.max.bytes = 1048588
        metadata.log.dir = null
        metadata.log.max.record.bytes.between.snapshots = 20971520
        metadata.log.segment.bytes = 1073741824
        metadata.log.segment.min.bytes = 8388608
        metadata.log.segment.ms = 604800000
        metadata.max.idle.interval.ms = 500
        metadata.max.retention.bytes = -1
        metadata.max.retention.ms = 604800000
        metric.reporters = []
        metrics.num.samples = 2
        metrics.recording.level = INFO
        metrics.sample.window.ms = 30000
        min.insync.replicas = 1
        node.id = 0
        num.io.threads = 8
        num.network.threads = 3
        num.partitions = 1
        num.recovery.threads.per.data.dir = 1
        num.replica.alter.log.dirs.threads = null
        num.replica.fetchers = 1
        offset.metadata.max.bytes = 4096
        offsets.commit.required.acks = -1
        offsets.commit.timeout.ms = 5000
        offsets.load.buffer.size = 5242880
        offsets.retention.check.interval.ms = 600000
        offsets.retention.minutes = 10080
        offsets.topic.compression.codec = 0
        offsets.topic.num.partitions = 50
        offsets.topic.replication.factor = 1
        offsets.topic.segment.bytes = 104857600
        password.encoder.cipher.algorithm = AES/CBC/PKCS5Padding
        password.encoder.iterations = 4096
        password.encoder.key.length = 128
        password.encoder.keyfactory.algorithm = null
        password.encoder.old.secret = null
        password.encoder.secret = null
        principal.builder.class = class org.apache.kafka.common.security.authenticator.DefaultKafkaPrincipalBuilder
        process.roles = []
        producer.purgatory.purge.interval.requests = 1000
        queued.max.request.bytes = -1
        queued.max.requests = 500
        quota.window.num = 11
        quota.window.size.seconds = 1
        remote.log.index.file.cache.total.size.bytes = 1073741824
        remote.log.manager.task.interval.ms = 30000
        remote.log.manager.task.retry.backoff.max.ms = 30000
        remote.log.manager.task.retry.backoff.ms = 500
        remote.log.manager.task.retry.jitter = 0.2
        remote.log.manager.thread.pool.size = 10
        remote.log.metadata.manager.class.name = null
        remote.log.metadata.manager.class.path = null
        remote.log.metadata.manager.impl.prefix = null
        remote.log.metadata.manager.listener.name = null
        remote.log.reader.max.pending.tasks = 100
        remote.log.reader.threads = 10
        remote.log.storage.manager.class.name = null
        remote.log.storage.manager.class.path = null
        remote.log.storage.manager.impl.prefix = null
        remote.log.storage.system.enable = false
        replica.fetch.backoff.ms = 1000
        replica.fetch.max.bytes = 1048576
        replica.fetch.min.bytes = 1
        replica.fetch.response.max.bytes = 10485760
        replica.fetch.wait.max.ms = 500
        replica.high.watermark.checkpoint.interval.ms = 5000
        replica.lag.time.max.ms = 30000
        replica.selector.class = null
        replica.socket.receive.buffer.bytes = 65536
        replica.socket.timeout.ms = 30000
        replication.quota.window.num = 11
        replication.quota.window.size.seconds = 1
        request.timeout.ms = 30000
        reserved.broker.max.id = 1000
        sasl.client.callback.handler.class = null
        sasl.enabled.mechanisms = [GSSAPI]
        sasl.jaas.config = null
        sasl.kerberos.kinit.cmd = /usr/bin/kinit
        sasl.kerberos.min.time.before.relogin = 60000
        sasl.kerberos.principal.to.local.rules = [DEFAULT]
        sasl.kerberos.service.name = null
        sasl.kerberos.ticket.renew.jitter = 0.05
        sasl.kerberos.ticket.renew.window.factor = 0.8
        sasl.login.callback.handler.class = null
        sasl.login.class = null
        sasl.login.connect.timeout.ms = null
        sasl.login.read.timeout.ms = null
        sasl.login.refresh.buffer.seconds = 300
        sasl.login.refresh.min.period.seconds = 60
        sasl.login.refresh.window.factor = 0.8
        sasl.login.refresh.window.jitter = 0.05
        sasl.login.retry.backoff.max.ms = 10000
        sasl.login.retry.backoff.ms = 100
        sasl.mechanism.controller.protocol = GSSAPI
        sasl.mechanism.inter.broker.protocol = GSSAPI
        sasl.oauthbearer.clock.skew.seconds = 30
        sasl.oauthbearer.expected.audience = null
        sasl.oauthbearer.expected.issuer = null
        sasl.oauthbearer.jwks.endpoint.refresh.ms = 3600000
        sasl.oauthbearer.jwks.endpoint.retry.backoff.max.ms = 10000
        sasl.oauthbearer.jwks.endpoint.retry.backoff.ms = 100
        sasl.oauthbearer.jwks.endpoint.url = null
        sasl.oauthbearer.scope.claim.name = scope
        sasl.oauthbearer.sub.claim.name = sub
        sasl.oauthbearer.token.endpoint.url = null
        sasl.server.callback.handler.class = null
        sasl.server.max.receive.size = 524288
        security.inter.broker.protocol = PLAINTEXT
        security.providers = null
        socket.connection.setup.timeout.max.ms = 30000
        socket.connection.setup.timeout.ms = 10000
        socket.listen.backlog.size = 50
        socket.receive.buffer.bytes = 102400
        socket.request.max.bytes = 104857600
        socket.send.buffer.bytes = 102400
        ssl.cipher.suites = []
        ssl.client.auth = none
        ssl.enabled.protocols = [TLSv1.2, TLSv1.3]
        ssl.endpoint.identification.algorithm = https
        ssl.engine.factory.class = null
        ssl.key.password = null
        ssl.keymanager.algorithm = SunX509
        ssl.keystore.certificate.chain = null
        ssl.keystore.key = null
        ssl.keystore.location = null
        ssl.keystore.password = null
        ssl.keystore.type = JKS
        ssl.principal.mapping.rules = DEFAULT
        ssl.protocol = TLSv1.3
        ssl.provider = null
        ssl.secure.random.implementation = null
        ssl.trustmanager.algorithm = PKIX
        ssl.truststore.certificates = null
        ssl.truststore.location = null
        ssl.truststore.password = null
        ssl.truststore.type = JKS
        transaction.abort.timed.out.transaction.cleanup.interval.ms = 10000
        transaction.max.timeout.ms = 900000
        transaction.remove.expired.transaction.cleanup.interval.ms = 3600000
        transaction.state.log.load.buffer.size = 5242880
        transaction.state.log.min.isr = 1
        transaction.state.log.num.partitions = 50
        transaction.state.log.replication.factor = 1
        transaction.state.log.segment.bytes = 104857600
        transactional.id.expiration.ms = 604800000
        unclean.leader.election.enable = false
        zookeeper.clientCnxnSocket = null
        zookeeper.connect = localhost:2181
        zookeeper.connection.timeout.ms = 18000
        zookeeper.max.in.flight.requests = 10
        zookeeper.session.timeout.ms = 18000
        zookeeper.set.acl = false
        zookeeper.ssl.cipher.suites = null
        zookeeper.ssl.client.enable = false
        zookeeper.ssl.crl.enable = false
        zookeeper.ssl.enabled.protocols = null
        zookeeper.ssl.endpoint.identification.algorithm = HTTPS
        zookeeper.ssl.keystore.location = null
        zookeeper.ssl.keystore.password = null
        zookeeper.ssl.keystore.type = null
        zookeeper.ssl.ocsp.enable = false
        zookeeper.ssl.protocol = TLSv1.2
        zookeeper.ssl.truststore.location = null
        zookeeper.ssl.truststore.password = null
        zookeeper.ssl.truststore.type = null
 (kafka.server.KafkaConfig)
[2023-01-24 19:36:17,206] INFO [ThrottledChannelReaper-Fetch]: Starting (kafka.server.ClientQuotaManager$ThrottledChannelReaper)
[2023-01-24 19:36:17,206] INFO [ThrottledChannelReaper-Produce]: Starting (kafka.server.ClientQuotaManager$ThrottledChannelReaper)
[2023-01-24 19:36:17,207] INFO [ThrottledChannelReaper-Request]: Starting (kafka.server.ClientQuotaManager$ThrottledChannelReaper)
[2023-01-24 19:36:17,210] INFO [ThrottledChannelReaper-ControllerMutation]: Starting (kafka.server.ClientQuotaManager$ThrottledChannelReaper)
[2023-01-24 19:36:17,226] INFO Log directory C:\tmp\kafka-logs not found, creating it. (kafka.log.LogManager)
[2023-01-24 19:36:17,254] INFO Loading logs from log dirs ArraySeq(C:\tmp\kafka-logs) (kafka.log.LogManager)
[2023-01-24 19:36:17,257] INFO Attempting recovery for all logs in C:\tmp\kafka-logs since no clean shutdown file was found (kafka.log.LogManager)
[2023-01-24 19:36:17,276] INFO Loaded 0 logs in 21ms. (kafka.log.LogManager)
[2023-01-24 19:36:17,277] INFO Starting log cleanup with a period of 300000 ms. (kafka.log.LogManager)
[2023-01-24 19:36:17,280] INFO Starting log flusher with a default period of 9223372036854775807 ms. (kafka.log.LogManager)
[2023-01-24 19:36:17,339] INFO [feature-zk-node-event-process-thread]: Starting (kafka.server.FinalizedFeatureChangeListener$ChangeNotificationProcessorThread)
[2023-01-24 19:36:17,347] INFO Feature ZK node at path: /feature does not exist (kafka.server.FinalizedFeatureChangeListener)
[2023-01-24 19:36:17,370] INFO [BrokerToControllerChannelManager broker=0 name=forwarding]: Starting (kafka.server.BrokerToControllerRequestThread)
[2023-01-24 19:36:17,651] INFO Updated connection-accept-rate max connection creation rate to 2147483647 (kafka.network.ConnectionQuotas)
[2023-01-24 19:36:17,655] INFO Awaiting socket connections on 0.0.0.0:9092. (kafka.network.DataPlaneAcceptor)
[2023-01-24 19:36:17,689] INFO [SocketServer listenerType=ZK_BROKER, nodeId=0] Created data-plane acceptor and processors for endpoint : ListenerName(PLAINTEXT) (kafka.network.SocketServer)
[2023-01-24 19:36:17,698] INFO [BrokerToControllerChannelManager broker=0 name=alterPartition]: Starting (kafka.server.BrokerToControllerRequestThread)
[2023-01-24 19:36:17,722] INFO [ExpirationReaper-0-Produce]: Starting (kafka.server.DelayedOperationPurgatory$ExpiredOperationReaper)
[2023-01-24 19:36:17,723] INFO [ExpirationReaper-0-Fetch]: Starting (kafka.server.DelayedOperationPurgatory$ExpiredOperationReaper)
[2023-01-24 19:36:17,724] INFO [ExpirationReaper-0-DeleteRecords]: Starting (kafka.server.DelayedOperationPurgatory$ExpiredOperationReaper)
[2023-01-24 19:36:17,725] INFO [ExpirationReaper-0-ElectLeader]: Starting (kafka.server.DelayedOperationPurgatory$ExpiredOperationReaper)
[2023-01-24 19:36:17,737] INFO [LogDirFailureHandler]: Starting (kafka.server.ReplicaManager$LogDirFailureHandler)
[2023-01-24 19:36:22,300] INFO Creating /brokers/ids/0 (is it secure? false) (kafka.zk.KafkaZkClient)
[2023-01-24 19:36:22,325] INFO Stat of the created znode at /brokers/ids/0 is: 25,25,1674560182312,1674560182312,1,0,0,72132952765693952,214,0,25
 (kafka.zk.KafkaZkClient)
[2023-01-24 19:36:22,326] INFO Registered broker 0 at path /brokers/ids/0 with addresses: PLAINTEXT://DESKTOP-ADOT1IA:9092, czxid (broker epoch): 25 (kafka.zk.KafkaZkClient)
[2023-01-24 19:36:22,374] INFO [ExpirationReaper-0-topic]: Starting (kafka.server.DelayedOperationPurgatory$ExpiredOperationReaper)
[2023-01-24 19:36:22,377] INFO [ExpirationReaper-0-Heartbeat]: Starting (kafka.server.DelayedOperationPurgatory$ExpiredOperationReaper)
[2023-01-24 19:36:22,378] INFO [ExpirationReaper-0-Rebalance]: Starting (kafka.server.DelayedOperationPurgatory$ExpiredOperationReaper)
[2023-01-24 19:36:22,383] INFO Successfully created /controller_epoch with initial epoch 0 (kafka.zk.KafkaZkClient)
[2023-01-24 19:36:22,390] INFO [GroupCoordinator 0]: Starting up. (kafka.coordinator.group.GroupCoordinator)
[2023-01-24 19:36:22,394] INFO [GroupCoordinator 0]: Startup complete. (kafka.coordinator.group.GroupCoordinator)
[2023-01-24 19:36:22,397] INFO Feature ZK node created at path: /feature (kafka.server.FinalizedFeatureChangeListener)
[2023-01-24 19:36:22,411] INFO [TransactionCoordinator id=0] Starting up. (kafka.coordinator.transaction.TransactionCoordinator)
[2023-01-24 19:36:22,414] INFO [Transaction Marker Channel Manager 0]: Starting (kafka.coordinator.transaction.TransactionMarkerChannelManager)
[2023-01-24 19:36:22,414] INFO [TransactionCoordinator id=0] Startup complete. (kafka.coordinator.transaction.TransactionCoordinator)
[2023-01-24 19:36:22,417] INFO [MetadataCache brokerId=0] Updated cache from existing <empty> to latest FinalizedFeaturesAndEpoch(features=Map(), epoch=0). (kafka.server.metadata.ZkMetadataCache)
[2023-01-24 19:36:22,446] INFO [ExpirationReaper-0-AlterAcls]: Starting (kafka.server.DelayedOperationPurgatory$ExpiredOperationReaper)
[2023-01-24 19:36:22,466] INFO [/config/changes-event-process-thread]: Starting (kafka.common.ZkNodeChangeNotificationListener$ChangeEventProcessThread)
[2023-01-24 19:36:22,476] INFO [SocketServer listenerType=ZK_BROKER, nodeId=0] Enabling request processing. (kafka.network.SocketServer)
[2023-01-24 19:36:22,482] INFO Kafka version: 3.3.2 (org.apache.kafka.common.utils.AppInfoParser)
[2023-01-24 19:36:22,483] INFO Kafka commitId: b66af662e61082cb (org.apache.kafka.common.utils.AppInfoParser)
[2023-01-24 19:36:22,483] INFO Kafka startTimeMs: 1674560182479 (org.apache.kafka.common.utils.AppInfoParser)
[2023-01-24 19:36:22,484] INFO [KafkaServer id=0] started (kafka.server.KafkaServer)
[2023-01-24 19:36:22,522] INFO [BrokerToControllerChannelManager broker=0 name=alterPartition]: Recorded new controller, from now on will use node DESKTOP-ADOT1IA:9092 (id: 0 rack: null) (kafka.server.BrokerToControllerRequestThread)
[2023-01-24 19:36:22,522] INFO [BrokerToControllerChannelManager broker=0 name=forwarding]: Recorded new controller, from now on will use node DESKTOP-ADOT1IA:9092 (id: 0 rack: null) (kafka.server.BrokerToControllerRequestThread)
--------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------
Open a new command prompt and create a topic with name javainuse-topic, that has only one partition & one replica.

encountered error upon running below :
C:\kafka_2.13-3.3.2>.\bin\windows\kafka-topics.bat --create --zookeeper localhost:2181 --replication-factor 1 --partitions 1 --topic javainuse-topic
Exception in thread "main" joptsimple.UnrecognizedOptionException: zookeeper is not a recognized option
        at joptsimple.OptionException.unrecognizedOption(OptionException.java:108)
        at joptsimple.OptionParser.handleLongOptionToken(OptionParser.java:510)
        at joptsimple.OptionParserState$2.handleArgument(OptionParserState.java:56)
        at joptsimple.OptionParser.parse(OptionParser.java:396)
        at kafka.admin.TopicCommand$TopicCommandOptions.<init>(TopicCommand.scala:567)
        at kafka.admin.TopicCommand$.main(TopicCommand.scala:47)
        at kafka.admin.TopicCommand.main(TopicCommand.scala)

solution : https://stackoverflow.com/questions/69297020/exception-in-thread-main-joptsimple-unrecognizedoptionexception-zookeeper-is

Newer versions(2.2+) of Kafka no longer requires ZooKeeper connection string
    --zookeeper localhost:2181
It throws the following exception while creating a topic
Exception in thread "main" joptsimple.UnrecognizedOptionException: zookeeper is not a recognized option
Instead, add Kafka Broker 
    --bootstrap-server localhost:9092 connection string.
    ./kafka-topics.sh --create --topic test-topic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 4

C:\kafka_2.13-3.3.2>.\bin\windows\kafka-topics.bat --create --topic javainuse-topic --bootstrap-server localhost:9092 --replication-factor 1 --partitions 1
Created topic javainuse-topic.
--------------------------------------------------------------------------------------------------------------------------------


--------------------------------------------------------------------------------------------------------------------------------
Next Open a new command prompt and create a producer to send message to the 
above created javainuse-topic and send a message - Hello World Javainuse to it-

C:\kafka_2.13-3.3.2>.\bin\windows\kafka-console-producer.bat --broker-list localhost:9092 --topic javainuse-topic
>Hello World Javainuse from Producer
>another message
>this is kafka
>edi wow
>test message
>from producer
>to consumer
>this is apache kafka
>hello world
>
--------------------------------------------------------------------------------------------------------------------------------

--------------------------------------------------------------------------------------------------------------------------------
Finally Open a new command prompt and start the consumer which listens to the topic javainuse-topic we just created above. 
We will get the message we had sent using the producer

C:\kafka_2.13-3.3.2>.\bin\windows\kafka-console-consumer.bat --bootstrap-server localhost:9092 --topic javainuse-topic --from-beginning
Hello World Javainuse from Producer
another message
this is kafka
edi wow
test message
from producer
to consumer
this is apache kafka
hello world

--------------------------------------------------------------------------------------------------------------------------------


--------------------------------------------------------------------------------------------------------------------------------
Spring Boot + Apache Kafka
reference : https://www.javainuse.com/spring/spring-boot-apache-kafka-hello-world



--------------------------------------------------------------------------------------------------------------------------------
