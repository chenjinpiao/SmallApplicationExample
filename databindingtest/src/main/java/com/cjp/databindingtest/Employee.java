package com.cjp.databindingtest;

/**
 * 类名:com.cjp.databindingtest
 * Created by chenjinpiao on 2017/12/26.
 * 作用：xxxxxxxx
 */

public class Employee {
    private String firstName;
    private String lastName;
    private boolean isFired;
    public Employee(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public boolean isFired() {
        return isFired;
    }

    public void setFired(boolean fired) {
        isFired = fired;
    }
}
