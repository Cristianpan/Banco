package models;

import java.io.Serializable;

/**
 * Cuenta
 */

public class Cuenta implements Serializable {
    private String numeroDeCuenta;
    private float saldo;

    public Cuenta(String numeroDeCuenta, float saldo) {
        this.numeroDeCuenta = numeroDeCuenta;
        this.saldo = saldo;
    }

    public String obtenerInformacionCuenta() {

        return "No. cuenta: " + this.numeroDeCuenta + "               Saldo: $" + saldo + "\n";
    }

    // funcionalidades para numero de cuenta
    public void setNumeroDeCuenta(String numeroDeCuenta) {
        this.numeroDeCuenta = numeroDeCuenta;
    }

    public String getNumeroDeCuenta() {
        return numeroDeCuenta;
    }

    // funcionalidades para saldo
    public void setSaldo(float saldo) {
        this.saldo += saldo;
    }

    public float getSaldo() {
        return saldo;
    }
}