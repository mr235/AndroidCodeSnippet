package com.mr235.androidcodesnippet.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/4/9.
 */
public class GsonUtil {
    private GsonUtil() {
    }

    public static <T> String toJson(T t) {
        Gson gson = new Gson();
        return gson.toJson(t);
    }

    public static Object getFiled(String json, String filedName) throws JSONException {
        Gson gson = new Gson();
        JSONObject obj = new JSONObject(json);
        return obj.get(filedName);
    }

    public static <T> T getType(String json, Class<T> t) {
        Gson gson = new Gson();
        return gson.fromJson(json, t);
    }

    public static <T> List<T> getListType(String json, Class<T> t) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List>() {
        }.getType();
        List tt = gson.fromJson(json, listType);
        if (tt != null && tt.size() > 0) {
            List<T> result = new ArrayList<T>();
            for (int i = 0; i < tt.size(); i++) {
                T temp = getType(toJson(tt.get(i)), t);
                result.add(temp);
            }
            return result;
        }
        return null;
    }

    public static <K, V> Map<K, V> getMapType(String json, Class<K> k, Class<V> v) {
        Gson gson = new Gson();
        Type mapType = new TypeToken<Map>() {
        }.getType();
        Map tt = gson.fromJson(json, mapType);
        if (tt != null && tt.size() > 0) {
            Map<K, V> result = new HashMap<K, V>();
            for (Object o : tt.keySet()) {
                result.put((K) o, getType(toJson(tt.get(o)), v));
            }
            return result;
        }
        return null;
    }

}