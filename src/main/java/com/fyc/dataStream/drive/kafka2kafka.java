/**
 * FileName: kafka2kafka
 * Author:   DFJX
 * Date:     2019/11/29 16:39
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.fyc.dataStream.drive;

import com.fyc.dataStream.utils.confUtil;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.configuration.ConfigConstants;
import org.apache.flink.configuration.Configuration;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer010;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer010;

import java.util.Properties;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author DFJX
 * @create 2019/11/29
 * @since 1.0.0
 */
public class kafka2kafka {
    public static void main(String[] args) throws Exception{
        Properties propertise = confUtil.getPropertise();
        Configuration config = new Configuration();
        config.setBoolean(ConfigConstants.LOCAL_START_WEBSERVER, true);
        StreamExecutionEnvironment env = StreamExecutionEnvironment.createLocalEnvironmentWithWebUI(config);
        DataStreamSource<String> topic_1 = env.addSource(new FlinkKafkaConsumer010<String>(
                "test",
                new SimpleStringSchema(),
                propertise
        ));
        topic_1.addSink(new FlinkKafkaProducer010<String>(
                "topic_2",
                new SimpleStringSchema(),
                propertise
        ));
        env.execute("topic_1 to topic_2");

    }
}
