package models;

import java.util.ArrayList;


/**
 * Cliente
 */
public class Cliente {

    private String idCliente;
    private String nombreCliente;
    private ArrayList<Cuenta> cuentasCliente;

    public Cliente(String[] datosCliente , Cuenta cuentaCliente) {
        this.idCliente = datosCliente[0];
        this.nombreCliente = datosCliente[1];
        this.cuentasCliente = new ArrayList<>();
        this.cuentasCliente.add(cuentaCliente);
    }


    public Cliente(String[] datosCliente, ArrayList<Cuenta> cuentasCliente) {
        this.idCliente = datosCliente[0];
        this.nombreCliente = datosCliente[1];
        this.cuentasCliente = cuentasCliente; 
    }

    public void imprimirInformacionCliente() {
        System.out.println("No. Cliente: " + this.idCliente + ", Nombre de Cliente: " + this.nombreCliente);
        System.out.println("Cuentas");
        for (Cuenta cuentaCliente : cuentasCliente) {
            cuentaCliente.imprimirInformacionCuenta();
        }
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

    public String getIdCliente() {
        return idCliente;
    }

}