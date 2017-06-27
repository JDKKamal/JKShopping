/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdkgroup.jkshopping.model.signup_100;

/**
 *
 * @author kamlesh
 */
public class ResponseProfile {
    
    private String profile_picture, birthdate, gendar, address;

    public ResponseProfile()
    {
    }

    public ResponseProfile(String profile_picture, String birthdate, String gendar, String address)
    {
        this.profile_picture = profile_picture;
        this.birthdate = birthdate;
        this.gendar = gendar;
        this.address = address;
    }

    public String getProfile_picture()
    {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture)
    {
        this.profile_picture = profile_picture;
    }

    public String getBirthdate()
    {
        return birthdate;
    }

    public void setBirthdate(String birthdate)
    {
        this.birthdate = birthdate;
    }

    public String getGendar()
    {
        return gendar;
    }

    public void setGendar(String gendar)
    {
        this.gendar = gendar;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }
   
}
