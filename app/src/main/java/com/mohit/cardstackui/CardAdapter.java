package com.mohit.cardstackui;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.GradientDrawable;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;

/**
 * Created by mohit on 11/15/2017.
 */

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.CardViewHolder> {

    int screenHeight,screenWidth;
    int []colors;
    GradientDrawable gradientDrawable;


    public CardAdapter(Activity activity) {
        Display display = activity.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenHeight = size.y;
        screenWidth = size.x;
        colors = new int[]{Color.parseColor("#ec407a"), Color.parseColor("#4fc3f7")};
        gradientDrawable = new GradientDrawable(
                GradientDrawable.Orientation.TOP_BOTTOM, colors);
        gradientDrawable.setCornerRadius(screenHeight / 100);

    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.stack_row_layout, parent, false);
        return new CardViewHolder(v);    }

    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    class CardViewHolder extends RecyclerView.ViewHolder{
        ConstraintLayout childCard,parentCard;
        public CardViewHolder(View itemView) {
            super(itemView);

            childCard =  itemView.findViewById(R.id.childCard);
            parentCard =  itemView.findViewById(R.id.parentCardStack);

            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(screenWidth - (2 * (int) (screenWidth * 0.04)), (int) (0.16 * screenHeight));     //parent card is small in height(16%) to cut overlap the child cards
            lp.setMargins((int) (screenWidth * 0.04), 0, (int) (screenWidth * 0.04), 0);
            parentCard.setLayoutParams(lp);

            ConstraintLayout.LayoutParams cp = new ConstraintLayout.LayoutParams(screenWidth - (2 * (int) (screenWidth * 0.04)), (int) (0.3 * screenHeight));    //child card is 305 of screen height
            childCard.setLayoutParams(cp);
            childCard.setPivotX((int) (screenHeight * 0.3));
            childCard.setBackground(gradientDrawable);

            childCard.getViewTreeObserver().addOnPreDrawListener(
                    new ViewTreeObserver.OnPreDrawListener() {
                        @Override
                        public boolean onPreDraw() {

                            int[] loc = new int[2];
                            childCard.getLocationInWindow(loc);
                            childCard.setRotationX(-(0.05f * (Math.abs(loc[1])-30)));       //    >>>>>>>>>>   (-(rotation * (cardLocation - 30) ;

                            return true;
                        }
                    });

        }
    }
}
