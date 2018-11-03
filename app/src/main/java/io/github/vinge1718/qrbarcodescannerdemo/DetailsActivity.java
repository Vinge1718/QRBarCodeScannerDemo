package io.github.vinge1718.qrbarcodescannerdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DetailsActivity extends AppCompatActivity {
    private TextView mDetailsTextView;
    public static final String TAG = DetailsActivity.class.getSimpleName();
    public ArrayList<Student> mStudentDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mDetailsTextView = findViewById(R.id.detailsTextView);
        Intent intent = getIntent();
        String scanResult = intent.getStringExtra("scan-result");
        mDetailsTextView.setText("The students Id is " + scanResult);
        getDetails(scanResult);
    }

    public void getDetails(String scanResult){
        final StudentService studentService = new StudentService();

        studentService.findStudent(scanResult, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                try{
                    String jsonData = response.body().string();
                    Log.v(TAG, jsonData);
                    mStudentDetails = studentService.processResults(response);
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
