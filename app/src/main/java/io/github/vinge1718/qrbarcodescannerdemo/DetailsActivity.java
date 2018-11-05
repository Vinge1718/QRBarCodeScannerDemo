package io.github.vinge1718.qrbarcodescannerdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DetailsActivity extends AppCompatActivity {
    @BindView(R.id.detailsTextView) TextView mDetailsTextView;
    @BindView(R.id.detailsListView) ListView mDetailsListView;

    public static final String TAG = DetailsActivity.class.getSimpleName();
    public ArrayList<String> mStudentDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

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
            public void onResponse(Call call, Response response) {
                mStudentDetails = studentService.processResults(response);
                DetailsActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        String[] studentStuff = new String[mStudentDetails.size()];
                        for (int i = 0; i < studentStuff.length; i++){
                            studentStuff[i] = mStudentDetails.get(i);
                        }

                        ArrayAdapter adapter = new ArrayAdapter(DetailsActivity.this, android.R.layout.simple_list_item_1, studentStuff);
                        mDetailsListView.setAdapter(adapter);
//                        mStudentNameTextView = findViewById(R.id.studentName) ;
//                        mStudentNameTextView.setText(studentName);
//                        mClassName = findViewById(R.id.studentClass);
//                        String studentClass = mStudentDetails.get(1);
//                        mClassName.setText(studentClass);
                    }
                });
            }
        });
    }
}
