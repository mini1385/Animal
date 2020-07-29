package com.je1224.animal;

public class CalendarHeader extends ViewModel {
    String header;
    long mCurrentTime;

    public CalendarHeader() {
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(long time) {
        String value = DateUtil.getDate(time, DateUtil.CALENDAR_HEADER_FORMAT);
        this.header = value;
    }

    public void setHeader(String header) {
        this.header = header;
    }
}
