package top.vabook.token.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

/**
 * @Author: vabook
 * @Date: 2019/7/11 16:04
 */
public class JsonUtils {

    private static ObjectMapper objectMapper = new ObjectMapper();

    //对象转字符串
    public static <T> String obj2String(T obj){

        if (obj == null)
            return  null;

        try {
            return obj instanceof String ? (String) obj:objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }

    }

    public static <T> T string2Obj(String str,Class<T> clazz){
        if (StringUtils.isEmpty(str) || clazz == null)
            return null ;

        try {
            return clazz.equals(String.class) ? (T) str:objectMapper.readValue(str,clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }
}
