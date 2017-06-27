/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdkgroup.jkshopping.model.param;

/**
 *
 * @author kamlesh
 */
public class RequestData 
{
    private int functioncode;

    public RequestData(int functioncode)
    {
        this.functioncode = functioncode;
    }

    public int getFunctioncode()
    {
        return functioncode;
    }

    public void setFunctioncode(int functioncode)
    {
        this.functioncode = functioncode;
    }
}
