package Controllers;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;

import Exceptions.InvalidCredentials;
import Helpers.Role;
import Roles.User;
import Roles.UserInterface;

public class UserController extends ModelController<UserInterface>{
    public UserController(File file){
        super(file);
        read();
    }

    public boolean writeUsertoFile(UserInterface newUser) {
		try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(objectSaveFile, true))){
			writer.writeObject(newUser);
			list.add(newUser);
			return true;
		} catch(Exception ex) {
			return false;
		}
	}

	public UserInterface checkUserCredentials(String name, String password) throws InvalidCredentials{
        for(UserInterface user : list){
            if(user.getName().matches(name) && user.getPassword().matches(password)){
                return user;
            }
        }
        throw new InvalidCredentials();
    }

	public ArrayList<UserInterface> getUserList(){
        return list;
    }

	public void registerUser(UserInterface newUser) {
		writeUsertoFile(newUser);
	}


	public boolean deleteUser(UserInterface user) {
		try{
			list.remove(user);
			Overwrite();
		} catch(Exception ex){
			System.out.print("Failed to overwrite file, user still exists!");
			return false;
		}
		return true;
	}

	public float getTotalSalaries() {
		float totalsalaries=0;
		for(int i=0;i<list.size();i++){
			totalsalaries += list.get(i).getSalary();
		}
		return totalsalaries;
	}
}
