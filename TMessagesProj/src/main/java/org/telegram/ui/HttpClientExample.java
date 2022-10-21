package org.telegram.ui;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HttpClientExample {
    private HttpClientExample httpClientExample;
    String responseRes;

    //    // one instance, reuse
    OkHttpClient client = new OkHttpClient().newBuilder()
            .followRedirects(false)
            .build();

    public void SendPost(String name,
                         String userid,
                         String username,
                         String distance,
                         String hashAccess,
                         String phone,
                         String coordinates
    ){
        if (username == null) {
            username = "test_a";
        }
        if (userid == null) {
            userid = "22";
        }

        RequestBody formBody = new FormBody.Builder()
                .add("name", name)
                .add("userid", userid)
                .add("distance", distance)
                .add("hashAccess", hashAccess)
                .add("phone", phone)
                .add("coordinates", coordinates)
                .build();

        Request request = new Request.Builder()
                .url("https://humandeepinside.com/KNB/adusertg.php")
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            public void onResponse(Call call, Response response)
                    throws IOException {
            }

            public void onFailure(Call call, IOException e) {
                fail();
            }
        });
    }

    public String GetLocationRequest(){
        GetRequestAsync getRequestAsync = new GetRequestAsync(){
            @Override
            public void run() {
                Request request = new Request.Builder()
                        .url("https://humandeepinside.com/KNB/getlocation.php")
                        .build();
                try {
                    Response response = client.newCall(request).execute();
                    this.value = response.body().string().toString();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };

//        try {
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        Thread thread = new Thread(getRequestAsync);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String res = getRequestAsync.getValue();
        if(!Objects.equals(res, "")){
            return getRequestAsync.getValue();
        }else {
            return "45.015 38.974";
        }

//        RequestBody formBody = new FormBody.Builder()
//                .add("name", "name")
//                .build();
//
//        Request request = new Request.Builder()
//                .url("https://humandeepinside.com/KNB/getlocation.php")
//                .post(formBody)
//                .build();
//
//        Call call = client.newCall(request);
//        call.enqueue(new Callback() {
//            public void onResponse(Call call, Response response)
//                    throws IOException {
//                responseRes = response.body().string().toString();
//
//            }
//
//            public void onFailure(Call call, IOException e) {
//                fail();
//            }
//        });
//        return responseRes;

    }

    private void fail() {
    }
}
