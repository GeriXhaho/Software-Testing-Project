package Directories.Controllers;

import java.io.*;
import java.util.ArrayList;

import Directories.Helpers.Author;
import Directories.Helpers.BookInterface;
import Directories.Helpers.Gender;
import Directories.Helpers.Author;

public class BookController extends ModelController<BookInterface> {
    public BookController(File file){
        super(file);
        read();
    }


    public boolean writeBooktoFile(BookInterface newBook) {
        if(checkifBookexists(newBook.getIsbn13())){
            System.out.println("Book already Exists");
			return false;
        }
		read();
		list.add(newBook);
		try {
			Overwrite();
			return true;
		} catch(Exception ex) {
			list.remove(newBook);
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
