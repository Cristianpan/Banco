package controllers;

import daos.*;
import errors.*;
import models.*;
import validators.*;

public class CtrlUsuarios {
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

    public Cliente obtenerCliente (String idCliente) throws NotFoundClientException {
        DaoClientes daoClientes = new DaoClientes(); 
        return daoClientes.obtenerCliente(idCliente); 
    }

    public int modificarCliente(String idCliente, String nombreCliente)
            throws NotFoundClientException, InvalidDataException {
        DaoClientes daoClientes = new DaoClientes();
        ClientValidation clientValidation = new ClientValidation();

        clientValidation.validarDatosCliente(idCliente, nombreCliente); 
        daoClientes.actualizarCliente(idCliente, nombreCliente);

        return 1;
    }

    public void generarReporte() throws NotFoundClientException {
        DaoClientes daoClientes = new DaoClientes();
        ReporteCliente reporteCliente = new ReporteCliente(); 
        reporteCliente.generarReporte(daoClientes.obtenerClientes());
    }

}

