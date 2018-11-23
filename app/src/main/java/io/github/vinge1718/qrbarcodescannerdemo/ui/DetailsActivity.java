package io.github.vinge1718.qrbarcodescannerdemo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.vinge1718.qrbarcodescannerdemo.R;
import io.github.vinge1718.qrbarcodescannerdemo.adapters.StudentDetailsListAdapter;
import io.github.vinge1718.qrbarcodescannerdemo.models.Student;
import io.github.vinge1718.qrbarcodescannerdemo.services.StudentService;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class DetailsActivity extends AppCompatActivity implements View.OnClickListener {
//    @BindView(R.id.detailsTextView) TextView mDetailsTextView;
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.timeButton) Button mTimeButton;

    private StudentDetailsListAdapter mAdapter;
    public static final String TAG = DetailsActivity.class.getSimpleName();
    public ArrayList<Student> mStudentDetails = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String scanResult = intent.getStringExtra("scan-result");
//        mDetailsTextView.setText("The students Id is " + scanResult);
        getDetails(scanResult);
        mTimeButton.setOnClickListener(this);
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

                        mAdapter = new StudentDetailsListAdapter(getApplicationContext(),mStudentDetails);
                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(DetailsActivity.this);
                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }

    @Override
    public void onClick(View v){
        if (v == mTimeButton ) {
            Intent scanIntent = getIntent();
            String scanResult = scanIntent.getStringExtra("scan-result");


            TimeZone tz = TimeZone.getTimeZone("GMT+3:00");
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
            df.setTimeZone(tz);
            String nowAsISO = df.format(new Date());
            Intent intent = new Intent(DetailsActivity.this, TimeConfirmation.class);
            intent.putExtra("dtTime", nowAsISO);
            intent.putExtra("studentId", scanResult);

            startActivity(intent);
        }
    }
}
