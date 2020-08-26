package com.fyc

import org.apache.flink.configuration.{ConfigConstants, Configuration}
import org.apache.flink.streaming.api.scala.{DataStream, StreamExecutionEnvironment, createTypeInformation}
import java.io.InputStream
import java.util.Properties

import com.fyc.tools.{KAFKA_TOPICS, REDIS_KEYS, StrongJedisClient, kafkaUtils}
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.api.java.utils.ParameterTool
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction
import org.apache.flink.streaming.connectors.kafka.{FlinkKafkaConsumer010, FlinkKafkaProducer09}
import org.apache.flink.util.Collector

object app {

  private val REDIS_GTW_COUNT: String = REDIS_KEYS.REDIS_GTW_COUNT
  private val topic_gtw: String = KAFKA_TOPICS.topic_gtw


  def main(args: Array[String]): Unit = {
    val configuration = new Configuration()
    configuration.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER,true)
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(configuration)

    val stream: DataStream[String] = env.addSource(new FlinkKafkaConsumer010[String](
      kafkaUtils.getTopicPropertise.getProperty(topic_gtw),
      new SimpleStringSchema(),
      kafkaUtils.getTopicPropertise
    ))
    val count: DataStream[(String, String, Int)] = stream.map(str => (str, REDIS_GTW_COUNT, 1)).keyBy(1).sum(2)
    count.addSink(new RichSinkFunction[(String, String, Int)] {
      override def invoke(value: (String, String, Int)): Unit = {
        val client: StrongJedisClient = StrongJedisClient.getInstance()
        client.incrBy(REDIS_GTW_COUNT,value._3)
      }
    })
    env.execute()



  }

}
