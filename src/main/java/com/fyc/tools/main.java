package com.fyc.tools;

public class main {
    public static void main(String[] args) {
        StrongJedisClient jedisClient = StrongJedisClient.getInstance();
        jedisClient.set("fyc","111");
    }
}
