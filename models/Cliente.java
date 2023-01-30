package models;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Cliente
 */
public class Cliente {

    private String numeroDeCliente;
    private String nombreCliente;
    private ArrayList<Cuenta> cuentasCliente;

    public Cliente(String numeroDeCliente, String nombreCliente, Cuenta cuentaCliente) {
        this.numeroDeCliente = numeroDeCliente;
        this.nombreCliente = nombreCliente;
        this.cuentasCliente = new ArrayList<>();
        this.cuentasCliente.add(cuentaCliente);
    }

    public void imprimirInformacionCliente() {
        System.out.println("No. Cliente: " + this.numeroDeCliente + ", Nombre de Cliente: " + this.nombreCliente);
        System.out.println("Cuentas");
        for (Cuenta cuentaCliente : cuentasCliente) {
            cuentaCliente.imprimirInformacionCuenta();
        }
    }

    public boolean isMatch(String cadena, Pattern patron) {
        Matcher mat = patron.matcher(cadena);
        return mat.matches();
    }

    public String getNumeroDeCliente() {
        return numeroDeCliente;
    }

    public void setNumeroDeCliente(String numeroDeCliente) {
        this.numeroDeCliente = numeroDeCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public ArrayList<Cuenta> getCuentasCliente() {
        return cuentasCliente;
    }

    public void setCuentaCliente(Cuenta cuentaCliente) {
        this.cuentasCliente.add(cuentaCliente);
    }

}