package Controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import FileHandlers.HeaderlessObjectOutputStream;
import Helpers.Book;
import Helpers.Order;

public class OrderController extends ModelController<Order>{

	public OrderController() {
		super("orders");
		read();
	}


	public boolean writeOrdertoFile(Order newOrder) throws Exception {
		if (!checkifOrderexists(newOrder.getDate())) {
			try {
				File orderFile = new File(filepath);
				FileOutputStream outputStream = new FileOutputStream(orderFile, true);
				ObjectOutputStream writer;
				if (orderFile.length() > 0)
					writer = new HeaderlessObjectOutputStream(outputStream);
				else
					writer = new ObjectOutputStream(outputStream);
				writer.writeObject(newOrder);
				writer.close();
				list.add(newOrder);
				return true;
			} catch (IOException ex) {
				return false;
			}
		}
		else{
			throw new Exception("It already Exists!;");
		}
	}

	public boolean checkifOrderexists(Date date) {
		for (Order order : list) {
			if (order.getDate().compareTo(date) == 0) {
				return true;
			}
		}
		return false;
	}

	public float calculateTotal(Book book, int quantity) {
		return book.getBprice() * quantity;
	}

	public int calculateIdNumber() {
		return list.size();
	}

	public Date orderDate() {
		return Calendar.getInstance().getTime();
	}
	
	public Date setDate(int day, int month, int year) {
		Calendar n = Calendar.getInstance();
		n.set(year, month - 1, day);
		return n.getTime();
	}

	public String printBookRecords(Date start, Date end) {
		String records = "";
		ArrayList<Order> selectedlist = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getDate().getTime() > start.getTime()
					&& list.get(i).getDate().getTime() < end.getTime()) {
				selectedlist.add(list.get(i));
			}
		}
		for (int i = 0; i < selectedlist.size(); i++) {
			records = (records + selectedlist.get(i).getBookbought() + "\t" + selectedlist.get(i).getQuantity()  + "\t" + selectedlist.get(i).getTotal() + "\n");
		}
		return records;
	}

	public float totalBookExpenses(Date start, Date end){
		float income=0;
		ArrayList<Order> selectedlist = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getDate().getTime() > start.getTime()
					&& list.get(i).getDate().getTime() < end.getTime()) {
				selectedlist.add(list.get(i));
			}
		}
		for (int i = 0; i < selectedlist.size(); i++) {
			income += selectedlist.get(i).getTotal();
		}
		return income;
	}
}
