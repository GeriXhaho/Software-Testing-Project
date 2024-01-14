package Helpers;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class BillMock implements BillInterface, Serializable {

    int id;
    Date billdate;
    String billName;

    float total=1500;



    public BillMock(int id, Date date, String name){
        this.id = id;
        this.billdate = date;
        this.billName = name;
    }

    @Override
    public float getTotal() {
        return this.total;
    }

    @Override
    public Date getBilldate() {
        return this.billdate;
    }

    @Override
    public ArrayList<String> getBooklist() {
        ArrayList<String> booklist = new ArrayList<>();
        booklist.add("Test1");
        booklist.add("Test2");
        return booklist;
    }

    @Override
    public ArrayList<Integer> getQuantitylist() {
        ArrayList<Integer> quantityList = new ArrayList<>();
        quantityList.add(6);
        quantityList.add(4);
        return quantityList;
    }

    @Override
    public ArrayList<Float> getPricelist() {
        ArrayList<Float> priceList = new ArrayList<>();
        priceList.add((float)150);
        priceList.add((float)150);
        return priceList;
    }

    @Override
    public String getBillName() {
        return this.billName;
    }

    @Override
    public int getQuantity() {
        return 10;
    }

    @Override
    public int getID() {
        return this.id;
    }

    @Override
    public String getBillText() {
        return "No text";
    }
}
