package com.je1224.animal;

public class MapSearch {
    String place_name;
    String address_name;
    String phone;
    String place_url;

    public MapSearch() {
    }

    public MapSearch(String place_name, String address_name, String phone, String place_url) {
        this.place_name = place_name;
        this.address_name = address_name;
        this.phone = phone;
        this.place_url = place_url;
    }

    public String getPlace_name() {
        return place_name;
    }

    public void setPlace_name(String place_name) {
        this.place_name = place_name;
    }

    public String getAddress_name() {
        return address_name;
    }

    public void setAddress_name(String address_name) {
        this.address_name = address_name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPlace_url() {
        return place_url;
    }

    public void setPlace_url(String place_url) {
        this.place_url = place_url;
    }
}
