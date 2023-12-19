package Exceptions;

public class OutOfStockException extends Exception{
    public OutOfStockException(){
        super("Not enough stock!");
    }
}
