///**
// * FileName: kafka2mysql
// * Author:   DFJX
// * Date:     2019/11/29 13:55
// * Description:
// * History:
// * <author>          <time>          <version>          <desc>
// * 作者姓名           修改时间           版本号              描述
// */
//package com.fyc.java.dataStream.drive;
//
//import com.alibaba.fastjson.JSON;
//import com.fyc.java.bean.student;
//import com.fyc.java.dataStream.sink.mysqlSink;
//import org.apache.flink.api.common.serialization.SimpleStringSchema;
//import org.apache.flink.configuration.ConfigConstants;
//import org.apache.flink.configuration.Configuration;
//import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
//import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
//import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
//
//import java.util.Properties;
///**
// * 〈一句话功能简述〉<br>
// * 〈〉
// *
// * @author DFJX
// * @create 2019/11/29
// * @since 1.0.0
// */
//public class kafka2mysql {
//
//    public static void main(String[] args) throws Exception {
//
//        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
//
//        Properties props = new Properties();
//        props.put("bootstrap.servers", "localhost:9092");
//        props.put("zookeeper.connect", "localhost:2181");
//        props.put("group.id", "fyc");
//        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
//        props.put("auto.offset.reset", "latest");
//        SingleOutputStreamOperator<String> student = env.addSource(new FlinkKafkaConsumer010<String>(
//                "topic_2",   //这个 kafka topic 需要和上面的工具类的 topic 一致
//                new SimpleStringSchema(),
//                props)).setParallelism(1)
//                .map(string -> JSON.parseObject(string, student.class)); //Fastjson 解析字符串成 student 对象
//
//        student.addSink(new mysqlSink()); //数据 sink 到 mysql
//
//        env.execute("Flink add sink");
//
//    }
//}
