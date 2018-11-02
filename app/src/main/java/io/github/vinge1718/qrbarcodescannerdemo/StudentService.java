package io.github.vinge1718.qrbarcodescannerdemo;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class StudentService {
    public static OkHttpClient client = new OkHttpClient();

    public static void findStudent(String scanResult, Callback callback){
        Request request = new Request.Builder()
                .url("https://moringa-attendance.herokuapp.com/api/v1/get-user/"+scanResult)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }
}
