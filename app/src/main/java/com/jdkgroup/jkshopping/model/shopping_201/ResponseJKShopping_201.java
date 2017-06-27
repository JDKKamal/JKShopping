/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdkgroup.jkshopping.model.shopping_201;

import com.jdkgroup.jkshopping.model.ResponseData;
import java.util.List;

public class ResponseJKShopping_201
{
    private int count;
    private ResponseData responsedata;
    private List<ModelCompany> alcompany;

    public ResponseJKShopping_201(int count, ResponseData responsedata, List<ModelCompany> alcompany)
    {
        this.count = count;
        this.responsedata = responsedata;
        this.alcompany = alcompany;
    }

    public int getCount()
    {
        return count;
    }

    public void setCount(int count)
    {
        this.count = count;
    }

    public ResponseData getResponsedata()
    {
        return responsedata;
    }

    public void setResponsedata(ResponseData responsedata)
    {
        this.responsedata = responsedata;
    }

    public List<ModelCompany> getAlcompany()
    {
        return alcompany;
    }

    public void setAlcompany(List<ModelCompany> alcompany)
    {
        this.alcompany = alcompany;
    }
    
    
}
