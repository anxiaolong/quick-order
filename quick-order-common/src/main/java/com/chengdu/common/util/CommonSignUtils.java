package com.chengdu.common.util;

import org.apache.commons.codec.digest.DigestUtils;

public class CommonSignUtils {
    public static String getSign(String data,String key){
//        System.out.println(data);
//        System.out.println(key);
//        System.out.println(DigestUtils.md5Hex(data + "&key=" + key));
        return DigestUtils.md5Hex(data+"&key="+key);
    }
}
