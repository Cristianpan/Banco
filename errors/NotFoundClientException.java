package errors;

public class NotFoundClientException extends Exception {
    public NotFoundClientException(String idCliente) {
        super("Error, no se ha encontrado al cliente: " + idCliente);
    }
}
