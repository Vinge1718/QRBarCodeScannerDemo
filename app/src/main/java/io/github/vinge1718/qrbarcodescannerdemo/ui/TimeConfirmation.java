package io.github.vinge1718.qrbarcodescannerdemo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.vinge1718.qrbarcodescannerdemo.R;
import io.github.vinge1718.qrbarcodescannerdemo.services.StudentService;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TimeConfirmation extends AppCompatActivity {
//    @BindView(R.id.timeTextView) TextView mTimeToPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_confirmation);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String dateTime = intent.getStringExtra("dtTime");
        String dtAndTime = dateTime;
        String studentId = intent.getStringExtra("studentId");
//        mTimeToPost.setText(dtAndTime + studentId);

        postTimeToApi(studentId, dtAndTime);
    }

    public void postTimeToApi(String studentId, String dtAndTime){
        final StudentService studentService = new StudentService();

        studentService.postTimeRequest(studentId, dtAndTime, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

            }
        });

    }
}
