package Exceptions;

public class InvalidCredentials extends Exception{
    public InvalidCredentials(){
        super("Invalid Credentials or user does not exist");
    }
}
