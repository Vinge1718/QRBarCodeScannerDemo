package io.github.vinge1718.qrbarcodescannerdemo.ui;

import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.vinge1718.qrbarcodescannerdemo.R;
import io.github.vinge1718.qrbarcodescannerdemo.services.StudentService;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class TimeConfirmation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
//    private String[] moringaClasses = new String[] {"MC3", "MPFT19", "MC7"};
    @BindView(R.id.planets_spinner) Spinner mSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_time_confirmation);
        ButterKnife.bind(this);
        mSpinner.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.planets_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        mSpinner.setAdapter(adapter);

        Intent intent = getIntent();
        String dateTime = intent.getStringExtra("dtTime");
        String dtAndTime = dateTime;
        String studentId = intent.getStringExtra("studentId");

        postTimeToApi(studentId, dtAndTime);
    }

//    MC3, MPFT19, MC7

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

    public void onItemSelected(AdapterView<?> parent, View view,
                               int pos, long id){
        // An item was selected. You can retrieve the selected item using
        String selectdClass = parent.getItemAtPosition(pos).toString();
        Toast.makeText(TimeConfirmation.this, selectdClass, Toast.LENGTH_LONG).show();

    }

    public void onNothingSelected(AdapterView<?> parent) {
        // Another interface callback
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }
}
