package com.intellicar.dummylafproject;

import java.util.ArrayList;

public class GetTokenRequest {
    String device_id;
    Long starttime;
    Long endtime;
    ArrayList<Integer> modules;
    String phone_publickey;

    public GetTokenRequest(String device_id, long starttime, long endtime, ArrayList<Integer> modulesArr, String phone_publickey) {
        this.device_id = device_id;
        this.starttime = starttime;
        this.endtime = endtime;
        this.modules = modulesArr;
        this.phone_publickey = phone_publickey;
    }

    public String getDevice_id() {
        return device_id;
    }

    public void setDevice_id(String device_id) {
        this.device_id = device_id;
    }

    public Long getStarttime() {
        return starttime;
    }

    public void setStarttime(Long starttime) {
        this.starttime = starttime;
    }

    public Long getEndtime() {
        return endtime;
    }

    public void setEndtime(Long endtime) {
        this.endtime = endtime;
    }

    public ArrayList<Integer> getModules() {
        return modules;
    }

    public void setModules(ArrayList<Integer> modules) {
        this.modules = modules;
    }

    public String getPhone_publickey() {
        return phone_publickey;
    }

    public void setPhone_publickey(String phone_publickey) {
        this.phone_publickey = phone_publickey;
    }
}
