package controllers;

import daos.*;
import errors.*;
import models.*;
import validators.*;

public class CtrlBanco {
    public int agregarCliente(String idCliente, String nombreCliente, String noCuenta, String saldo)
            throws InvalidDataException, NumberFormatException, ExistNumberClientException, ExistAccountException {

        AccountValidation accountValidation = new AccountValidation();
        ClientValidation clientValidation = new ClientValidation();

        clientValidation.validarDatosCliente(idCliente, nombreCliente);
        accountValidation.validarCuenta(noCuenta);

        DaoClientes daoClientes = new DaoClientes();
        DaoCuentas daoCuentas = new DaoCuentas();

        if (daoClientes.existeIdCliente(idCliente)) {
            throw new ExistNumberClientException(idCliente);

        } else if (daoCuentas.existeCuenta(noCuenta)) {
            throw new ExistAccountException(noCuenta);

        } else {
            daoCuentas.agregarCuentaCliente(idCliente, new Cuenta(noCuenta, Float.parseFloat(saldo)));
            daoClientes.agregarCliente(idCliente, nombreCliente);
        }

        return 1;
    }

    public int agregarCuentaCliente(String idCliente, String noCuenta, String saldo)
            throws InvalidDataException, NumberFormatException, ExistAccountException, NotFoundClientException {

        AccountValidation accountValidation = new AccountValidation();
        accountValidation.validarCuenta(noCuenta);

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

    public int eliminarCuenta(String idCliente, String noCuenta)
            throws DeleteException, NotFoundAccountOfClient, NotFoundClientException {
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

    public int elimarCliente(String idCliente) throws NotFoundClientException, DeleteException {
        DaoClientes daoClientes = new DaoClientes();
        Cliente cliente = daoClientes.obtenerCliente(idCliente);

        if (!BalanceValidation.validarSaldo(cliente.getCuentasCliente())) {
            daoClientes.eliminarCliente(idCliente);
        } else {
            throw new DeleteException(
                    "Error al eliminar al cliente.\nTiene una o mas cuentas con fondos. Por favor retirelos e intente nuevamente");
        }

        return 1;
    }

    public int modificarCliente(String idCliente, String nombreCliente)
            throws NotFoundClientException, InvalidDataException {
        DaoClientes daoClientes = new DaoClientes();
        ClientValidation clientValidation = new ClientValidation();

        clientValidation.validarDatosCliente(idCliente, nombreCliente); 
        daoClientes.actualizarCliente(idCliente, nombreCliente);

        return 1;
    }

    public int añadirSaldoCuenta(String idCliente, String noCuenta, String deposito)
            throws ExistAccountException, NumberFormatException, NotFoundAccountOfClient {
        DaoCuentas daoCuentas = new DaoCuentas();
        Cuenta cuenta = daoCuentas.obtenerCuenta(idCliente, noCuenta);

        cuenta.setSaldo(Float.parseFloat(deposito));

        daoCuentas.actualizarCuentaCliente(idCliente, cuenta);
        return 1;
    }

    public void generarReporte() {
        DaoClientes daoClientes = new DaoClientes();
        ReporteCliente reporteCliente = new ReporteCliente(); 
        reporteCliente.generarReporte(daoClientes.obtenerClientes());
    }

}

