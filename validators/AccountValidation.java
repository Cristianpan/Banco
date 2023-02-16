package validators;
import java.util.regex.Pattern;

import errors.InvalidDataException;
import utils.Equalizer;

public class AccountValidation {
    public boolean validarCuenta (String numeroDeCuenta) throws InvalidDataException { 
        Pattern patronNumeroDeCuenta = Pattern.compile("[0-9]{16}");

        if (!Equalizer.isMatch(numeroDeCuenta, patronNumeroDeCuenta)) {
            throw new InvalidDataException(
                    "El dato no. de cuenta es erroneo, verifique que solo contenga 16 valores numericos");
        }

        return true; 

    }
    
}
