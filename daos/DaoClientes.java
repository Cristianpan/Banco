package daos;

import java.io.File;
import java.util.ArrayList;
import errors.NotFoundClientException;
import errors.NumberClientException;
import models.Cliente;
import utils.Ereaser;
import utils.Serializer;

/**
 * DaoClientes
 */
public class DaoClientes {
    private final String path = "./data/clientes";
    private ArrayList<String[]> datosClientes;

    public DaoClientes() {
        datosClientes = new ArrayList<>();
        String[] archivosClientes = new File(path).list();

        for (String archivoCliente : archivosClientes) {
            datosClientes.add(Serializer.deserializarObjeto(path + "/" + archivoCliente, String[].class));
        }
    }

    public ArrayList<Cliente> obtenerClientes() {
        ArrayList<Cliente> clientes = new ArrayList<>();
        DaoCuentas daoCuentas = new DaoCuentas();

        for (String[] datoCliente : datosClientes) {
            clientes.add(new Cliente(datoCliente, daoCuentas.obtenerCuentasPorCliente(datoCliente[0])));
        }

        return clientes;
    }

    public Cliente obtenerCliente(String idCliente) throws NotFoundClientException {
        DaoCuentas daoCuentas = new DaoCuentas();
        int indexCliente = existeIdCliente(idCliente);

        if (indexCliente >= 0) {
            return new Cliente(datosClientes.get(indexCliente), daoCuentas.obtenerCuentasPorCliente(idCliente));
        } else {
            throw new NotFoundClientException("No se ha encontrado al cliente con el id:" + idCliente);
        }
    }

    public void actualizarCliente(String idCliente, String nombreCliente) throws NotFoundClientException {
        if (existeIdCliente(idCliente) >= 0) {
            Serializer.serializarObjeto(path + "/" + idCliente + ".dat", new String[] { idCliente, nombreCliente });
        } else {
            throw new NotFoundClientException("No se ha encontrado al cliente con el id:" + idCliente);
        }
    }

    public void eliminarCliente(String idCliente) {
        Ereaser.DeleteFile(path + "/" + idCliente + ".dat");
    }

    public void agregarCliente(String idCliente, String nombreCliente) {
            Serializer.serializarObjeto(path + "/" + idCliente + ".dat", new String[] { idCliente, nombreCliente });
    }

    public int existeIdCliente(String idCliente) {
        int index = 0;
        for (String[] datoCliente : datosClientes) {
            if (datoCliente[0].equals(idCliente)) {
                return index;
            }

            index++;
        }

        return -1;
    }
}