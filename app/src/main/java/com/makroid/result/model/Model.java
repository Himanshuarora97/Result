package com.makroid.result.model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Model {
    // Used to get result of particular roll number
    public static List<ListItem> getData(String jsonObjectString, String roll) throws JSONException {
        JSONObject jsonObject = new JSONObject(jsonObjectString).getJSONObject(roll);
        JSONObject value = (JSONObject) jsonObject.get("exams"); // Exam javaObject
//        Log.e("same here ", String.valueOf(value.length()));
        List<String> examList = new ArrayList<>();

        // iterate over exam jsonObject
        for (Iterator<String> it = value.keys(); it.hasNext(); ) {
            String key = it.next();
            examList.add(key);
        }
//        Log.e(" tohu ",examList.toString());
        ArrayList<String> marks = new ArrayList<>();
        ArrayList<String> internal = new ArrayList<>();
        ArrayList<String> external = new ArrayList<>();
        for (String key : examList) {
            marks.add((String) ((JSONArray) value.get(key)).get(1));
            internal.add(((String) ((JSONArray) value.get(key)).get(0)).split(" ")[0]);
            external.add(((String) ((JSONArray) value.get(key)).get(0)).split(" ")[2]);
        }
        List<ListItem> data = new ArrayList<>();
        ListItem firstData = new ListItem();
        firstData.setcRank("College Rank : " + jsonObject.get("cRank"));
        firstData.setcPercentage( "Credit Percentage : "+jsonObject.get("cPercentage") + " %");
        firstData.setPercentage( "Percentage : "+ jsonObject.get("percentage") + " %");
        firstData.setuRank("University Rank : "  + jsonObject.get("uRank"));
        firstData.setTotal("Total marks Obtained : " + jsonObject.get("total") + " / " + (examList.size()) * 100);
        firstData.setCredit("Total Credit Secured  : " + jsonObject.get("credit"));
        // first (0 index) element in arrayList data refers to basic info as mention above
        data.add(firstData);

        for (int i = 0; i < marks.size(); i++) {
            ListItem item = new ListItem();
            item.setexam(examList.get(i));
            item.setmarks("Marks : " + marks.get(i));
            item.setinternal("Internal : " + internal.get(i));
            item.setexternal("External : " + external.get(i));
            // Used to add Subject information in arrayList
            data.add(item);
        }
        return data;
    }

}
