package com.jdkgroup.jkshopping.model.param.signup_100;

/**
 *
 * @author kamlesh
 */
public class ModelSignUp 
{
    private int id;
    private String unique_id, username, email, password, cell_no, platform, current_date;

    public ModelSignUp()
    {
    }
    
    public ModelSignUp(String unique_id, String username, String email, String password, String cell_no, String platform)
    {
        this.unique_id = unique_id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.cell_no = cell_no;
        this.platform = platform;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
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

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }

    public String getCell_no()
    {
        return cell_no;
    }

    public void setCell_no(String cell_no)
    {
        this.cell_no = cell_no;
    }

    public String getPlatform()
    {
        return platform;
    }

    public void setPlatform(String platform)
    {
        this.platform = platform;
    }

    public String getCurrent_date()
    {
        return current_date;
    }

    public void setCurrent_date(String current_date)
    {
        this.current_date = current_date;
    }
    
    
}
