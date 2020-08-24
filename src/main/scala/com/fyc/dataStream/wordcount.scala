package com.fyc.dataStream

import org.apache.flink.configuration.{ConfigConstants, Configuration}
import org.apache.flink.streaming.api.scala._

//必须先监听9000端口 cmd到netcat目录下  执行nc64.exe -l -p 9000
object wordcount {
  def main(args: Array[String]): Unit = {

    val configuration = new Configuration()
    configuration.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER,true)
    val env: StreamExecutionEnvironment = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(configuration)
    val ds: DataStream[String] = env.socketTextStream("localhost",9000)
    val count: DataStream[(String, Int)] = ds.flatMap(_.split(" "))
      .map((_, 1))
      .keyBy(0)
      //.timeWindow(Time.seconds(5),Time.seconds(5))
      .sum(1)
    count.print().setParallelism(1)
    env.execute("word count")







  }
}
