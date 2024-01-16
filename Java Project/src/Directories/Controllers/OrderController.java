package Directories.Controllers;

import Directories.Helpers.BookInterface;
import Directories.Helpers.OrderInterface;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class OrderController extends ModelController<OrderInterface>{

	public OrderController(File file) {
		super(file);
		read();
	}


	public boolean writeOrdertoFile(OrderInterface newOrder) throws Exception {
		if (!checkifOrderexists(newOrder.getDate())) {
			read();
			list.add(newOrder);
			try {
				Overwrite();
				return true;
			} catch(Exception ex) {
				list.remove(newOrder);
				return false;
			}
		}
		else{
			throw new Exception("It already Exists!;");
		}
	}

	public boolean checkifOrderexists(Date date) {
		for (OrderInterface order : list) {
			if (order.getDate().compareTo(date) == 0) {
				return true;
			}
		}
		return false;
	}

	public float calculateTotal(BookInterface book, int quantity) {
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
		month = month - 1;
		n.set(year, month, day);
		return n.getTime();
	}

	public String printBookRecords(Date start, Date end) {
		String records = "";
		ArrayList<OrderInterface> selectedlist = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getDate().getTime() > start.getTime()
					&& list.get(i).getDate().getTime() < end.getTime()) {
				selectedlist.add(list.get(i));
			}
		}
		for (int i = 0; i < selectedlist.size(); i++) {
			records = records.concat(selectedlist.get(i).getBookbought());
			records = records.concat("\t");
			records = records.concat(String.valueOf(selectedlist.get(i).getQuantity()));
			records = records.concat ("\t");
			records = records.concat(String.valueOf(selectedlist.get(i).getTotal()));
			records = records.concat("\n");
		}
		return records;
	}

	public float totalBookExpenses(Date start, Date end){
		float income=0;
		ArrayList<OrderInterface> selectedlist = new ArrayList<>();
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
