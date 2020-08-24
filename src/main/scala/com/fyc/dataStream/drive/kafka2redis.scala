package com.fyc.dataStream.drive

import java.util.Properties

import org.apache.flink.configuration.{ConfigConstants, Configuration}
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010
import java.util.UUID.randomUUID

import com.fyc.dataStream.utils.confUtil
object kafka2redis {
  def main(args: Array[String]): Unit = {
//    val propertise: Properties = confUtil.getPropertise
//    val config = new Configuration
//    config.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER, true)
//    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(config)
//

    println(randomUUID())


  }
}
