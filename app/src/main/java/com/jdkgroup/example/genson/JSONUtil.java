/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdkgroup.example.genson;

import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;

import java.util.List;
import java.util.Map;

public class JSONUtil {

    private static Genson genson = new Genson();
    private static Genson skipNullGenson = new GensonBuilder()
            .setSkipNull(true).create();
    private static Genson prettyGenson = new GensonBuilder().useIndentation(
            true).create();

    public static String getJson(Object obj)
    {
        return genson.serialize(obj);
    }

    public static <T> T deserializeJson(String json, Class<T> clazz)
    {
        return genson.deserialize(json, clazz);
    }

    public static <T> List<T> getListOfObjects(String json, GenericType<List<T>> list)
    {
        return prettyGenson.deserialize(json, list);
    }

    public static <K, V> Map<K, V> getMap(String json,
            GenericType<Map<K, V>> map)
    {
        return genson.deserialize(json, map);
    }

    public static String getJsonSkipNulls(Object obj)
    {
        return skipNullGenson.serialize(obj);
    }

    public static String getPrettyJson(Object obj)
    {
        return prettyGenson.serialize(obj);
    }
}
