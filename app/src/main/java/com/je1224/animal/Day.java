package com.je1224.animal;

import com.je1224.animal.DateUtil;
import com.je1224.animal.ViewModel;

import java.util.Calendar;

public class Day extends ViewModel {
    String day;

    public Day() {
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    // TODO : day에 달력일값넣기
    public void setCalendar(Calendar calendar){

        day = DateUtil.getDate(calendar.getTimeInMillis(), DateUtil.DAY_FORMAT);

    }

}
