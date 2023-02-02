package test;

import controllers.CtrlBanco;

public class CuentaBanco {
    public static void main(String[] args) {

        CtrlBanco ctrlBanco = new CtrlBanco(); 


       try {
            //ctrlBanco.agregarCliente("1234567891234588", "Diana Carolina Vazquez Rodriguez", "1234567891234568", "0");
            //ctrlBanco.añadirSaldoCuenta("1234567891234567", "1234567891234567", "200");    
            
            //ctrlBanco.agregarCuentaCliente( "1234567891234567","1234567891234588", "100");
            ctrlBanco.modificarCliente("1234567891234567", "Diana Carolina Vazquez");
            ctrlBanco.generarReporte();
            
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
