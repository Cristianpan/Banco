package controllers;

import java.util.ArrayList;
import daos.DaoClientes;
import daos.DaoCuentas;
import errors.DeleteException;
import errors.ExistAccountException;
import errors.InvalidDataException;
import errors.NotFoundAccountOfClientException;
import errors.NotFoundClientException;
import models.Cuenta;
import validators.AccountValidation;
import validators.BalanceValidation;

public class CtrlCuentas {
    public int agregarCuentaCliente(String idCliente, String noCuenta, String saldo)
            throws InvalidDataException, NumberFormatException, ExistAccountException, NotFoundClientException {

        AccountValidation.validarCuenta(noCuenta);

        DaoCuentas daoCuentas = new DaoCuentas();
        DaoClientes daoClientes = new DaoClientes();

        if (daoClientes.existeIdCliente(idCliente)) {
            if (!daoCuentas.existeCuenta(noCuenta)) {
                daoCuentas.agregarCuentaCliente(idCliente, new Cuenta(noCuenta, Float.parseFloat(saldo)));
            } else {
                throw new ExistAccountException(noCuenta);
            }
        } else {
            throw new NotFoundClientException(idCliente);
        }

        return 1;
    }

    public ArrayList<Cuenta> obtenerCuentasCliente (String idCliente) throws NotFoundClientException {
        DaoCuentas daoCuentas = new DaoCuentas(); 
        return daoCuentas.obtenerCuentasPorCliente(idCliente); 
    }

    public Cuenta obtenerCuentaCliente (String idCliente, String numCuenta) throws NotFoundAccountOfClientException {
        DaoCuentas daoCuentas = new DaoCuentas(); 

        return daoCuentas.obtenerCuenta(idCliente, numCuenta); 
    }

    public int eliminarCuentaCLiente(String idCliente, String noCuenta)
            throws DeleteException, NotFoundAccountOfClientException, NotFoundClientException {
        DaoCuentas daoCuentas = new DaoCuentas();
        Cuenta cuentaCliente = daoCuentas.obtenerCuenta(idCliente, noCuenta);

        if (!BalanceValidation.validarSaldo(cuentaCliente)) {
            if (daoCuentas.eliminarCuentaCLiente(idCliente, noCuenta) == 0) {
                new DaoClientes().eliminarCliente(idCliente);
            }
        } else {
            throw new DeleteException("Error al eliminar la cuenta" + noCuenta
                    + ". Por favor retire todos los fondos e intente de nuevo.");
        }

        return 1;
    }

    public int agregarSaldoCuenta(String idCliente, String noCuenta, String deposito)
            throws ExistAccountException, NumberFormatException, NotFoundAccountOfClientException {
        DaoCuentas daoCuentas = new DaoCuentas();
        Cuenta cuenta = daoCuentas.obtenerCuenta(idCliente, noCuenta);

        cuenta.setSaldo(Float.parseFloat(deposito));

        daoCuentas.actualizarCuentaCliente(idCliente, cuenta);
        return 1;
    }
}
