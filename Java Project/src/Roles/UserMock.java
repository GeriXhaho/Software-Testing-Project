package Roles;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    public boolean equals(Object obj) {
        if(obj instanceof UserMock){
            return this.name.equals(((UserMock) obj).getName()) && this.password.equals(((UserMock) obj).getPassword());
        }
        return false;
    }
}
