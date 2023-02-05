package errors;

public class NotFoundAccountOfClient extends Exception {
    public NotFoundAccountOfClient (String idCliente, String numCuenta) {
        super("Error, el no. de cuenta: " + numCuenta
        + ", no corresponde al no. de cliente: " + idCliente); 
    }
    
}
