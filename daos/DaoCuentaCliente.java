package daos;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

import errors.ExistAccountException;
import errors.NumberClientException;
import models.Cliente;
import models.Cuenta;

public class DaoCuentaCliente {

    private ArrayList<Cliente> clientes;
    private ArrayList<String> numeroDeCuentas;

    public DaoCuentaCliente () {
        this.clientes = new ArrayList<>();
        this.numeroDeCuentas = new ArrayList<>();

        String numCliente; 
        String nombreCliente; 
        String numeroDeCuenta; 
        float saldo; 
        int indexNumCliente; // Almacena el valor de la posición del cliente en el ArrayList en caso de existir

        try {
            Scanner input = new Scanner(new FileReader("./db/cuentas.txt")); 

            while (input.hasNextLine()){
                StringTokenizer datosCliente = new StringTokenizer(input.nextLine(), ","); 

                numCliente = datosCliente.nextToken(); 
                nombreCliente = datosCliente.nextToken(); 
                numeroDeCuenta = datosCliente.nextToken(); 
                saldo = Float.parseFloat(datosCliente.nextToken()); 
                
                indexNumCliente = existeNumeroDeCliente(numCliente); 
                this.numeroDeCuentas.add(numeroDeCuenta); //registra la existencia de una cuenta

                if (indexNumCliente < 0) {
                    this.clientes.add(new Cliente (numCliente, nombreCliente, new Cuenta(numeroDeCuenta, saldo))); 
                } else {
                    this.clientes.get(indexNumCliente).getCuentasCliente().add(new Cuenta(numeroDeCuenta, saldo)); 
                }

            }

            input.close();

        } catch (FileNotFoundException e) {
            System.out.println("No se ha podido abrir el archivo.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public ArrayList<Cliente> obtenerRegistros (){
        return this.clientes; 
    }

    
    /*
     * Recibe el id del cliente y la nueva cuenta que será agregada
     * Lanza una exepción si el numero de cliente no existe o si la cuenta es existente
     */
    public String agregarRegistro (String numCliente, Cuenta cuenta) throws NumberClientException, ExistAccountException{
        int indexNumCliente = existeNumeroDeCliente(numCliente); 

        if (indexNumCliente >= 0) {
            if (!existeNumeroDeCuenta(cuenta.getNumeroDeCuenta())){
                this.numeroDeCuentas.add(cuenta.getNumeroDeCuenta()); 
                this.clientes.get(indexNumCliente).setCuentaCliente(cuenta);
                actualizarArchivo();
            } else {
                throw new ExistAccountException ("Error al hacer el registro. El numero de cuenta " + cuenta.getNumeroDeCuenta() + " ya ha sido asignado a un cliente"); 
            }
        } else {
            throw new NumberClientException("Error al hacer el registro. No existe el cliente con el numero:" + numCliente); 
        }

        return "Registro de nueva cuenta exitoso a: " + this.clientes.get(indexNumCliente).getNombreCliente() + " con no. de cuenta: " + cuenta.getNumeroDeCuenta(); 
    }

    /*
     * Recibe el un objeto Cliente
     * Lanza una excepcion si el numero de cliente ya es existente o si la cuenta ya esta registrada
     */
    public String agregarRegistro (Cliente cliente) throws ExistAccountException, NumberClientException {
        String nuevoNumeroDeCliente = cliente.getNumeroDeCliente(); 
        String nuevoNumeroDeCuenta = cliente.getCuentasCliente().get(0).getNumeroDeCuenta(); 
        int indexNumCliente = existeNumeroDeCliente(nuevoNumeroDeCliente); 

        if (indexNumCliente < 0){
            if (!existeNumeroDeCuenta(nuevoNumeroDeCuenta)){
                this.numeroDeCuentas.add(nuevoNumeroDeCuenta); 
                this.clientes.add(cliente); 
                actualizarArchivo();
            } else {
                throw new ExistAccountException ("Error al hacer el registro. El numero de cuenta " + cliente.getCuentasCliente().get(0).getNumeroDeCuenta() + " ya ha sido asignado a un cliente"); 
            }
        } else {
            throw new NumberClientException("Error al hacer el registro. El numero de cliente ya ha sido registrado en el sistema"); 
        }

        return "Nuevo cliente registrado.";
    }

    public void actualizarArchivo (){
        try {
            PrintWriter printer = new PrintWriter(new FileWriter("./db/cuentas.txt", false));

            for (Cliente cliente : clientes) {
                for (Cuenta cuentaCliente : cliente.getCuentasCliente()) {
                    printer.format("%s,%s,%s,%f\n", cliente.getNumeroDeCliente(), cliente.getNombreCliente(), cuentaCliente.getNumeroDeCuenta(), cuentaCliente.getSaldo()); 
                }
            }
            printer.close();
        } catch (IOException e) {
            System.out.println("Error al actualizar el archivo");
        }
    }

    //Retorna true si el numero de cuenta y existe en el registro, si no, retorna falso 
    private boolean existeNumeroDeCuenta(String numeroDeCuenta) {
        return numeroDeCuentas.contains(numeroDeCuenta);
    }


    /*
     * Retorna el valor de la posicion en el arreglo en caso de existir el numero de cliente
     * Retorna un -1 si el numero de cliente no se encuentra registrado
     */
    private int existeNumeroDeCliente (String numCliente){
        int indexNumCliente = 0; 

        for (Cliente cliente : this.clientes) {
            if (cliente.getNumeroDeCliente().equals(numCliente)){
                return indexNumCliente; 
            }
            indexNumCliente++;  
            
        }

        return -1;  
    }

}
