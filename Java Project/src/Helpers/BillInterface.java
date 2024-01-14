package Helpers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface BillInterface {
    float getTotal();

    Date getBilldate();

    ArrayList<String> getBooklist();

    ArrayList<Integer> getQuantitylist();

    ArrayList<Float> getPricelist();

    String getBillName();

    int getQuantity();

    int getID();

    String getBillText();
}
