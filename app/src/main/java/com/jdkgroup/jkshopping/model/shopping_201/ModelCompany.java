package com.jdkgroup.jkshopping.model.shopping_201;

/**
 *
 * @author kamlesh
 */
public class ModelCompany
{
    private int id;
    private String name, description, rating, company_logo, status, current_date;

    public ModelCompany()
    {
    }

    public ModelCompany(String name, String description, String rating, String company_logo, String status)
    {
        this.name = name;
        this.description = description;
        this.rating = rating;
        this.company_logo = company_logo;
        this.status = status;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public String getRating()
    {
        return rating;
    }

    public void setRating(String rating)
    {
        this.rating = rating;
    }

    public String getCompany_logo()
    {
        return company_logo;
    }

    public void setCompany_logo(String company_logo)
    {
        this.company_logo = company_logo;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
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
