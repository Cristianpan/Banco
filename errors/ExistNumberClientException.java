package errors;

public class ExistNumberClientException extends Exception {

    public ExistNumberClientException (String idCliente) {
        super("Error, el no. de cliente" + idCliente + " ya ha sido registrado en el sistema"); 
    }
    
}
