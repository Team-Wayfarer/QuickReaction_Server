package org.quickresponse.qr.service.fcm;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.UnsupportedEncodingException;

import java.net.URLEncoder;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AndroidPushPeriodicNotifications {

    public static String PeriodicNotificationJson() throws JSONException, UnsupportedEncodingException {

        LocalDate localDate = LocalDate.now();

        String sampleData[] = {"device_token"};

        JSONObject body = new JSONObject();

        List<String> tokenlist = new ArrayList<String>();

        for(int i=0; i<sampleData.length; i++){
            tokenlist.add(sampleData[i]);
        }

        JSONArray array = new JSONArray();

        for(int i=0; i<tokenlist.size(); i++) {
            array.put(tokenlist.get(i));
        }

        body.put("registration_ids", array);

        JSONObject notification = new JSONObject();

        notification.put("title", URLEncoder.encode("제성","UTF-8"));
        notification.put("body", URLEncoder.encode("진짜 안녕! ","UTF-8"));

        body.put("notification", notification);

        System.out.println(body.toString());

        return body.toString();
    }

/*    public static String alarmSaveNotification(AlarmSaveRequestDto alarmSaveRequestDto) throws JSONException {
        LocalDateTime localDateTime = LocalDateTime.now();
        JSONObject notification = new JSONObject();
        JSONObject body = new JSONObject();

        String sampleData[] = {"device token value 1","device token value 2","device token value 3"};

        List<String> tokenlist = new ArrayList<>();

        for(int i=0; i<sampleData.length; i++){
            tokenlist.add(sampleData[i]);
        }

        JSONArray array = new JSONArray();

        for(int i=0; i<tokenlist.size(); i++) {
            array.put(tokenlist.get(i));
        }

        body.put("registration_ids", array);

        if(alarmSaveRequestDto.getDecibel()!=null){
            notification.put("title"," Decibel warning!!");
            notification.put("body","Decibel : " + alarmSaveRequestDto.getDecibel());
            System.out.println(notification.toString());
        }
        if(alarmSaveRequestDto.getHeart()!=null){
            notification.put("title"," Heart warning!!");
            notification.put("body","Heart : " + alarmSaveRequestDto.getHeart());
            System.out.println(notification.toString());
        }
        if(alarmSaveRequestDto.getTumble()!=null){
            notification.put("title"," Tumble warning!!");
            notification.put("body","Tumble : " + alarmSaveRequestDto.getTumble());
            System.out.println(notification.toString());
        }

        body.put("notification", notification);
        return body.toString();
    }*/
}
