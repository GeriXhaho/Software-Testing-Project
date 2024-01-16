package Controllers;

import FileHandlers.OverwriteHandler;

import java.io.*;
import java.util.ArrayList;

public abstract class ModelController<T> implements Serializable{

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
			for(int i=0;i<limit;i++) {
				Object obj = reader.readObject();
				list.add((T)obj);

			}
        } catch (EOFException e) {
			System.out.println("Read all the data from file "+filename);
		} catch (Exception e) {
			e.printStackTrace();
		}

    }

    public void Overwrite() throws Exception{
		OverwriteHandler.Overwrite(list, objectSaveFile);
	}
    
}
