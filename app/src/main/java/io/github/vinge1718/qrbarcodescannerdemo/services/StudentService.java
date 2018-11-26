package io.github.vinge1718.qrbarcodescannerdemo.services;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import io.github.vinge1718.qrbarcodescannerdemo.models.FinalStudent;
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

    public static void filterAttendance(String classId, String queryDate, Callback callback){
        Request request = new Request.Builder()
                .url("https://moringa-attendance.herokuapp.com/api/v1/get-class-time/"+classId+"/"+queryDate)
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

            String avatarUrl = users.getString("avatar_url"); //.getJSONObject("users")
            String studentClass = users.getString("class_name"); //.getJSONObject("users")
            String email = users.getString("email"); //.getJSONObject("users")
            String roleName = users.getString("role_name"); //.getJSONObject("users")
            int userId = users.getInt("user_id"); //.getJSONObject("users")
            String userName = users.getString("username");//.getJSONObject("users")

            Student student = new Student(avatarUrl, studentClass, email, roleName, userId, userName);
            studentDetails.add(student);

        } catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }

        return studentDetails;
    }

    public ArrayList processFilteredResults(Response response){
        ArrayList<FinalStudent> filteredDetails = new ArrayList<>();

        try{
            String jsonData = response.body().string();
            JSONArray filteredUsers = new JSONArray(jsonData);

            for (int i = 0; i < filteredUsers.length(); i++){
                JSONObject userDetailsJson = filteredUsers.getJSONObject(i);
                String avatarUrl = userDetailsJson.getString("avatar_url");
                String studentClass = userDetailsJson.getString("class_name");
                String email = userDetailsJson.getString("email");
                String roleName = userDetailsJson.getString("role_name");
                String timeIn = userDetailsJson.getString("time_in");
                int userId = userDetailsJson.getInt("user_id");
                String userName = userDetailsJson.getString("username");

                FinalStudent finalStudent = new FinalStudent(avatarUrl, studentClass, email, roleName, timeIn, userId, userName);
                filteredDetails.add(finalStudent);
            }
        }
        catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }

        return filteredDetails;
    }

}
