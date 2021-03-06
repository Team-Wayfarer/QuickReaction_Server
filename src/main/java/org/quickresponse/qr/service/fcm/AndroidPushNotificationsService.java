package org.quickresponse.qr.service.fcm;

import org.springframework.http.HttpEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.concurrent.CompletableFuture;

// firebase_server_key = firebase project > cloud messaging > server key

@Service
public class AndroidPushNotificationsService {

    private static final String firebase_server_key = "AAAA6OJJkHE:APA91bHphU0ug4ER5rqhiradyOrsiAJROFXsYP7tn51-OEvEQKJRzunM4H8h3isVGwQWOHTyeR2rGKXI0iwhrV1wVZsucSHRmn6VpKYG9tPElCQ8IRaiqIqeWVS8DU5EIlfePtj1gPvS";
    private static final String firebase_api_url = "https://fcm.googleapis.com/fcm/send";

    @Async
    public CompletableFuture<String> send(HttpEntity<String> entity) {

        RestTemplate restTemplate = new RestTemplate();

        ArrayList<ClientHttpRequestInterceptor> interceptors = new ArrayList<>();

        interceptors.add(new HeaderRequestInterceptor("Authorization", "key=" + firebase_server_key));
        interceptors.add(new HeaderRequestInterceptor("Content-Type", "application/json;"+"UTF-8"));

        restTemplate.setInterceptors(interceptors);
        String firebaseResponse = restTemplate.postForObject(firebase_api_url, entity, String.class);

        return CompletableFuture.completedFuture(firebaseResponse);
    }
}
