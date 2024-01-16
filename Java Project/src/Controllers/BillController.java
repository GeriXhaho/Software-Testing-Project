package Controllers;

import FileHandlers.HeaderlessObjectOutputStream;
import Helpers.Bill;
import Helpers.BillInterface;
import Helpers.Book;
import Helpers.BookInterface;
import Roles.User;
import Roles.UserInterface;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class BillController extends ModelController<BillInterface> {
	private int totalBooks = 0;
	private float totalSold = 0;

	public BillController(File file) {
		super(file);
		read();
	}

	public boolean writeBilltoFile(BillInterface newBill) {
		if (newBill.getTotal() == 0)
			return false;
		try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(objectSaveFile, true))){
			writer.writeObject(newBill);
			writer.close();
			list.add(newBill);
			return true;
		} catch (Exception ex) {
			return false;
		}
	}

	public boolean writeBilltoTxt(BillInterface newBill) {
		try {
			int billid = newBill.getID();
			File billFile = new File(
					"D:\\Apps\\Project\\Software-Testing-Project\\Java Project\\src\\Files\\Printed Bills\\Bill_" + billid + ".txt");
			PrintWriter writer = new PrintWriter(billFile);
			writer.write(newBill.getBillText());
			writer.close();
			return true;
		} catch (IOException ex) {
			return false;
		}
	}

	public String billtoString(BookInterface book, int quantity) {
		return (book.getTitle() + " by " + book.getAuthor().toString() + "\t\t\t" + quantity + " x "
				+ book.getSPrice() + " leke\n");
	}

	public int calculateIdNumber() {
		return list.size();
	}

	public Date billDate() {
		return Calendar.getInstance().getTime();
	}

	public Date setDate(int day, int month, int year) {
		Calendar n = Calendar.getInstance();
		month = month - 1;
		n.set(year, month , day);
		return n.getTime();
	}

	public String printLibrarianRecords(Date start, Date end, ArrayList<UserInterface> userlist) {
		String records = "";
		ArrayList<BillInterface> selectedlist = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getBilldate().getTime() > start.getTime()
					&& list.get(i).getBilldate().getTime() < end.getTime()) {
				selectedlist.add(list.get(i));
			}
		}
		for (int j = 0; j < userlist.size(); j++) {
			records = records.concat(userlist.get(j).getName());
			records = records.concat("\t");
			int quantity = 0;
			float total = 0;
			int numberOfBills = 0;
			for (int k = 0; k < selectedlist.size(); k++) {
				if (userlist.get(j).getName().matches(selectedlist.get(k).getBillName())) {
					quantity += selectedlist.get(k).getQuantity();
					total += selectedlist.get(k).getTotal();
					numberOfBills++;
				}
			}
			totalBooks += quantity;
			totalSold += total;
			records = (records + numberOfBills + "\t\t" + quantity + "\t\t\t" + total + " $\n");
			if (selectedlist.isEmpty()) {
				records = "";
			}
		}
		return records;
	}

	public String totalRecords() {
		return ("" + totalBooks + " Books, " + totalSold + "$");
	}

	public String printBookRecords(Date start, Date end) {
		String records = "";
		ArrayList<BillInterface> selectedlist = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getBilldate().getTime() > start.getTime()
					&& list.get(i).getBilldate().getTime() < end.getTime()) {
				selectedlist.add(list.get(i));
			}
		}
		for (int i = 0; i < selectedlist.size(); i++) {
			for (int j = 0; j < selectedlist.get(i).getBooklist().size(); j++) {
				records = records.concat(selectedlist.get(i).getBooklist().get(j));
				records = records.concat("\t");
				records = records.concat(String.valueOf(selectedlist.get(i).getQuantitylist().get(j)));
				records = records.concat( "\t");
				records = records.concat(String.valueOf(selectedlist.get(i).getPricelist().get(j) * selectedlist.get(i).getQuantitylist().get(j)));
				records = records.concat("\n");
			}
		}
		return records;
	}

	public float totalbookIncome(Date start, Date end) {
		float income = 0;
		ArrayList<BillInterface> selectedlist = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getBilldate().getTime() > start.getTime()
					&& list.get(i).getBilldate().getTime() < end.getTime()) {
				selectedlist.add(list.get(i));
			}
		}
		for (int i = 0; i < selectedlist.size(); i++) {
			for (int j = 0; j < selectedlist.get(i).getBooklist().size(); j++) {
				income += selectedlist.get(i).getPricelist().get(j) * selectedlist.get(i).getQuantitylist().get(j);
			}
		}
		return income;
	}
}
