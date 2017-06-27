/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdkgroup.jkshopping.model.param.signup_100;

import com.jdkgroup.jkshopping.model.param.RequestData;

import java.util.List;

/**
 *
 * @author kamlesh
 */
public class RequestSignUpInsert {

    private RequestData requestdata;
    private List<ModelSignUp> alsignup;

    public RequestSignUpInsert(RequestData requestdata, List<ModelSignUp> alsignup)
    {
        this.requestdata = requestdata;
        this.alsignup = alsignup;
    }

    public RequestData getRequestdata()
    {
        return requestdata;
    }

    public void setRequestdata(RequestData requestdata)
    {
        this.requestdata = requestdata;
    }

    public List<ModelSignUp> getAlsignup()
    {
        return alsignup;
    }

    public void setAlsignup(List<ModelSignUp> alsignup)
    {
        this.alsignup = alsignup;
    }

}
