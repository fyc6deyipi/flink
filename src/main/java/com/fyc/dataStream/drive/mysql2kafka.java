/**
 * FileName: drive
 * Author:   DFJX
 * Date:     2019/11/28 15:03
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.fyc.dataStream.drive;

import com.fyc.dataStream.source.mysqlSource;
import com.fyc.dataStream.utils.confUtil;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer010;

import java.util.Properties;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author DFJX
 * @create 2019/11/28
 * @since 1.0.0
 */
public class mysql2kafka {

    public static void main(String[] args) throws Exception{
        Properties props = confUtil.getPropertise();
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        DataStreamSource<String> students = env.addSource(new mysqlSource());
        students.print();
        students.addSink(new FlinkKafkaProducer010<String>(
                "topic_1",
                new SimpleStringSchema(),
                props
        ));
        env.execute("from mysql");
    }
}
