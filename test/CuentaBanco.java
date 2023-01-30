package test;

import controllers.CtrlClients;

public class CuentaBanco {
    public static void main(String[] args) {

        CtrlClients ctrlClients = new CtrlClients(); 
        String msg; 
        try {
            msg = ctrlClients.agregarRegistro("3012345689111111","1111111111111114", "399"); 
            System.out.println(msg);
        } catch (NumberFormatException e) {
            System.out.println("Verifique que el dato saldo contenga datos validos");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }



    }
}
