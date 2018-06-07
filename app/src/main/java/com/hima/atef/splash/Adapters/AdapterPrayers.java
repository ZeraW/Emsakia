package com.hima.atef.splash.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hima.atef.splash.R;

import java.util.ArrayList;


public class AdapterPrayers extends RecyclerView.Adapter<AdapterPrayers.ViewHolderKitty> {

    private Context mContext;
    private ArrayList<String> mArrayList;
    private ArrayList<String> mArrayList2;


    public AdapterPrayers(Context mContext, ArrayList<String> mArrayList, ArrayList<String> mArrayList2) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
        this.mArrayList2 = mArrayList2;
    }

    @Override
    public ViewHolderKitty onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_prayers_rows, parent, false);
        return new ViewHolderKitty(v);
    }

    @Override
    public void onBindViewHolder(ViewHolderKitty holder, int position) {

        holder.txtTitle.setText(Html.fromHtml(mArrayList2.get(position)));
        holder.txtDescription.setText(Html.fromHtml(mArrayList.get(position)));




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

    public class ViewHolderKitty extends RecyclerView.ViewHolder {
        TextView txtTitle, txtDescription;

        /*LinearLayout linear;*/

        public ViewHolderKitty(View itemView) {
            super(itemView);

            txtTitle = itemView.findViewById(R.id.txtTitlePrayers);
            txtDescription = itemView.findViewById(R.id.txtDescriptionPrayer);

        }
    }
}