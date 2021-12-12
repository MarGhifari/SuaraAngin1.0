//package com.komc.suaraanginrev.adapter;
//
//import android.content.Context;
//import android.view.GestureDetector;
//import android.view.MotionEvent;
//import android.view.View;
//
//import androidx.annotation.NonNull;
//import androidx.recyclerview.widget.RecyclerView;
//
//public class RecyclerTouchListener implements RecyclerView.OnItemTouchListener{
//
//    private GestureDetector gestureDetector;
//    private ClickListener clickListener;
//
//    public RecyclerTouchListener(Context context, final RecyclerView recyclerView, final ClickListener clickListener) {
//        this.clickListener = clickListener;
//        gestureDetector = new GestureDetector(context, new GestureDetector.SimpleOnGestureListener() {
//            @Override
//            public boolean onSingleTapUp(MotionEvent e) {
//                return true;
//            }
//        });
//    }
//
//    @Override
//    public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
//
//        View child = rv.findChildViewUnder(e.getX(), e.getY());
//        if (child != null && clickListener != null && gestureDetector.onTouchEvent(e)) {
//            clickListener.onClick(rv.getChildPosition(child));
//        }
//        return false;
//    }
//
//    @Override
//    public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
//
//    }
//
//    @Override
//    public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
//
//    }
//
//    public interface ClickListener{
//        void onClick(View view, int position);
//
//    }
//}