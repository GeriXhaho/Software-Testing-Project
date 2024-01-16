package Directories.Helpers;
import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class Bill implements BillInterface,Serializable{
    @Serial
    private static final long serialVersionUID = 2L;
    private int id;
    private Date billdate;
    private float totalamount;
    private String billBody;
    private String billTotal;
    private String billName;
    private int totalQuantity;
    private ArrayList<String> booklist;
    private ArrayList<Integer> quantitylist;
    private ArrayList<Float> pricelist;

    

    public Bill(int id, Date date, String name){
        this.id = id;
        this.billdate = date;
        this.totalamount = 0;
        this.billBody = ("Bill_ID "+this.id+"\t\t\t\t\t"+this.billdate+"\n\n");
        this.billTotal = ("Total:"+"\t"+this.totalamount);
        this.billName = name;
        this.totalQuantity = 0;
        this.booklist = new ArrayList<>();
        this.quantitylist = new ArrayList<>();
        this.pricelist = new ArrayList<>();


    }

    public void addTotal(float price, int quantity){
        this.pricelist.add(price);
        this.quantitylist.add(quantity);
        this.totalamount += (price*quantity);
        this.billTotal = ("\nTotal:"+"\t"+this.totalamount);
        this.totalQuantity += quantity;
    }
    public float getTotal(){
        return this.totalamount;
    }
    public void addBillText(String billBody) {
        this.billBody = (this.billBody + billBody);
    }
    public String getBillText() {
        return billBody+billTotal+("\nIssued by: "+billName);
    }

    public int getID(){
        return this.id;
    }

    public Date getBilldate() {
        return billdate;
    }

    public String getBillName() {
        return billName;
    }
    
    public int getQuantity(){
        return totalQuantity;
    }

    public void addBook(String book){
        booklist.add(book);
    }
    
    public ArrayList<String> getBooklist() {
        return booklist;
    }

    public ArrayList<Integer> getQuantitylist() {
        return quantitylist;
    }

    public ArrayList<Float> getPricelist() {
        return pricelist;
    }

    
}
