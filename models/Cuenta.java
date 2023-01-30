package models;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Cuenta
 */
public class Cuenta {
    private String numeroDeCuenta;
    private float saldo;

    public Cuenta(String numeroDeCuenta, float saldo) {
        this.numeroDeCuenta = numeroDeCuenta;
        this.saldo = saldo;
    }

    public void imprimirInformacionCuenta() {
        System.out.println("No. cuenta: " + this.numeroDeCuenta + ", Saldo: $" + saldo);
    }

    public boolean isMatch(String cadena, Pattern patron) {
        Matcher mat = patron.matcher(cadena);
        return mat.matches();
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
        this.saldo = saldo;
    }

    public float getSaldo() {
        return saldo;
    }
}