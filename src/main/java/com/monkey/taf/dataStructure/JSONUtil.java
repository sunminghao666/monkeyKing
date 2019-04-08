package com.monkey.taf.dataStructure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONUtil {
    private static final String SEP1 = ",";

    private static final String SEP2 = "|";

    private static final String SEP3 = "=";

    // 对象转化为json对象
    public static JSONObject parseObjectToJSONObject(Object obj) {
        JSONObject jsonObject = JSONObject.fromObject(obj);
        return jsonObject;
    }

    // //字符串转化为json对象

    public static JSONObject parseStringToJSONObject(String str) {
        JSONObject jsonObject = JSONObject.fromObject(str);
        return jsonObject;
    }

    // 对象转化为json字符串
    public static String parseObjectToString(Object obj) {
        Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(obj);
    }

    // List集合转换成json字符串
    public static String parseListToString(List < ? extends Object > t) {
        Gson gson = new GsonBuilder().serializeNulls().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        return gson.toJson(t);
    }

    // json转化成集合
    @SuppressWarnings({ "unchecked", "deprecation" })
    public static List < ? extends Object > parseStringToList(String str, Class < ? extends Object > c) {
        JSONArray array = JSONArray.fromObject(str);
        List < ? extends Object > list = JSONArray.toList(array, c);
        return list;
    }

    /**
     * 转换JSONObject为 Map格式
     * 
     * @param jsonObject JSONObject对象
     * @return map Map对象
     */
    @SuppressWarnings("unchecked")
    public static Map < String, Object > parseJSONObject2Map(JSONObject jsonObject) {
        Map < String, Object > map = new HashMap < String, Object >();
        JSONObject nullObject = new JSONObject();
        nullObject.put("nullObject", null);
        for (Iterator < String > iterator = jsonObject.keys(); iterator.hasNext();) {
            String key = iterator.next();
            Object value = jsonObject.get(key);
            if (value.equals(nullObject.get("nullObject"))) {
                map.put(key, null);
            }
            else {
                map.put(key, value);
            }
        }
        return map;
    }

    /**
     * 转换JSONArray为 Map格式
     * 
     * @param JSONArray jsonArray对象
     * @return List 对象
     */
    public static List < Object > parseJSONArray2List(JSONArray jsonArray) {
        List < Object > list = new ArrayList < Object >();
        for (int i = 0; i < jsonArray.size(); i++) {
            Object v = jsonArray.get(i);
            // 如果内层还是数组的话，继续解析
            if (v instanceof JSONArray) {
                parseJSONArray2List((JSONArray) v);
            }
            else if (v instanceof JSONObject) {
                list.add(parseJSONObject2Map((JSONObject) v));

            }
            else {
                list.add(v);
            }
        }
        return list;
    }

    @SuppressWarnings("unchecked")
    public static List < Map < String, Object >> parseJSON2List(String jsonStr) {
        List < Map < String, Object >> list = new ArrayList < Map < String, Object >>();
        try {
            JSONArray jsonArr = JSONArray.fromObject(jsonStr);
            Iterator < JSONObject > it = jsonArr.iterator();
            while (it.hasNext()) {
                JSONObject json2 = it.next();
                list.add(parseJSON2Map(json2.toString()));
            }
            return list;
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @SuppressWarnings("unchecked")
    public static Map < String, Object > parseJSON2Map(String jsonStr) {
        Map < String, Object > map = new HashMap < String, Object >();
        // 最外层解析
        try {
            JSONObject json = JSONObject.fromObject(jsonStr);
            for (Object k : json.keySet()) {
                Object v = json.get(k);
                // 如果内层还是数组的话，继续解析
                if (v instanceof JSONArray) {
                    List < Map < String, Object >> list = new ArrayList < Map < String, Object >>();
                    Iterator < JSONObject > it = ((JSONArray) v).iterator();
                    while (it.hasNext()) {
                        JSONObject json2 = it.next();
                        list.add(parseJSON2Map(json2.toString()));
                    }
                    map.put(k.toString(), list);
                }
                else {
                    map.put(k.toString(), v);
                }
            }
            return map;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    public static Map < String, Object > parseJSON2MapThrowException(String jsonStr) {
        Map < String, Object > map = new HashMap < String, Object >();
        JSONObject json = JSONObject.fromObject(jsonStr);
        for (Object k : json.keySet()) {
            Object v = json.get(k);
            // 如果内层还是数组的话，继续解析
            if (v instanceof JSONArray) {
                List < Map < String, Object >> list = new ArrayList < Map < String, Object >>();
                Iterator < JSONObject > it = ((JSONArray) v).iterator();
                while (it.hasNext()) {
                    JSONObject json2 = it.next();
                    list.add(parseJSON2MapThrowException(json2.toString()));
                }
                map.put(k.toString(), list);
            }
            else {
                map.put(k.toString(), v);
            }
        }
        return map;
    }

    public static Object jsonToObject(String json, Class < ? > clazz) {
        JSONObject obj = JSONObject.fromObject(json);
        return JSONObject.toBean(obj, clazz);
    }

    public static Object jsonToObject(String json, Class < ? > clazz, Map < ?, ? > map) {
        JSONObject obj = JSONObject.fromObject(json);
        if (map == null || map.isEmpty()) {
            return JSONObject.toBean(obj, clazz);
        }
        return JSONObject.toBean(obj, clazz, map);
    }

    public static String beanToJson(Object obj, JsonConfig config) {
        JSONObject jsonArray = null;
        if (config == null) {
            return beanToJson(obj);
        }
        else {
            jsonArray = JSONObject.fromObject(obj, config);
        }
        return jsonArray.toString();
    }

    public static String beanToJson(Object obj) {
        JSONObject jsonObject = JSONObject.fromObject(obj);
        return jsonObject.toString();
    }

    /**
     * String @param s String @return
     */
    public static String twser(String s) {
        return null;
    }

    /**
     * 将json对象转换成Map
     * 
     * @param jsonObject json对象
     * @return Map对象
     */
    @SuppressWarnings("unchecked")
    public static Map < String, String > JSONObjectToMap(JSONObject jsonObject) {
        Map < String, String > result = new HashMap < String, String >();
        Iterator < String > iterator = jsonObject.keys();
        String key = null;
        String value = null;
        while (iterator.hasNext()) {
            key = iterator.next();
            value = jsonObject.getString(key);
            result.put(key, value);
        }
        return result;
    }

    /**
     * String转换Map
     * 
     * @param mapText :需要转换的字符串
     * @return Map<?,?>
     */
    public static Map < String, Object > StringToMap(String mapText) {

        if (mapText == null || mapText.equals("")) {
            return null;
        }
        mapText = mapText.substring(1);

        Map < String, Object > map = new HashMap < String, Object >();
        String[] text = mapText.split("\\" + SEP2); // 转换为数组
        for (String str : text) {
            String[] keyText = str.split(SEP3); // 转换key与value的数组
            if (keyText.length < 1) {
                continue;
            }
            String key = keyText[0]; // key
            String value = keyText[1]; // value
            if (value.charAt(0) == 'M') {
                Map < ?, ? > map1 = StringToMap(value);
                map.put(key, map1);
            }
            else if (value.charAt(0) == 'L') {
                List < ? > list = StringToList(value);
                map.put(key, list);
            }
            else {
                map.put(key, value);
            }
        }
        return map;
    }

    /**
     * String转换List
     * 
     * @param listText :需要转换的文本
     * @return List<?>
     */
    public static List < Object > StringToList(String listText) {
        if (listText == null || listText.equals("")) {
            return null;
        }
        listText = listText.substring(1);

        List < Object > list = new ArrayList < Object >();
        String[] text = listText.split("\\" + SEP1);
        String listStr = "";
        boolean flag = false;
        for (String str : text) {
            if (!str.equals("")) {
                if (str.charAt(0) == 'M') {
                    Map < ?, ? > map = StringToMap(str);
                    list.add(map);
                }
                else if (str.charAt(0) == 'L' || flag) {
                    flag = true;
                    listStr += str + SEP1;
                }
                else {
                    list.add(str);
                }
            }
            if (str.equals("")) {
                flag = false;
                List < ? > lists = StringToList(listStr);
                list.add(lists);
            }
        }
        return list;
    }

    /**
     * 转换JSONObject为 Map格式
     * 
     * @param jsonObject JSONObject对象
     * @return map Map对象
     */
    @SuppressWarnings("unchecked")
    public static Map < String, Object > parseJSON2Map(JSONObject jsonObject) {
        Map < String, Object > map = new HashMap < String, Object >();
        JSONObject nullObject = new JSONObject();
        nullObject.put("nullObject", null);
        for (Iterator < String > iterator = jsonObject.keys(); iterator.hasNext();) {
            String key = iterator.next();
            Object value = jsonObject.get(key);
            if (value.equals(nullObject.get("nullObject"))) {
                map.put(key, null);
            }
            else {
                map.put(key, value);
            }
        }
        return map;
    }

    /**
     * 转换JSONArray为 Map格式
     * 
     * @param JSONArray jsonArray对象
     * @return List 对象
     */
    public static List < Object > parseJSONArray2Map(JSONArray jsonArray) {
        List < Object > list = new ArrayList < Object >();
        for (int i = 0; i < jsonArray.size(); i++) {
            Object v = jsonArray.get(i);
            // 如果内层还是数组的话，继续解析
            if (v instanceof JSONArray) {
                parseJSONArray2Map((JSONArray) v);
            }
            else if (v instanceof JSONObject) {
                list.add(parseJSON2Map((JSONObject) v));

            }
            else {
                list.add(v);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        String str = "{dayRange:5,changeRateGt:'20',changeRateLt:'-20'}";
        Map < String, Object > jsonMap = parseJSON2Map(str);
        System.out.println(jsonMap);
    }
}
