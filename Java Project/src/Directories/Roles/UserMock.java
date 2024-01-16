package Directories.Roles;

import Directories.Helpers.Role;

import java.io.Serializable;

public class UserMock implements UserInterface, Serializable {
    private final String name;
    private final String password;

    public UserMock(String name, String password){
        this.name = name;
        this.password = password;
    }
    @Override
    public String getName() {
        return this.name;
    }
    public String getPassword() {
        return this.password;
    }
    public int getSalary() {
        return 300;
    }

    @Override
    public int getAccesslevel() {
        return 0;
    }

    @Override
    public Role getRole() {
        return null;
    }

    @Override
    public String getEmail() {
        return null;
    }

    @Override
    public String getPhone() {
        return null;
    }

    @Override
    public String getLastName() {
        return null;
    }

    @Override
    public void setEmail(String text) {

    }

    @Override
    public void setPhone(String text) {

    }

    @Override
    public void setSalary(int i) {

    }

    @Override
    public void setRole(Role role) {

    }

    @Override
    public void setPassword(String text) {

    }

    @Override
    public void setAccesslevel(int i) {

    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof UserMock){
            return this.name.equals(((UserMock) obj).getName()) && this.password.equals(((UserMock) obj).getPassword());
        }
        return false;
    }
}
