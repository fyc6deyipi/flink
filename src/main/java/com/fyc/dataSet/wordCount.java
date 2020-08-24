///**
// * FileName: wordCount
// * Author:   DFJX
// * Date:     2019/11/27 16:37
// * Description:
// * History:
// * <author>          <time>          <version>          <desc>
// * 作者姓名           修改时间           版本号              描述
// */
//package com.fyc.dataSet;
//
//
//import org.apache.flink.api.common.functions.FlatMapFunction;
//import org.apache.flink.api.java.DataSet;
//import org.apache.flink.api.java.ExecutionEnvironment;
//import org.apache.flink.api.java.operators.DataSource;
//import org.apache.flink.api.java.tuple.Tuple2;
//import org.apache.flink.util.Collector;
//
///**
// * 〈一句话功能简述〉<br>
// * 〈〉
// *
// * @author DFJX
// * @create 2019/11/27
// * @since 1.0.0
// */
//public class wordCount {
//    public static void main(String[] args) throws Exception {
//        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
//
//        DataSource<String> textFile = env.readTextFile("C:\\Users\\DFJX\\Desktop\\wordcount.txt");
//        DataSet map = textFile.flatMap(new FlatMapFunction<String, Tuple2<String,Integer>>() {
//            public void flatMap(String s, Collector<Tuple2<String,Integer>> collector) throws Exception {
//                String[] s1 = s.split(" ");
//                for (String s2 : s1) {
//                    collector.collect(new Tuple2<String,Integer>(s2, 1));
//                }
//            }
//        }).groupBy(0).sum(1);
//        map.print();
//    }
//
//}
