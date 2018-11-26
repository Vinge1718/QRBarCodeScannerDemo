package io.github.vinge1718.qrbarcodescannerdemo.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Adapter;
import android.widget.TextView;

import java.io.IOException;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.vinge1718.qrbarcodescannerdemo.R;
import io.github.vinge1718.qrbarcodescannerdemo.adapters.FinalStudentListAdapter;
import io.github.vinge1718.qrbarcodescannerdemo.models.FinalStudent;
import io.github.vinge1718.qrbarcodescannerdemo.services.StudentService;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class FilteredResults extends AppCompatActivity {
    @BindView(R.id.filteredRecyclerView) RecyclerView filteredRecyclerView;
    @BindView(R.id.filteredStudentsTextView) TextView mFilteredResultsTextView;

    private FinalStudentListAdapter filterAdapter;
    public ArrayList<FinalStudent> mFilteredStudents = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filtered_results);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String queryDate = intent.getStringExtra("dateSelected");
        String classId = intent.getStringExtra("classId");

        mFilteredResultsTextView.setText("These are the results for the date " + queryDate +" and class " + classId);

        getFilteredList(classId,queryDate);
    }

    public void getFilteredList(String classId, String queryDate){
        final StudentService studentService = new StudentService();

        studentService.filterAttendance(classId, queryDate, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mFilteredStudents = studentService.processFilteredResults(response);

                FilteredResults.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        filterAdapter = new FinalStudentListAdapter(getApplicationContext(), mFilteredStudents);
                        filteredRecyclerView.setAdapter(filterAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(FilteredResults.this);
                        filteredRecyclerView.setLayoutManager(layoutManager);
//                        filteredRecyclerView.setHasFixedSize(true);
                    }
                });
            }
        });
    }
}
