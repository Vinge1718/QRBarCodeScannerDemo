package io.github.vinge1718.qrbarcodescannerdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity {
    private TextView mDetailsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mDetailsTextView = findViewById(R.id.detailsTextView);
        Intent intent = getIntent();
        String scanResult = intent.getStringExtra("scan-result");
        mDetailsTextView.setText("The student Url is " + scanResult);
    }
}
