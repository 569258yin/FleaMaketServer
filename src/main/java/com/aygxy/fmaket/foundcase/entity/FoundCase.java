package com.aygxy.fmaket.foundcase.entity;

import java.util.Date;

public class FoundCase {
    private String fdcid;

    private String userid;

    private String fdctitle;

    private Date fdctime;

    private String fdccontext;

    private Date fdcfindtime;

    private String fdcfindaddress;

    public String getFdcid() {
        return fdcid;
    }

    public void setFdcid(String fdcid) {
        this.fdcid = fdcid == null ? null : fdcid.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getFdctitle() {
        return fdctitle;
    }

    public void setFdctitle(String fdctitle) {
        this.fdctitle = fdctitle == null ? null : fdctitle.trim();
    }

    public Date getFdctime() {
        return fdctime;
    }

    public void setFdctime(Date fdctime) {
        this.fdctime = fdctime;
    }

    public String getFdccontext() {
        return fdccontext;
    }

    public void setFdccontext(String fdccontext) {
        this.fdccontext = fdccontext == null ? null : fdccontext.trim();
    }

    public Date getFdcfindtime() {
        return fdcfindtime;
    }

    public void setFdcfindtime(Date fdcfindtime) {
        this.fdcfindtime = fdcfindtime;
    }

    public String getFdcfindaddress() {
        return fdcfindaddress;
    }

    public void setFdcfindaddress(String fdcfindaddress) {
        this.fdcfindaddress = fdcfindaddress == null ? null : fdcfindaddress.trim();
    }
}