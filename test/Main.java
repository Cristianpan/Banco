package test;

import controllers.CtrlClientes;

public class Main {
    public static void main(String[] args) {

        CtrlClientes ctrlBanco = new CtrlClientes(); 
       try {
        ctrlBanco.agregarCliente("1234567891234567", "Cristian David Pan Zaldivar", "1234567891234567", "100");        
        ctrlBanco.generarReporte();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
