package io.github.vinge1718.qrbarcodescannerdemo.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.vinge1718.qrbarcodescannerdemo.R;
import io.github.vinge1718.qrbarcodescannerdemo.models.Student;
import io.github.vinge1718.qrbarcodescannerdemo.ui.TimeConfirmation;

public class StudentDetailsListAdapter extends RecyclerView.Adapter<StudentDetailsListAdapter.StudentViewHolder> {
    private ArrayList<Student> mStudentDetails;
    private Context mContext;


    public StudentDetailsListAdapter(Context context, ArrayList<Student> details){
        mContext = context;
        mStudentDetails = details;
    }

    @Override
    public StudentDetailsListAdapter.StudentViewHolder onCreateViewHolder(ViewGroup parent, int viewtype){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_details_list_item, parent, false);
        StudentViewHolder viewHolder = new StudentViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StudentDetailsListAdapter.StudentViewHolder holder, int position){
        holder.bindStudent(mStudentDetails.get(position));
    }

    @Override
    public int getItemCount(){
        return mStudentDetails.size();
    }

    public class StudentViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.studentImageView) ImageView mStudentImageView;
        @BindView(R.id.studentNameTextView) TextView mStudentNameTextView;
        @BindView(R.id.classTextView) TextView mClassTextView;
        @BindView(R.id.arrivalTime) TextView mArrivalTime;
        @BindView(R.id.arrivalDate) TextView mArrivalDate;
        @BindView(R.id.detailsTextView) TextView mStudentId;


        private Context mContext;

        public StudentViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindStudent(Student student){
            Picasso.with(mContext).load(student.getImageUrl()).into(mStudentImageView);
            mStudentNameTextView.setText(student.getStudentName());
            mClassTextView.setText(student.getClasses());

            long currentTime= System.currentTimeMillis();
            Calendar cal=Calendar.getInstance();
            cal.setTimeInMillis(currentTime);
            String showTime=String.format("%1$tI:%1$tM:%1$tS %1$Tp", cal);
            mArrivalTime.setText(showTime);

            Date dt = Calendar.getInstance().getTime();
            SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            String formattedDate = df.format(dt);
            mArrivalDate.setText(formattedDate);
        }
    }
}
