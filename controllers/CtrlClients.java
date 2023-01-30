package controllers;

import java.util.ArrayList;

import daos.DaoCuentaCliente;
import errors.ExistAccountException;
import errors.InvalidDataException;
import errors.NumberClientException;
import models.*;
import validators.AccountValidation;
import validators.ClientValidation;

public class CtrlClients {

    public String agregarRegistro (String numeroDeCliente, String nombreCliente, String numeroDeCuenta, String saldo) throws InvalidDataException, NumberFormatException, ExistAccountException, NumberClientException {
        DaoCuentaCliente daoCuentaCliente = new DaoCuentaCliente(); 
        AccountValidation accountValidation = new AccountValidation(); 
        ClientValidation clientValidation = new ClientValidation();  
        accountValidation.validarCuenta(numeroDeCuenta); 
        clientValidation.validarDatosCliente(nombreCliente, numeroDeCliente);
        
        return daoCuentaCliente.agregarRegistro(new Cliente(numeroDeCliente, nombreCliente, new Cuenta(numeroDeCuenta, Float.parseFloat(saldo)))); 
    }

    public String agregarRegistro (String numeroDeCliente, String numeroDeCuenta, String Saldo) throws InvalidDataException, NumberFormatException, NumberClientException, ExistAccountException {

        DaoCuentaCliente daoCuentaCliente = new DaoCuentaCliente(); 
        AccountValidation accountValidation = new AccountValidation(); 

        accountValidation.validarCuenta(numeroDeCuenta); 

        return daoCuentaCliente.agregarRegistro(numeroDeCliente, new Cuenta(numeroDeCuenta, Float.parseFloat(Saldo))); 
    }


    public ArrayList<Cliente> obtenerRegistros () {
        DaoCuentaCliente daoCuentaCliente = new DaoCuentaCliente(); 

        return daoCuentaCliente.obtenerRegistros(); 
    }
}
