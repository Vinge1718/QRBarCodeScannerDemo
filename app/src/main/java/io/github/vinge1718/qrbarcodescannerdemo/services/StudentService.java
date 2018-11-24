package io.github.vinge1718.qrbarcodescannerdemo.services;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import io.github.vinge1718.qrbarcodescannerdemo.models.Student;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class StudentService {
    public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    public static OkHttpClient client = new OkHttpClient();

    public static void findStudent(String scanResult, Callback callback){
        Request request = new Request.Builder()
                .url("https://moringa-attendance.herokuapp.com/api/v1/get-user/"+scanResult)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public static void postTimeRequest(String scanResult, String dtAndTime, Callback callback){

        OkHttpClient client = new OkHttpClient();
        JSONObject postData = new JSONObject();

        try {
            postData.put("timestamp",dtAndTime);
        } catch (JSONException e) {
            e.printStackTrace();
        }

//        RequestBody formBody = new FormBody.Builder()
//                .add("timestamp", dtAndTime)
//                .build();

        RequestBody formBody = RequestBody.create(JSON , postData.toString());

        Request request = new Request.Builder()
                .url("https://moringa-attendance.herokuapp.com/api/v1/post-user-time/"+scanResult)
                .post(formBody)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList processResults(Response response){
        ArrayList<Student> studentDetails = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            JSONObject users = new JSONObject(jsonData);

            String imageUrl = users.getJSONObject("users").getString("image");
            String studentName = users.getJSONObject("users").getString("name");
            String studentClass = users.getJSONObject("users").getString("class");

            Student student = new Student(imageUrl, studentName, studentClass);
            studentDetails.add(student);

        } catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }

        return studentDetails;
    }


}
