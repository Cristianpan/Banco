package errors;

public class NotFoundAccountOfClientException extends Exception {
    public NotFoundAccountOfClientException (String idCliente, String numCuenta) {
        super("Error, el no. de cuenta: " + numCuenta
        + ", no corresponde al no. de cliente: " + idCliente); 
    }
    
}
