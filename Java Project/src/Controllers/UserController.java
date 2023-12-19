package Controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;

import Exceptions.InvalidCredentials;
import FileHandlers.HeaderlessObjectOutputStream;
import Helpers.Role;
import Roles.User;

public class UserController extends ModelController<User>{
    public UserController(){
        super("users");
        read();
    }

    public boolean writeUsertoFile(User newUser) {
		try {
			File userFile = new File(filepath);
			FileOutputStream outputStream = new FileOutputStream(userFile, true);
			ObjectOutputStream writer;
			if (userFile.length() > 0)
				writer = new HeaderlessObjectOutputStream(outputStream);
			else
				writer = new ObjectOutputStream(outputStream); 
			writer.writeObject(newUser);
			writer.close();
			list.add(newUser);
			return true;
		} catch(IOException ex) {
			return false;
		}
	}

    public User checkUserCredentials(String name, String password) throws InvalidCredentials{
        for(User user : list){
            if(user.getName().matches(name) && user.getPassword().matches(password)){
                return user;
            }
        }
        throw new InvalidCredentials();
    }

	public ArrayList<User> getUserList(){
        return list;
    }

	public void registerUser(String name, String lastname, Calendar birthday, String phone, String email, int salary, Role role, String password){
		User newUser = new User(name, lastname, birthday, phone, email, salary, role, password);
		writeUsertoFile(newUser); 
	}

	public void deleteUser(User user){
		list.remove(user);
		try{
			Overwrite();
		}
		catch(IOException ex){
			System.out.print("Failed to overwrite file, user still exists!");
		}
		
	}

	public float getTotalSalaries(){
		float totalsalaries=0;
		for(int i=0;i<list.size();i++){
			totalsalaries += list.get(i).getSalary();
		}
		return totalsalaries;
	}
}
