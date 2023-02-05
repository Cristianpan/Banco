package validators;

import java.util.ArrayList;

import models.Cuenta;

public class BalanceValidation {
    public static boolean validarSaldo(Cuenta cuentaCliente) {
        return cuentaCliente.getSaldo() >= 0; 
    }

    public static boolean validarSaldo (ArrayList<Cuenta> cuentasCliente) {
        for (Cuenta cuentaCliente : cuentasCliente) {
            if (cuentaCliente.getSaldo() >= 0) {
                return true; 
            }
        }
        return false; 
    }
}
