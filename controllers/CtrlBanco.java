package controllers;

import java.util.ArrayList;

import daos.DaoClientes;
import daos.DaoCuentas;
import errors.DeleteException;
import errors.ExistAccountException;
import errors.InvalidDataException;
import errors.NotFoundClientException;
import errors.NumberClientException;
import models.Cliente;
import models.Cuenta;
import utils.Ereaser;
import validators.AccountValidation;
import validators.ClientValidation;

public class CtrlBanco {
    public void agregarCliente(String idCliente, String nombreCliente, String noCuenta, String saldo)
            throws InvalidDataException, NumberFormatException, NumberClientException, ExistAccountException {
        AccountValidation accountValidation = new AccountValidation();
        ClientValidation clientValidation = new ClientValidation();

        clientValidation.validarDatosCliente(idCliente, nombreCliente);
        accountValidation.validarCuenta(noCuenta);

        DaoClientes daoClientes = new DaoClientes();
        DaoCuentas daoCuentas = new DaoCuentas();

        if (daoClientes.existeIdCliente(idCliente) >= 0) {
            throw new NumberClientException(
                    "Error al hacer el registro. El no. de cliente " + idCliente + " ya ha sido asignado");

        } else if (daoCuentas.existeCuenta(noCuenta)) {
            throw new ExistAccountException("Error al hacer el registro. El numero de cuenta " + noCuenta
                    + " ya ha sido asignado a un cliente");
        } else {
            daoCuentas.agregarCuentaCliente(idCliente, new Cuenta(noCuenta, Float.parseFloat(saldo)));
            daoClientes.agregarCliente(idCliente, nombreCliente);
            System.out.println("Registro con exito");
        }
    }

    public void agregarCuentaCliente(String idCliente, String noCuenta, String saldo)
            throws InvalidDataException, NumberFormatException, ExistAccountException {
        AccountValidation accountValidation = new AccountValidation();
        accountValidation.validarCuenta(noCuenta);

        DaoCuentas daoCuentas = new DaoCuentas();

        if (!daoCuentas.existeCuenta(noCuenta)) {
            daoCuentas.agregarCuentaCliente(idCliente, new Cuenta(noCuenta, Float.parseFloat(saldo)));
            System.out.println("Nueva cuenta agrega al cliente: " + idCliente);
        } else {
            throw new ExistAccountException(
                    "La cuenta " + noCuenta + "ya ha sido registrada. Ingrese otro numero de cuenta");
        }
    }

    public void eliminarCuenta(String idCliente, String noCuenta) throws ExistAccountException, DeleteException {
        DaoCuentas daoCuentas = new DaoCuentas();
        Cuenta cuentaCliente = daoCuentas.obtenerCuenta(idCliente, noCuenta);

        if (cuentaCliente.getSaldo() <= 0) {
            if (daoCuentas.eliminarCuentaCLiente(idCliente, noCuenta) == 0) {
                eliminarHistorial(idCliente);
            }

            System.out.println("La cuenta: " + noCuenta + "ha sido eliminada del cliente: " + idCliente);
        } else {
            throw new DeleteException("Error al eliminar la cuenta" + noCuenta
                    + ". Por favor retire todos los fondos e intente de nuevo.");
        }
    }

    public void elimarCliente(String idCliente) throws NotFoundClientException, DeleteException {
        DaoClientes daoClientes = new DaoClientes();
        Cliente cliente = daoClientes.obtenerCliente(idCliente);

        if (!validarSaldo(cliente.getCuentasCliente())) {
            eliminarHistorial(idCliente);
            System.out.println("El cliente ha sido eliminado con exito");
        } else {
            throw new DeleteException(
                    "Error al eliminar al cliente.\nTiene una o mas cuentas con fondos. Por favor retirelos e intente nuevamente");
        }
    }

    public void modificarCliente(String idCliente, String nombreCliente) throws NotFoundClientException {
        DaoClientes daoClientes = new DaoClientes();

        daoClientes.actualizarCliente(idCliente, nombreCliente);
        System.out.println("Nombre del cliente actualizado");
    }

    public void añadirSaldoCuenta(String idCliente, String noCuenta, String deposito)
            throws ExistAccountException, NumberFormatException {
        DaoCuentas daoCuentas = new DaoCuentas();
        Cuenta cuenta = daoCuentas.obtenerCuenta(idCliente, noCuenta);

        cuenta.setSaldo(Float.parseFloat(deposito));

        daoCuentas.actualizarCuentaCliente(idCliente, cuenta);
        System.out.println("Actualización de saldo de cuenta exitoso");
    }

    public void generarReporte() {
        DaoClientes daoClientes = new DaoClientes();

        for (Cliente cliente : daoClientes.obtenerClientes()) {
            cliente.imprimirInformacionCliente();
        }
    }

    private boolean validarSaldo(ArrayList<Cuenta> cuentasCliente) {

        for (Cuenta cuenta : cuentasCliente) {
            if (cuenta.getSaldo() > 0) {
                return true;
            }
        }

        return false;
    }

    private void eliminarHistorial(String idCliente) {
        Ereaser.DeleteFile("./data/cuentas/" + idCliente);
        Ereaser.DeleteFile("./data/clientes/" + idCliente + ".dat");
    }
}
