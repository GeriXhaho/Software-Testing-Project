package FileHandlers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class OverwriteHandler {

    public static<T> void Overwrite(ArrayList<T> objectlist, File filepath) throws IOException{
        FileOutputStream outputStream = new FileOutputStream(filepath, false);
		ObjectOutputStream writer;
		writer = new ObjectOutputStream(outputStream);
		writer.writeObject(objectlist.get(0));
        writer = new HeaderlessObjectOutputStream(outputStream);
        for(int i=1 ;i<objectlist.size();i++){
            writer.writeObject(objectlist.get(i));
        }
		writer.close();
    }
}
