package validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import errors.InvalidDataException;

public class AccountValidation {
    public boolean validarCuenta (String numeroDeCuenta) throws InvalidDataException { 
        Pattern patronNumeroDeCuenta = Pattern.compile("[0-9]{16}");

        if (!isMatch(numeroDeCuenta, patronNumeroDeCuenta)) {
            throw new InvalidDataException(
                    "El dato no. de cuenta es erroneo, verifique que solo contenga 16 valores numericos");
        }

        return true; 

    }
    
    private boolean isMatch(String cadena, Pattern patron) {
        Matcher mat = patron.matcher(cadena);
        return mat.matches();
    }
}
