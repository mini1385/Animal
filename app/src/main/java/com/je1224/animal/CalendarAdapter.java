package com.je1224.animal;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.Calendar;
import java.util.List;

public class CalendarAdapter extends RecyclerView.Adapter {
    private final int HEADER_TYPE = 0;
    private final int EMPTY_TYPE = 1;
    private final int DAY_TYPE = 2;

    private List<Object> mCalendarList;

    public CalendarAdapter(List<Object> calendarList) {
        mCalendarList = calendarList;
    }

    public void setCalendarList(List<Object> calendarList) {
        mCalendarList = calendarList;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        Object item = mCalendarList.get(position);
        if (item instanceof Long) {
            return HEADER_TYPE;
        } else if (item instanceof String) {
            return EMPTY_TYPE;
        } else {
            return DAY_TYPE;

        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == HEADER_TYPE) {

            HeaderViewHolder viewHolder = new HeaderViewHolder(inflater.inflate(R.layout.item_calendar_header, parent, false));

            StaggeredGridLayoutManager.LayoutParams params = (StaggeredGridLayoutManager.LayoutParams)viewHolder.itemView.getLayoutParams();
            params.setFullSpan(true);
            viewHolder.itemView.setLayoutParams(params);

            return viewHolder;

        } else if (viewType == EMPTY_TYPE) {
            return new EmptyViewHolder(inflater.inflate(R.layout.item_day_empty, parent, false));

        }

        else {
            return new DayViewHolder(inflater.inflate(R.layout.item_day, parent, false));

        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        int viewType = getItemViewType(position);

        if (viewType == HEADER_TYPE) {
            HeaderViewHolder hvh = (HeaderViewHolder) holder;
            Object item = mCalendarList.get(position);
            CalendarHeader model = new CalendarHeader();
            if (item instanceof Long) {
                model.setHeader((Long) item);
            }
            hvh.bind(model);
        }

        else if (viewType == EMPTY_TYPE) {
            EmptyViewHolder evh = (EmptyViewHolder) holder;
            EmptyDay model = new EmptyDay();
            evh.bind(model);
        }

        else if (viewType == DAY_TYPE) {
            DayViewHolder dvh = (DayViewHolder) holder;
            Object item = mCalendarList.get(position);
            Day model = new Day();
            if (item instanceof Calendar) {

                model.setCalendar((Calendar) item);
            }
            dvh.bind(model);
        }

    }

    @Override
    public int getItemCount() {
        if (mCalendarList != null) {
            return mCalendarList.size();
        }
        return 0;
    }

    private class HeaderViewHolder extends RecyclerView.ViewHolder {

        TextView itemHeaderTitle;

        public HeaderViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }


        public void initView(View v){
            itemHeaderTitle = v.findViewById(R.id.item_header_title);
        }

        public void bind(ViewModel model){
            String header = ((CalendarHeader)model).getHeader();
            itemHeaderTitle.setText(header);
        };
    }


    private class EmptyViewHolder extends RecyclerView.ViewHolder {


        public EmptyViewHolder(@NonNull View itemView) {
            super(itemView);
            initView(itemView);
        }

        public void initView(View v){

        }

        public void bind(ViewModel model){

        };
    }

    private class DayViewHolder extends RecyclerView.ViewHolder {

        Context context;
        TextView itemDay;

        public DayViewHolder(@NonNull View itemView) {
            super(itemView);

            initView(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    context=v.getContext();
                    Intent intent=new Intent(context,TaskActivity.class);
                    context.startActivity(intent);
                }
            });

        }

        public void initView(View v){
            itemDay = v.findViewById(R.id.item_day);

        }

        public void bind(ViewModel model){
            String day = ((Day)model).getDay();
            itemDay.setText(day);
        };


    }
}
