package com.jdkgroup.example.removeelement;

/**
 * Created by kamlesh on 6/13/2017.
 */

public class ModelStudent
{
    private int id;
    private String name;

    public ModelStudent(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
