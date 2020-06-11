package org.quickresponse.qr.service.fcm;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.quickresponse.qr.domain.spot.Spot;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AndroidPushPeriodicNotifications {
    public static String DoubtUserNotification(String duid, Spot userVisitedSpot, LocalDateTime infectionVisitTime, LocalDateTime userVisitTime) throws JSONException, UnsupportedEncodingException {

        LocalDate localDate = LocalDate.now();

        JSONObject body = new JSONObject();

        body.put("to", duid);

        JSONObject data = new JSONObject();

        data.put("title",URLEncoder.encode("확진자와 접촉하였습니다.","UTF-8"));
        data.put("body1",URLEncoder.encode("방문한 장소 이름: "+ userVisitedSpot.getName(),"UTF-8"));
        data.put("body2",URLEncoder.encode("방문한 장소 주소 "+ userVisitedSpot.getAddress().toString(),"UTF-8"));
        data.put("body3",URLEncoder.encode("확진자 방문시간 "+ infectionVisitTime.toString(),"UTF-8"));
        data.put("body4",URLEncoder.encode("본인 방문시간 "+ userVisitTime.toString(),"UTF-8"));

/*
        data.put("title","확진자와 접촉하였습니다.");
        data.put("body1","방문한 장소 이름: "+ userVisitedSpot.getName());
        data.put("body2","방문한 장소 주소 "+ userVisitedSpot.getAddress());
        data.put("body3","확진자 방문시간 "+ infectionVisitTime.toString());
        data.put("body4","본인 방문시간 "+ userVisitTime.toString());
*/

        body.put("data", data);

        System.out.println(body.toString());

        return body.toString();
    }

    public static String PeriodicNotificationJson() throws JSONException, UnsupportedEncodingException {


        String sampleData[] = {"device"};

        JSONObject body = new JSONObject();

        List<String> tokenlist = new ArrayList<>();

        for(int i=0; i<sampleData.length; i++){
            tokenlist.add(sampleData[i]);
        }

        JSONArray array = new JSONArray();

        for(int i=0; i<tokenlist.size(); i++) {
            array.put(tokenlist.get(i));
        }

        body.put("registration_ids", array);

        JSONObject notification = new JSONObject();
        notification.put("title",URLEncoder.encode("ㅎㅇ","UTF-8"));
        notification.put("body",URLEncoder.encode("진짜 안녕!!!!","UTF-8"));
        notification.put("click_action","OpenSub");

        body.put("notification", notification);

/*        JSONObject data = new JSONObject();

        data.put("title",URLEncoder.encode("찐막","UTF-8"));
        data.put("message",URLEncoder.encode("찌리찌리찐막","UTF-8"));

        body.put("data", data);*/

        System.out.println(body.toString());

        return body.toString();
    }
}
