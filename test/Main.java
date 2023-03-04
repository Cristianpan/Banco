package test;

import controllers.CtrlUsuarios;

public class Main {
    public static void main(String[] args) {

        CtrlUsuarios ctrlBanco = new CtrlUsuarios(); 
       try {
        ctrlBanco.agregarCliente("1234567891234567", "Cristian David Pan Zaldivar", "1234567891234567", "100");        
        ctrlBanco.generarReporte();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
