package Helpers;

import java.io.Serializable;
import java.util.Date;

public class Order implements OrderInterface,Serializable{
    private int id;
    private Date orderDate;
    private float orderTotal;
    private int orderQuantity;
    private String orderName;
    private String bookbought;

    public Order(int id, Date date, String name, String bookbought){
        this.id = id;
        this.orderDate = date;
        this.orderTotal = 0;
        this.orderName = name;
        this.bookbought = bookbought;


    }

    @Override
    public String toString() {
        return ("Order_"+this.id+" issued by "+this.orderName + ", Total: "+this.orderTotal);
    }
    public void addQuantity(int quantity) {
        this.orderQuantity += quantity;
    }

    public void addTotal(float total) {
        this.orderTotal += total;
    }

    public int getQuantity(){
        return orderQuantity;
    }

    public float getTotal(){
        return orderTotal;
    }

    public Date getDate(){
        return orderDate;
    }
    public String getBookbought() {
        return bookbought;
    }
}
