package daos;

import java.io.File;
import java.util.ArrayList;
import errors.NotFoundAccountOfClientException;
import errors.NotFoundClientException;
import models.Cuenta;
import utils.Connection;

public class DaoCuentas {

    private final String path = "./data/cuentas";
    private ArrayList<String> cuentas;

    public DaoCuentas() {
        File[] carpetas = new File(path).listFiles();
        cuentas = new ArrayList<>();

        for (File string : carpetas) {
            for (String file : string.list()) {
                cuentas.add(file);
            }
        }
    }

    public ArrayList<Cuenta> obtenerCuentasPorCliente(String idCliente) throws NotFoundClientException {
        ArrayList<Cuenta> cuentasCliente = new ArrayList<>();
        String pathCliente = path + "/" + idCliente;

        String[] cuentas = new File(pathCliente).list();

        for (String cuenta : cuentas) {
            cuentasCliente.add(Connection.readObject(pathCliente + "/" + cuenta, Cuenta.class));
        } 

        if (cuentasCliente.isEmpty()) {
            throw new NotFoundClientException(idCliente); 
        }

        return cuentasCliente;
    }

    public Cuenta obtenerCuenta(String idCliente, String numeroDeCuenta) throws NotFoundAccountOfClientException {
        String pathCuentaCliente = path + "/" + idCliente + "/" + numeroDeCuenta + ".dat";

        File archivoCuenta = new File(pathCuentaCliente);

        if (archivoCuenta.exists()) {
            return Connection.readObject(pathCuentaCliente, Cuenta.class);
        } else {
            throw new NotFoundAccountOfClientException(idCliente, numeroDeCuenta); 
        }

    }

    public void agregarCuentaCliente(String idCliente, Cuenta cuentaCliente) {

        File directorio = new File(path + "/" + idCliente);
        String pathArchivo = path + "/" + idCliente + "/" + cuentaCliente.getNumeroDeCuenta() + ".dat";

        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                Connection.writeObject(pathArchivo, cuentaCliente);
            }
        } else {
            Connection.writeObject(pathArchivo, cuentaCliente);
        }
    }

    public void actualizarCuentaCliente(String idCliente, Cuenta cuentaCliente) throws NotFoundAccountOfClientException {
        String pathCuentaCliente = path + "/" + idCliente + "/" + cuentaCliente.getNumeroDeCuenta() + ".dat";

        File archivoCuenta = new File(pathCuentaCliente);

        if (archivoCuenta.exists()) {
            Connection.writeObject(pathCuentaCliente, cuentaCliente);
        } else {
            throw new NotFoundAccountOfClientException(idCliente, cuentaCliente.getNumeroDeCuenta()); 
        }
    }

    public int eliminarCuentaCLiente(String idCliente, String numeroDeCuenta) {
        String pathCuentaCliente = path + "/" + idCliente + "/" + numeroDeCuenta + ".dat";
        Connection.deleteFile(pathCuentaCliente);
         return new File(path + "/" + idCliente).list().length; 
    }

    public void eliminarCuentasCliente(String idCliente) {
        Connection.deleteFile(path + "/" + idCliente);
    }

    public boolean existeCuenta(String cuenta) {
        return cuentas.contains(cuenta + ".dat");
    }

}
