package com.tanwuyu.ivrtym.bucketanime.utils;

import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Created by ivrty on 2017/4/22.
 */

public class SerializableUtil {
    //可序列化对象转String
    public static <T extends Serializable> String serializableObj2String(T t) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        String s = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            objectOutputStream.writeObject(t);
            s = new String(Base64.encode(byteArrayOutputStream.toByteArray(), Base64.DEFAULT));
            objectOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {

        }

        return s;
    }

    //String转序列化对象
    public static <T> T string2SerializableObj(String s, Class<T> clazz) {
        T result = null;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = null;
        try {

            ObjectInputStream objectInputStream = new ObjectInputStream(new ByteArrayInputStream(Base64.encode(s.getBytes(),Base64.DEFAULT)));
            result = (T) objectInputStream.readObject();
            Log.e("ERROR_EMPTY?",String.valueOf(result==null));
            objectInputStream.close();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            Log.e("ERROR_HERE",e.toString());
            e.printStackTrace();

        }
        return result;
    }
}
