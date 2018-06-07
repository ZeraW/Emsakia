package com.hima.atef.splash.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.hima.atef.splash.R;

import java.util.ArrayList;

/**
 * Created by Hima on 3/18/2018.
 */

public class AdapterAzkar extends RecyclerView.Adapter<AdapterAzkar.ViewHolder> {

    private Context mContext;
    private ArrayList<String> mArrayList;
    private ArrayList<Integer> mArrayList2;
    int x ;



    public AdapterAzkar(Context mContext, ArrayList<String> mArrayList, ArrayList<Integer> mArrayList2) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
        this.mArrayList2 = mArrayList2;
    }

    @Override
    public AdapterAzkar.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_azkar_rows, parent, false);
        return new AdapterAzkar.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final AdapterAzkar.ViewHolder holder, final int position) {


        holder.txtDescription.setText(Html.fromHtml(mArrayList.get(position)));
        holder.txtCounter.setText(String.valueOf(mArrayList2.get(position)));
         x = mArrayList2.get(position);


        holder.txtCounter.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                if (x > 0) {

                    x = x - 1;
                    holder.txtCounter.setText(String.valueOf(x));
                }

            }
        });
        holder.btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                x = mArrayList2.get(position);
                holder.txtCounter.setText(String.valueOf(x));

            }
        });






      /*  String imgURL = currentElement.getmImgeUrl();
        String creatorName = currentElement.getmCreator();
        int likecount = currentElement.getmLikes();

        holder.mTextViewCreator.setText(creatorName);
       *//* holder.mTextViewLikes.setText("Likes:" + likecount);
        Picasso.with(mContext).load(imgURL).fit().centerInside().into(holder.mImageView);*//*
        holder.linear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(mContext,BigKitty.class);
                intent.putExtra("Image",currentKitty.getmImgeUrl());
                intent.putExtra("Text",currentKitty.getmCreator());
                intent.putExtra("Likes",currentKitty.getmLikes());

                mContext.startActivity(intent);

            }
        });*/


    }

    @Override
    public int getItemCount() {
        return mArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtDescription, txtCounter;
        ImageButton btnReset;

        /*LinearLayout linear;*/

        public ViewHolder(View itemView) {
            super(itemView);

            txtCounter = itemView.findViewById(R.id.txtCounterAzkar);
            txtDescription = itemView.findViewById(R.id.txtDescriptionAzkar);
            btnReset = itemView.findViewById(R.id.btnCounterResetAzkar);

        }
    }
}

