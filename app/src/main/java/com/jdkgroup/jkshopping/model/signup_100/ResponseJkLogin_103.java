/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdkgroup.jkshopping.model.signup_100;

import com.jdkgroup.jkshopping.model.ResponseData;

/**
 *
 * @author kamlesh
 */
public class ResponseJkLogin_103 {

    private ResponseData reponsedata;
    private ResponseSignUp responsesignup;
    private ResponseProfile responseprofile;

    public ResponseJkLogin_103(ResponseData reponsedata, ResponseSignUp responsesignup, ResponseProfile responseprofile)
    {
        this.reponsedata = reponsedata;
        this.responsesignup = responsesignup;
        this.responseprofile = responseprofile;
    }

    public ResponseData getReponsedata()
    {
        return reponsedata;
    }

    public void setReponsedata(ResponseData reponsedata)
    {
        this.reponsedata = reponsedata;
    }

    public ResponseSignUp getResponsesignup()
    {
        return responsesignup;
    }

    public void setResponsesignup(ResponseSignUp responsesignup)
    {
        this.responsesignup = responsesignup;
    }

    public ResponseProfile getResponseprofile()
    {
        return responseprofile;
    }

    public void setResponseprofile(ResponseProfile responseprofile)
    {
        this.responseprofile = responseprofile;
    }
    
    
}
