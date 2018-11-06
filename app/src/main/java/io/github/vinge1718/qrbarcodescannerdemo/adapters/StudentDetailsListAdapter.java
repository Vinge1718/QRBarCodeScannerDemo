package io.github.vinge1718.qrbarcodescannerdemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.vinge1718.qrbarcodescannerdemo.R;
import io.github.vinge1718.qrbarcodescannerdemo.models.Student;

public class StudentDetailsListAdapter extends RecyclerView.Adapter<StudentDetailsListAdapter.StudentViewHolder> {
    private ArrayList<Student> mStudentDetails = new ArrayList<>();
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

    public class StudentViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.studentImageView) ImageView mStudentImageView;
        @BindView(R.id.studentNameTextView) TextView mStudentNameTextView;
        @BindView(R.id.classTextView) TextView mClassTextView;
        @BindView(R.id.timeButton) Button mTimeButton;

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

        }
    }
}
