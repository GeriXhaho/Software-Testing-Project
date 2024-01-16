package Directories.Controllers;


import java.io.*;
import java.util.ArrayList;

public abstract class ModelController<T> {

	protected File objectSaveFile;
    public ArrayList<T> list;
    protected String filename;

    protected ModelController(File file){
        list = new ArrayList<>();
        this.filename = (file != null) ? file.getName() : null;
        this.objectSaveFile = file;

    }
    public void read(){
        list = new ArrayList<>();
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
    public void Overwrite() throws IOException{
        ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(objectSaveFile.getPath(), false));
        for (T t : list) {
            writer.writeObject(t);
        }
        writer.close();
    }
    
}
