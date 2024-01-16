package Controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import Helpers.Author;
import Helpers.Book;
import Helpers.BookInterface;
import Helpers.Gender;

public class BookController extends ModelController<BookInterface>{
    public BookController(File file){
        super(file);
        read();
    }


    public boolean writeBooktoFile(BookInterface newBook) {
        if(checkifBookexists(newBook.getIsbn13())){
            System.out.print("Book already Exists");
			return false;
        }
		try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(objectSaveFile, true))){
			writer.writeObject(newBook);
			writer.close();
			list.add(newBook);
			return true;
		} catch(Exception ex) {
			return false;
		}
	}

    public boolean checkifBookexists(String isbn){
        for(BookInterface book : list){
            if(book.getIsbn13().matches(isbn)){
				return true;
            }
        }
        return false;
    }

    public Author[] getAvailableAuthors() {
		return new Author[] {
				new Author("Fyodor", "Dostoevsky", Gender.MALE),
				new Author("Noam", "Chomsky", Gender.MALE),
				new Author("Anne", "Applebaum", Gender.FEMALE),
				new Author("George", "Orwell", Gender.MALE)
		};
	}
	public String getBookCollection(){
		String collection="";
		for(int i=0;i<list.size();i++){
			String book = list.get(i).StocktoString();
			collection = (collection.concat(book) );
		}
		return collection;
	}
    public ArrayList<BookInterface> getBookList(){
        return list;
    }
	
}
