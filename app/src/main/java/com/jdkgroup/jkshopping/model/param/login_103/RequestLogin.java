/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdkgroup.jkshopping.model.param.login_103;

import com.jdkgroup.jkshopping.model.param.RequestData;

/**
 *
 * @author kamlesh
 */
public class RequestLogin {

    private RequestData requestdata;
    private String email, password, fcm_id;

    public RequestLogin()
    {
    }

    public RequestLogin(RequestData requestdata, String email, String password, String fcm_id)
    {
        this.requestdata = requestdata;
        this.email = email;
        this.password = password;
        this.fcm_id = fcm_id;
    }

    public RequestData getRequestdata()
    {
        return requestdata;
    }

    public void setRequestdata(RequestData requestdata)
    {
        this.requestdata = requestdata;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getFcm_id()
    {
        return fcm_id;
    }

    public void setFcm_id(String fcm_id)
    {
        this.fcm_id = fcm_id;
    }

    
}
