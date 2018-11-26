package io.github.vinge1718.qrbarcodescannerdemo.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.github.vinge1718.qrbarcodescannerdemo.R;
import io.github.vinge1718.qrbarcodescannerdemo.models.FinalStudent;

public class FinalStudentListAdapter extends RecyclerView.Adapter<FinalStudentListAdapter.FilteredViewHolder>  {
    private Context mContext;
    private ArrayList<FinalStudent> mFilteredStudents;

    public FinalStudentListAdapter(Context context, ArrayList<FinalStudent> filteredDetails){
        mContext = context;
        mFilteredStudents = filteredDetails;
    }

    @Override
    public FinalStudentListAdapter.FilteredViewHolder onCreateViewHolder (ViewGroup parent, int viewtype){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.filteredlistitems, parent, false);
        FilteredViewHolder viewHolder = new FilteredViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(FinalStudentListAdapter.FilteredViewHolder holder, int position){
        holder.bindFilteredStudent(mFilteredStudents.get(position));
    }

    @Override
    public int getItemCount(){
        return mFilteredStudents.size();
    }

    public class FilteredViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.userImageView) ImageView mStudentImageView;
        @BindView(R.id.studentNameTextView) TextView mStudentNameTextViewextView;
        @BindView(R.id.filteredIdTextView) TextView mStudentIdTextView;
        @BindView(R.id.classTextView) TextView mClassTextView;
        @BindView(R.id.emailTextView) TextView mEmailTextView;
        @BindView(R.id.roleNameTextView) TextView mRoleNameTextView;

        private Context mContext;

        public FilteredViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindFilteredStudent(FinalStudent finalStudent){
            Picasso.with(mContext).load(finalStudent.getImageUrl()).into(mStudentImageView);
            mStudentNameTextViewextView.setText(finalStudent.getUserName());
            mStudentIdTextView.setText("Student ID: "+String.valueOf(finalStudent.getUserId()));
            mClassTextView.setText(finalStudent.getClasses());
            mEmailTextView.setText(finalStudent.getEmail());
            mRoleNameTextView.setText(finalStudent.getRoleName());
        }
    }

}
