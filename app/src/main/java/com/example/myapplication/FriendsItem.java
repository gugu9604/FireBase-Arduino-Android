package com.example.myapplication;

public class FriendsItem {

    private int profile;
    private String info;
    private String phone;

    public int getProfile(){
        return profile;
    }
    public String getInfo(){
        return info;
    }
    public String getPhone(){
        return phone;
    }

    public FriendsItem(int profile, String info, String phone){
        this.profile=profile;
        this.info=info;
        this.phone=phone;
    }

}

