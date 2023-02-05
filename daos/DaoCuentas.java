package daos;

import java.io.File;
import java.util.ArrayList;
import errors.NotFoundAccountOfClient;
import models.Cuenta;
import utils.Ereaser;
import utils.Serializer;

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

    public ArrayList<Cuenta> obtenerCuentasPorCliente(String idCliente) {
        ArrayList<Cuenta> cuentasCliente = new ArrayList<>();
        String pathCliente = path + "/" + idCliente;

        String[] cuentas = new File(pathCliente).list();

        for (String cuenta : cuentas) {
            cuentasCliente.add(Serializer.deserializarObjeto(pathCliente + "/" + cuenta, Cuenta.class));
        }

        return cuentasCliente;
    }

    public Cuenta obtenerCuenta(String idCliente, String numeroDeCuenta) throws NotFoundAccountOfClient {
        String pathCuentaCliente = path + "/" + idCliente + "/" + numeroDeCuenta + ".dat";

        File archivoCuenta = new File(pathCuentaCliente);

        if (archivoCuenta.exists()) {
            return Serializer.deserializarObjeto(pathCuentaCliente, Cuenta.class);
        } else {
            throw new NotFoundAccountOfClient(idCliente, numeroDeCuenta); 
        }

    }

    public void agregarCuentaCliente(String idCliente, Cuenta cuentaCliente) {

        File directorio = new File(path + "/" + idCliente);
        String pathArchivo = path + "/" + idCliente + "/" + cuentaCliente.getNumeroDeCuenta() + ".dat";

        if (!directorio.exists()) {
            if (directorio.mkdirs()) {
                Serializer.serializarObjeto(pathArchivo, cuentaCliente);
            }
        } else {
            Serializer.serializarObjeto(pathArchivo, cuentaCliente);
        }
    }

    public void actualizarCuentaCliente(String idCliente, Cuenta cuentaCliente) throws NotFoundAccountOfClient {
        String pathCuentaCliente = path + "/" + idCliente + "/" + cuentaCliente.getNumeroDeCuenta() + ".dat";

        File archivoCuenta = new File(pathCuentaCliente);

        if (archivoCuenta.exists()) {
            Serializer.serializarObjeto(pathCuentaCliente, cuentaCliente);
        } else {
            throw new NotFoundAccountOfClient(idCliente, cuentaCliente.getNumeroDeCuenta()); 
        }
    }

    public int eliminarCuentaCLiente(String idCliente, String numeroDeCuenta) {
        String pathCuentaCliente = path + "/" + idCliente + "/" + numeroDeCuenta + ".dat";
        Ereaser.DeleteFile(pathCuentaCliente);
         return new File(path + "/" + idCliente).list().length; 
    }

    public void eliminarCuentasCliente(String idCliente) {
        Ereaser.DeleteFile(path + "/" + idCliente);
    }

    public boolean existeCuenta(String cuenta) {
        return cuentas.contains(cuenta + ".dat");
    }

}
