package com.fyc

import org.apache.flink.configuration.{ConfigConstants, Configuration}
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, createTypeInformation}
import java.io.InputStream
import java.util.Properties


import com.fyc.tools.kafkaUtils
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer010, FlinkKafkaProducer09}
import org.apache.flink.util.Collector

object app {

  val configuration = new Configuration()
  configuration.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER,true)
  val env: StreamExecutionEnvironment = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(configuration)

  def main(args: Array[String]): Unit = {
    println(kafkaUtils.getKafkaPropertise.getProperty("bootstrap.servers"))
    val stream: DataStream[String] = env.addSource(new FlinkKafkaConsumer010[String](
      kafkaUtils.getTopicPropertise.getProperty("topic_gtw"),
      new SimpleStringSchema(),
      kafkaUtils.getKafkaPropertise
    ))
    val count: DataStream[(String, String, Int)] = stream.map(str => (str, "count", 1)).keyBy(1).sum(2)

    env.execute()



  }

}
