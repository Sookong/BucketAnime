package com.tanwuyu.ivrtym.bucketanime.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.tanwuyu.ivrtym.bucketanime.base.MyApplication;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.viewentity.AccountRecords;
import com.tanwuyu.ivrtym.bucketanime.model.httpEntity.viewentity.SearchRecords;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by ivrty on 2017/3/18.
 */

public class SharedPreferencesUtil {
    public static String APP_CONFIG = "appconfig";
    public static String IS_ONLY_PLAY_BY_WIFI = "isonlyplaybywifi";
    public static String ACCOUNT_RECORDS = "countrecords";
    public static String SEARCH_RECORDS = "searchrecords";

    public static String APP_INFO = "appinfo";
    public static String IS_FIRST_OPEN_APP = "isFirstOpenApp";


    /**
     * appinfo
     */
    //是否第一次打开app
    public static boolean isFirstOpenApp() {
        SharedPreferences sharedPreferences = MyApplication.getInstance().getSharedPreferences(APP_INFO, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(IS_FIRST_OPEN_APP, true);
    }

    public static void setIsFirstOpenApp(Boolean isFirstOpenApp) {
        SharedPreferences sharedPreferences = MyApplication.getInstance().getSharedPreferences(APP_INFO, Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(IS_FIRST_OPEN_APP, isFirstOpenApp).commit();

    }

    //账户输入记录
    public static LinkedHashSet<String> getAccoundRecords() {
        SharedPreferences sharedPreferences = MyApplication.getInstance().getSharedPreferences(APP_INFO, Context.MODE_PRIVATE);
        String defaultRecordsJson = "{accountRecords:[]}";
        String recordsJson = sharedPreferences.getString(ACCOUNT_RECORDS,defaultRecordsJson);
        AccountRecords accountRecords = new GsonBuilder().create().fromJson(recordsJson,AccountRecords.class);
        LinkedHashSet<String> recordsSet = accountRecords.getAccountRecords();
        return recordsSet;
    }

    public static Boolean setAccountRecords(String accountRecord) {
        SharedPreferences sharedPreferences = MyApplication.getInstance().getSharedPreferences(APP_INFO, Context.MODE_PRIVATE);
        LinkedHashSet<String> recordsSet = getAccoundRecords();
        recordsSet.add(accountRecord);

        AccountRecords accountRecords = new AccountRecords();
        accountRecords.setAccountRecords(recordsSet);

        String recordsJson = new GsonBuilder().create().toJson(accountRecords);
        return sharedPreferences.edit().putString(ACCOUNT_RECORDS,recordsJson).commit();
    }
    public static Boolean removeAccountRecord(String accountRecord){
        SharedPreferences sharedPreferences = MyApplication.getInstance().getSharedPreferences(APP_INFO, Context.MODE_PRIVATE);
        LinkedHashSet<String> recordsSet = getAccoundRecords();
        recordsSet.remove(accountRecord);

        AccountRecords accountRecords = new AccountRecords();
        accountRecords.setAccountRecords(recordsSet);

        String recordsJson = new GsonBuilder().create().toJson(accountRecords);
        return sharedPreferences.edit().putString(ACCOUNT_RECORDS,recordsJson).commit();
    }


    //搜索记录
    public static LinkedHashSet<String> getSearchRecords() {
        //clearSearchRecords();
        SharedPreferences sharedPreferences = MyApplication.getInstance().getSharedPreferences(APP_INFO, Context.MODE_PRIVATE);
        String defaultRecordsJson = "{searchRecords:[]}";
        String recordsJson = sharedPreferences.getString(SEARCH_RECORDS,defaultRecordsJson);
        Log.e("SP_SEARCH_GET",recordsJson);
        SearchRecords searchRecords = new GsonBuilder().create().fromJson(recordsJson,SearchRecords.class);
        LinkedHashSet<String> recordsSet = searchRecords.getSearchRecords();
        return recordsSet;
    }

    public static Boolean setSearchRecords(String searchRecord) {
        SharedPreferences.Editor editor = MyApplication.getInstance().getSharedPreferences(APP_INFO, Context.MODE_PRIVATE).edit().clear();

        LinkedHashSet<String> recordsSet = getSearchRecords();
        recordsSet.add(searchRecord);

        SearchRecords searchRecords = new SearchRecords();
        searchRecords.setSearchRecords(recordsSet);

        String recordsJson = new GsonBuilder().create().toJson(searchRecords);
        Log.e("SP_SEARCH_SET",recordsJson);


        return editor.putString(SEARCH_RECORDS,recordsJson).commit();

    }

    public static boolean clearSearchRecords() {
        SharedPreferences.Editor editor = MyApplication.getInstance().getSharedPreferences(APP_INFO, Context.MODE_PRIVATE).edit().clear();

        return editor.remove(SEARCH_RECORDS).commit();
    }


    /**
     * appcofig
     */
    //是否只在wifi下播放
    public static boolean isOnlyPlayByWifi() {
        SharedPreferences sharedPreferences = MyApplication.getInstance().getSharedPreferences(APP_CONFIG, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(IS_ONLY_PLAY_BY_WIFI, true);
    }

    public static void setIsOnlyPlayByWifi(Boolean isOnlyPlayByWifi) {
        SharedPreferences sharedPreferences = MyApplication.getInstance().getSharedPreferences(APP_CONFIG, Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean(IS_ONLY_PLAY_BY_WIFI, isOnlyPlayByWifi).commit();
    }

}
