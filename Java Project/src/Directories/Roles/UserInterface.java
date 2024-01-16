package Directories.Roles;

import Directories.Helpers.Role;

public interface UserInterface {
    public String getName();
    public String getPassword();
    public int getSalary();

    int getAccesslevel();

    Role getRole();

    String getEmail();

    String getPhone();

    String getLastName();

    void setEmail(String text);

    void setPhone(String text);

    void setSalary(int i);

    void setRole(Role role);

    void setPassword(String text);

    void setAccesslevel(int i);
}
