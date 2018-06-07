package com.hima.atef.splash.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hima.atef.splash.Utilities.ModelTimeAndDate;
import com.hima.atef.splash.R;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Hima on 3/20/2018.
 */

public class AdapterTimeAndDate extends RecyclerView.Adapter<AdapterTimeAndDate.ViewHolder> {

    private Context mContext;
    private ArrayList<ModelTimeAndDate> mArrayList;
    private ArrayList<String> mArrayList2;
    String [] days = {"الاربعاء","الخميس","الجمعة","السبت","الاحد","الاثنين","الثلاثاء","الاربعاء","الخميس","الجمعة","السبت","الاحد","الاثنين","الثلاثاء","الاربعاء","الخميس","الجمعة","السبت","الاحد","الاثنين","الثلاثاء","الاربعاء","الخميس","الجمعة","السبت","الاحد","الاثنين","الثلاثاء","الاربعاء","الخميس"};


    public AdapterTimeAndDate(Context mContext, ArrayList<ModelTimeAndDate> mArrayList, ArrayList<String> mArrayList2) {
        this.mContext = mContext;
        this.mArrayList = mArrayList;
        this.mArrayList2 = mArrayList2;

    }

    @Override
    public AdapterTimeAndDate.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.activity_time_and_date_rows, parent, false);
        return new AdapterTimeAndDate.ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final AdapterTimeAndDate.ViewHolder holder, final int position) {

        int x = position + 1;
        NumberFormat nf =NumberFormat.getInstance(new Locale("ar","EG"));
        nf.format(x);


        ModelTimeAndDate currentItem = mArrayList.get(position);
        String date = currentItem.getDate();
        String Fagr = currentItem.getFagr();
        String Shrouq = currentItem.getShrouq();
        String Zohr = currentItem.getZohr();
        String Asr = currentItem.getAsr();
        String Magreb = currentItem.getMagreb();
        String Ishaa = currentItem.getIshaa();
        String Emsak = currentItem.getEMSAK();

        holder.txtMonthDays.setText(String.valueOf(nf.format(x)) + " رمضان");
        holder.txtSALAHTIME.setText(Emsak);
        holder.txtSALAHTIME.append("\n" + Fagr);
        holder.txtSALAHTIME.append("\n" + Shrouq);
        holder.txtSALAHTIME.append("\n" + Zohr);
        holder.txtSALAHTIME.append("\n" + Asr);
        holder.txtSALAHTIME.append("\n" + Magreb);
        holder.txtSALAHTIME.append("\n" + Ishaa);

        holder.txtSALAH.setText(mArrayList2.get(position));
        holder.txtWeekDays.setText(days[position]);









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
        TextView txtSALAH, txtSALAHTIME, txtWeekDays, txtMonthDays;


        /*LinearLayout linear;*/

        public ViewHolder(View itemView) {
            super(itemView);

            txtSALAH = itemView.findViewById(R.id.txtTADSALAH);
            txtSALAHTIME = itemView.findViewById(R.id.txtTADSALAHTIME);
            txtWeekDays = itemView.findViewById(R.id.txtTADWeekDay);
            txtMonthDays = itemView.findViewById(R.id.txtTADMonthDay);


        }
    }
}

