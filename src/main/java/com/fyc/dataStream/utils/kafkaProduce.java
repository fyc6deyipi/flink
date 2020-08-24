/**
 * FileName: kafkaUtils
 * Author:   DFJX
 * Date:     2019/11/29 10:06
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.fyc.dataStream.utils;

import com.alibaba.fastjson.JSON;
import com.fyc.bean.student;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author DFJX
 * @create 2019/11/29
 * @since 1.0.0
 */
public class kafkaProduce {
    public static final String broker_list = "81.70.54.74:9092";
    public static final String topic = "test";  //kafka topic 需要和 flink 程序用同一个 topic

    public static void writeToKafka() throws InterruptedException {
        Properties props = new Properties();
        props.put("bootstrap.servers", broker_list);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        KafkaProducer producer = new KafkaProducer<String, String>(props);

        for (int i = 1; i <= 100; i++) {
            student student = new student(i, "fyc" + i, (i%2)+"" , 18 + i);
            ProducerRecord record = new ProducerRecord<String, String>(topic, null, null, student.toString());
            producer.send(record);
            System.out.println("发送数据: " + JSON.toJSONString(student));
        }
        producer.flush();
    }

    public static void main(String[] args) throws InterruptedException {
//        int count = 0;
        while (true) {
            Thread.sleep(300);
            writeToKafka();
//            count++;
        }
    }


}
