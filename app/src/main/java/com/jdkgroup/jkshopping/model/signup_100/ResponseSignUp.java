package com.jdkgroup.jkshopping.model.signup_100;

/**
 *
 * @author kamlesh
 */
public class ResponseSignUp 
{
    private String unique_id, username, email, cell_no;

    public ResponseSignUp()
    {
    }

    public ResponseSignUp(String unique_id, String username, String email, String cell_no)
    {
        this.unique_id = unique_id;
        this.username = username;
        this.email = email;
        this.cell_no = cell_no;
    }

    public String getUnique_id()
    {
        return unique_id;
    }

    public void setUnique_id(String unique_id)
    {
        this.unique_id = unique_id;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public String getCell_no()
    {
        return cell_no;
    }

    public void setCell_no(String cell_no)
    {
        this.cell_no = cell_no;
    }
    
    
}
