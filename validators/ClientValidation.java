package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import errors.InvalidDataException;

public class ClientValidation {
    public boolean validarNombre(String nombreCliente) throws InvalidDataException {
        Pattern patronNombre = Pattern.compile("[a-zA-Z ]+");

        if (!isMatch(nombreCliente, patronNombre)) {
            throw new InvalidDataException(
                    "Caracteres invalidos en nombre de cliente: " + nombreCliente);
        }

        return true;
    }

    public boolean validarIdCliente(String idCliente) throws InvalidDataException {
        Pattern patronNumCliente = Pattern.compile("[0-9]{16}");
        
        if (!isMatch(idCliente, patronNumCliente)) {
            throw new InvalidDataException(
                    "El no. de cliente" + idCliente + "es erroneo. Verifique que solo contenga 16 valores numericos");
        }

        return true; 
    }

    public boolean validarDatosCliente (String idCliente, String nombreCliente) throws InvalidDataException{
        return validarNombre(nombreCliente) && validarIdCliente(idCliente); 
    }


    private boolean isMatch(String cadena, Pattern patron) {
        Matcher mat = patron.matcher(cadena);
        return mat.matches();
    }
}
