package Directories.Roles;

import Directories.Helpers.Role;

import java.io.Serial;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

public class User implements UserInterface,Serializable{
    @Serial
    private static final long serialVersionUID = 1L;

    private String firstName;
    private String lastName;
    private Calendar birthday = Calendar.getInstance();
    private String phone;
    private String email;
    private int salary;
    private Role role;
    private String password;
    private int accesslevel;

    public User(String name, String password){
        this.firstName = name;
        this.lastName = "bosh";
        this.phone = "bosh";
        this.email = "bosh";
        this.salary  = 3000;
        this.password = password;
        this.role = Role.Librarian;
        this.accesslevel = role.ordinal();
    }
    public User(String name, String lastname, Calendar birthday, String phone, String email, int salary, Role role, String password){
        this.firstName = name;
        this.lastName = lastname;
        this.birthday.setTime(birthday.getTime());
        this.phone = phone;
        this.email = email;
        this.salary  = salary;
        this.password = password;
        this.role = role;
        this.accesslevel = role.ordinal();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return salary == user.salary &&
                accesslevel == user.accesslevel &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(birthday, user.birthday) &&
                Objects.equals(phone, user.phone) &&
                Objects.equals(email, user.email) &&
                role == user.role &&
                Objects.equals(password, user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, birthday, phone, email, salary, role, password, accesslevel);
    }

    public String getName(){
        return this.firstName;
    }

    public String getPassword(){
        return this.password;
    }

    public Role getRole(){
        return this.role;
    }
    @Override
    public String toString() {
        return (""+this.firstName+" "+this.lastName);
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Calendar getBirthday() {
        return birthday;
    }

    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAccesslevel() {
        return accesslevel;
    }

    public void setAccesslevel(int accesslevel) {
        this.accesslevel = accesslevel;
    }
}
