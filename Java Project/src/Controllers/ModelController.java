package Controllers;

import FileHandlers.OverwriteHandler;

import java.io.*;
import java.util.ArrayList;

public abstract class ModelController<T> {

	protected File objectSaveFile;
    public ArrayList<T> list;
    protected String filename;
    protected String filepath;

    protected ModelController(File file){
        list = new ArrayList<>();
        this.filename = (file != null) ? file.getName() : null;
        this.objectSaveFile = file;

    }
    public void read(){
		int limit = 20000;
        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(objectSaveFile.getPath()))) {
			T obj;
            list = new ArrayList<>();
			for(int i=0;i<limit;i++) {
				obj = (T)reader.readObject();
				list.add(obj);
			}
        } catch (EOFException e) {
			System.out.println("Read all the data from file "+filename);
		} catch (Exception e) {
			System.out.print(e);
		}

    }

    public void Overwrite() throws Exception{
		OverwriteHandler.Overwrite(list, objectSaveFile);
	}
    
}
