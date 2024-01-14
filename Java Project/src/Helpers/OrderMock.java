package Helpers;

import java.io.Serializable;
import java.util.Date;

public class OrderMock implements OrderInterface, Serializable {

    @Override
    public Date getDate() {
        return null;
    }

    @Override
    public String getBookbought() {
        return null;
    }

    @Override
    public int getQuantity() {
        return 0;
    }

    @Override
    public float getTotal() {
        return 0;
    }
}
