package com.contbook.spy_d.contbookdemo;

import java.security.PublicKey;

/**
 * Created by SPY-D on 10/27/2017.
 */

public class UpdateInfo {
    public String name;
    public String email;
    public String cont;
    public String fb;
    public String ln;
    public String git;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getFb() {
        return fb;
    }

    public void setFb(String fb) {
        this.fb = fb;
    }

    public String getLn() {
        return ln;
    }

    public void setLn(String ln) {
        this.ln = ln;
    }

    public String getGit() {
        return git;
    }

    public void setGit(String git) {
        this.git = git;
    }

    public UpdateInfo()
    {

    }

    public UpdateInfo(String email,String name, String cont, String fb, String ln, String git) {
        this.email= email;
        this.name = name;
        this.cont = cont;
        this.fb = fb;
        this.ln = ln;
        this.git = git;
    }
}
