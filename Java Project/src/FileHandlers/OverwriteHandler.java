package FileHandlers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class OverwriteHandler {

    public static<T> void Overwrite(ArrayList<T> objectlist, File filepath) throws IOException{
		ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(filepath, false));
        for (T t : objectlist) {
            writer.writeObject(t);
        }
		writer.close();
    }
}
