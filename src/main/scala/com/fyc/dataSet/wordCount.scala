package com.fyc.dataSet

import org.apache.flink.api.scala._

object wordCount {
  def main(args: Array[String]): Unit = {
    val env: ExecutionEnvironment = ExecutionEnvironment.getExecutionEnvironment
    val dset: DataSet[String] = env.readTextFile("C:\\Users\\DFJX\\Desktop\\wordcount.txt")
    val map:DataSet[(String, Int)] = dset
      .flatMap(_.toUpperCase.split(""))
      .map((_,1))
      .groupBy(0)
      .sum(1)
    map.print()

  }

}
