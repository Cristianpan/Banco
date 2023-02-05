package errors; 

/**
 * ExistAccountException
 */
public class ExistAccountException extends Exception {
    public ExistAccountException (String noCuenta){
        super("Error, el no. de cuenta: " + noCuenta + " ya ha sido registrada."); 
    }    
}