package validators;

import java.util.regex.Pattern;

import errors.InvalidDataException;
import utils.Equalizer;

public class ClientValidation {
    public static boolean validarDatosCliente (String idCliente, String nombreCliente) throws InvalidDataException{
        return validarNombre(nombreCliente) && validarIdCliente(idCliente); 
    }
    
    private static boolean validarNombre(String nombreCliente) throws InvalidDataException {
        Pattern patronNombre = Pattern.compile("[a-zA-Z ]+");

        if (!Equalizer.isMatch(nombreCliente, patronNombre)) {
            throw new InvalidDataException(
                    "Caracteres invalidos en nombre de cliente: " + nombreCliente);
        }

        return true;
    }

    private static boolean validarIdCliente(String idCliente) throws InvalidDataException {
        Pattern patronNumCliente = Pattern.compile("[0-9]{16}");
        
        if (!Equalizer.isMatch(idCliente, patronNumCliente)) {
            throw new InvalidDataException(
                    "El no. de cliente" + idCliente + "es erroneo. Verifique que solo contenga 16 valores numericos");
        }

        return true; 
    }
}
