package com.fyc

import com.fyc.tools.{KAFKA_TOPICS, REDIS_KEYS, StrongJedisClient, kafkaUtils}
import org.apache.flink.api.common.serialization.SimpleStringSchema
import org.apache.flink.api.scala.createTypeInformation
import org.apache.flink.configuration.{ConfigConstants, Configuration}
import org.apache.flink.streaming.api.functions.sink.RichSinkFunction
import org.apache.flink.streaming.api.scala.{AllWindowedStream, DataStream, StreamExecutionEnvironment}
import org.apache.flink.streaming.api.windowing.time.Time
import org.apache.flink.streaming.api.windowing.windows.TimeWindow
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011

object gtw_source_count {
  private val REDIS_GTW_SOURCE_COUNT: String = REDIS_KEYS.REDIS_GTW_SOURCE_COUNT
  private val topic_gtw: String = KAFKA_TOPICS.topic_gtw

  def main(args: Array[String]): Unit = {
    val configuration = new Configuration()
    configuration.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER,true)
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(configuration)
    StrongJedisClient.getInstance().del(REDIS_GTW_SOURCE_COUNT)
    val consumer = new FlinkKafkaConsumer011[String](
      topic_gtw,
      new SimpleStringSchema(),
      kafkaUtils.getKafkaPropertise
    )
    consumer.setStartFromEarliest()
    val stream: DataStream[String] = env.addSource(consumer)
    val value: DataStream[(String, Int)] = stream.map(str => (str.split(",")(6), 1)).keyBy(0).timeWindowAll(Time.seconds(1)).sum(1)
    value.addSink(new RichSinkFunction[(String, Int)] {
      override def invoke(value: (String, Int)): Unit = {
        println(value)
        val client: StrongJedisClient = StrongJedisClient.getInstance()
        if(client.hexists(REDIS_GTW_SOURCE_COUNT,value._1.hashCode.toString)) {
          val num: Int = value._2 + client.hget(REDIS_GTW_SOURCE_COUNT, value._1.hashCode.toString).toInt
          client.hset(REDIS_GTW_SOURCE_COUNT,value._1.hashCode.toString,num.toString)
        }else{
          client.hset(REDIS_GTW_SOURCE_COUNT,value._1.hashCode.toString,value._2.toString)
        }
      }
    })
    env.execute()
  }

}
