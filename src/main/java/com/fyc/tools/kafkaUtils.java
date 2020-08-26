package com.fyc.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Properties;

public class kafkaUtils {


    private static final HashMap<String,String> map =  new HashMap<String, String>(5);
    private static Properties properties;
    static {
        InputStream is = kafkaUtils.class.getClassLoader().getResourceAsStream("kafka.properties");
        properties = new Properties();
        try {
            properties.load(is);
            map.put("bootstrap.servers",properties.getProperty("bootstrap.servers"));
            map.put("group.id",properties.getProperty("group.id"));
            map.put("key.deserializer",properties.getProperty("key.deserializer"));
            map.put("value.deserializer",properties.getProperty("value.deserializer"));
            map.put("auto.offset.reset",properties.getProperty("auto.offset.reset"));
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            if(null != is)
            {
                try{
                    is.close();
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
    public static HashMap<String,String> getKafkaMap(){
        return map;
    }
    public static Properties getKafkaPropertise(){
        return properties;
    }
    public static Properties getTopicPropertise(){
        InputStream is = kafkaUtils.class.getClassLoader().getResourceAsStream("kafka_topic.properties");
        properties= new Properties();
        try {
            properties.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            if(null != is)
            {
                try{
                    is.close();
                }catch(Exception e)
                {
                    e.printStackTrace();
                }
            }
        }
        return properties;
    }




}
