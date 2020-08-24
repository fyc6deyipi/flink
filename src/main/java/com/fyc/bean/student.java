/**
 * FileName: student
 * Author:   DFJX
 * Date:     2019/11/28 14:46
 * Description:
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package com.fyc.bean;

import com.alibaba.fastjson.JSON;
import org.apache.flink.api.common.serialization.SerializationSchema;

import java.io.InputStream;


/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author DFJX
 * @create 2019/11/28
 * @since 1.0.0
 */
public class student {

    private int id;
    private String name;
    private String sex;
    private int age;

    public student() {
    }

    public student(int id, String name, String sex, int age) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.age = age;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
//        return "{\"id\":"+id+",\"name\":\""+name+"\",\"sex\":\""+sex+"\",\"age\":"+age+"}";
    }




}
