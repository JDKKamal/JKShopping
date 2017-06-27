/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jdkgroup.example.genson;

import com.owlike.genson.GenericType;
import com.owlike.genson.Genson;
import com.owlike.genson.GensonBuilder;
import com.owlike.genson.annotation.JsonIgnore;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class POJOConstructorWithArgument {

    public static void main(String[] args)
    {

        //Genson genson = new GensonBuilder().useConstructorWithArguments(true).create();
        
        Genson genson = new GensonBuilder()
                .useDateFormat(new SimpleDateFormat("yyyy-MM-dd"))
                .useIndentation(true)
                .useConstructorWithArguments(true)
                .create();

        Employee emp1 = new Employee("Hari");

        emp1.setId("E432156");
        emp1.setFirstName("Hari krishna");
        emp1.setPassword("Password123");

        List<Employee> alEmp = new ArrayList<>();
        alEmp.add(emp1);

        String json = genson.serialize(alEmp);

        List<Employee> alEmployees = genson.deserialize(json, new GenericType<List<Employee>>() {
        });

        System.out.println(json + "" + alEmployees.get(0).firstName);

    }

    public static class Employee {

        private String id;
        private String firstName;
        private String lastName;
        @JsonIgnore
        private String password;

        public String getId()
        {
            return id;
        }

        public void setId(String id)
        {
            this.id = id;
        }

        public String getFirstName()
        {
            return firstName;
        }

        public void setFirstName(String firstName)
        {
            this.firstName = firstName;
        }

        public String getLastName()
        {
            return lastName;
        }

        public void setLastName(String lastName)
        {
            this.lastName = lastName;
        }

        @JsonIgnore
        public String getPassword()
        {
            return password;
        }

        @JsonIgnore
        public void setPassword(String password)
        {
            this.password = password;
        }

        public Employee(String firstName)
        {
            this.firstName = firstName;
        }

        @Override
        public String toString()
        {
            StringBuilder builder = new StringBuilder();
            builder.append("Employee [id=").append(id).append(", firstName=")
                    .append(firstName).append(", lastName=").append(lastName)
                    .append(", password=").append(password).append("]");
            return builder.toString();
        }
    }
}
