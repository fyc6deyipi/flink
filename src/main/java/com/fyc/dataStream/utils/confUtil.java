package com.fyc.dataStream.utils;

import java.util.Properties;

public class confUtil {
    public static Properties getPropertise(){
        Properties props = new Properties();
        props.put("bootstrap.servers", "81.70.54.74:9092");
        props.put("zookeeper.connect", "81.70.54.74:2181");
        props.put("group.id", "fyc");
        props.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("value.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        props.put("auto.offset.reset", "earliest");
        return props;
    }
}