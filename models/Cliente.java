package models;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Cliente
 */
public class Cliente implements Serializable{

    private String idCliente;
    private String nombreCliente;
    private ArrayList<Cuenta> cuentasCliente;

    public Cliente(String[] datosCliente, Cuenta cuentaCliente) {
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

    public String ObtenerDatosCliente() {
        return "No. Cliente: " + this.idCliente + "               Nombre: " + this.nombreCliente + "\n";
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