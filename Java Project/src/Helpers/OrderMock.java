package Helpers;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

public class OrderMock implements OrderInterface, Serializable {
    Date orderDate;
    public OrderMock(Date date){
        this.orderDate = date;
    }
    @Override
    public Date getDate() {
        return this.orderDate;
    }

    @Override
    public String getBookbought() {
        return "Geri Kevi Hoxha";
    }

    @Override
    public int getQuantity() {
        return 15;
    }

    @Override
    public float getTotal() {
        return 1500;
    }

    @Override
    public void addTotal(float total) {

    }

    @Override
    public void addQuantity(int i) {

    }
}
