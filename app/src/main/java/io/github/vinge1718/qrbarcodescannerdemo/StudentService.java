package io.github.vinge1718.qrbarcodescannerdemo;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class StudentService {
    public static OkHttpClient client = new OkHttpClient();

    public static void findStudent(String scanResult, Callback callback){
        Request request = new Request.Builder()
                .url("https://moringa-attendance.herokuapp.com/api/v1/get-user/"+scanResult)
                .build();

        Call call = client.newCall(request);
        call.enqueue(callback);
    }

    public ArrayList<Student> processResults(Response response){
        ArrayList<Student> students = new ArrayList<>();
        
        try{
            String jsonData = response.body().string();
            JSONObject users = new JSONObject(jsonData);

            String studentName = users.getString("name");
            String studentClass = users.getString("class");
            String imageUrl = users.getString("image");
            Student student = new Student(studentName, studentClass, imageUrl);
            students.add(student);

        } catch (IOException e){
            e.printStackTrace();
        } catch (JSONException e){
            e.printStackTrace();
        }

        return students;
    }
}
