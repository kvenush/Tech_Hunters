package com.example.sqlitetutorial;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList job_id, job_title, job_description, job_salary;

    CustomAdapter(Activity activity, Context context, ArrayList job_id, ArrayList job_title, ArrayList job_description,
                  ArrayList job_salary){
        this.activity = activity;
        this.context = context;
        this.job_id = job_id;
        this.job_title = job_title;
        this.job_description = job_description;
        this.job_salary = job_salary;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
        holder.job_id_txt.setText(String.valueOf(job_id.get(position)));
        holder.job_title_txt.setText(String.valueOf(job_title.get(position)));
        holder.job_description_txt.setText(String.valueOf(job_description.get(position)));
        holder.job_salary_txt.setText(String.valueOf(job_salary.get(position)));
        //Recyclerview onClickListener
        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, UpdateActivity.class);
                intent.putExtra("id", String.valueOf(job_id.get(position)));
                intent.putExtra("title", String.valueOf(job_title.get(position)));
                intent.putExtra("author", String.valueOf(job_description.get(position)));
                intent.putExtra("pages", String.valueOf(job_salary.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return job_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView job_id_txt, job_title_txt, job_description_txt, job_salary_txt;
        LinearLayout mainLayout;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            job_id_txt = itemView.findViewById(R.id.book_id_txt);
            job_title_txt = itemView.findViewById(R.id.book_title_txt);
            job_description_txt  = itemView.findViewById(R.id.book_author_txt);
            job_salary_txt = itemView.findViewById(R.id.book_pages_txt);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
