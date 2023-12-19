package Controllers;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import FileHandlers.OverwriteHandler;

public abstract class ModelController<T> {
    protected ArrayList<T> list;
    protected String filename;
    protected String filepath;

    protected ModelController(String filename){
        list = new ArrayList<>();
        this.filename = filename;
        this.filepath = ("D:\\Programming\\Java Project\\Java Project\\src\\Files\\"+filename+".dat");

    }
    public void read(){
        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(filepath))) {
			T obj;
			while(true) {
				obj = (T)reader.readObject();
				list.add(obj);
			}
		} catch (EOFException e) {
			System.out.println("Read all the " + filename + " from the file");
		} catch (ClassNotFoundException e) {
			System.out.print(e);
		} catch(IOException e) {
			System.out.print(e);
		}
    }

    public void Overwrite() throws IOException{
		File overwrittenFile = new File(filepath);
		OverwriteHandler.Overwrite(list, overwrittenFile);
	}
    
}
