package Helpers;

import java.util.Date;

public interface OrderInterface {
    Date getDate();

    String getBookbought();

    int getQuantity();

    float getTotal();

    void addTotal(float total);

    void addQuantity(int i);
    //methods to be added here
}
