package daos;

import java.io.File;
import java.util.ArrayList;
import errors.NotFoundClientException;
import models.Cliente;
import utils.Ereaser;
import utils.Connection;

/**
 * DaoClientes
 */
public class DaoClientes {
    private final String path = "./data/clientes";

    public ArrayList<Cliente> obtenerClientes() throws NotFoundClientException {
        ArrayList<Cliente> clientes = new ArrayList<>();
        String[] archivosClientes = new File(path).list();
        DaoCuentas daoCuentas = new DaoCuentas();

        for (String archivoCliente : archivosClientes) {
            clientes.add(new Cliente(Connection.readObject(path + "/" + archivoCliente, String[].class),
                    daoCuentas.obtenerCuentasPorCliente(archivoCliente.replaceAll(".dat", ""))));
        }

        return clientes;
    }

    public Cliente obtenerCliente(String idCliente) throws NotFoundClientException {
        DaoCuentas daoCuentas = new DaoCuentas();

        if (existeIdCliente(idCliente)) {
            return new Cliente(Connection.readObject(path + "/" + idCliente + ".dat", String[].class),
                    daoCuentas.obtenerCuentasPorCliente(idCliente));
        } else {
            throw new NotFoundClientException(idCliente);
        }
    }

    public void actualizarCliente(String idCliente, String nombreCliente) throws NotFoundClientException {
        if (existeIdCliente(idCliente)) {
            Connection.writeObject(path + "/" + idCliente + ".dat", new String[] { idCliente, nombreCliente });
        } else {
            throw new NotFoundClientException(idCliente);
        }
    }

    public void eliminarCliente(String idCliente) throws NotFoundClientException {
        if (existeIdCliente(idCliente)) {
            new DaoCuentas().eliminarCuentasCliente(idCliente);
            Ereaser.deleteFile(path + "/" + idCliente + ".dat");
        } else {
            throw new NotFoundClientException(idCliente);
        }
    }

    public void agregarCliente(String idCliente, String nombreCliente) {
        Connection.writeObject(path + "/" + idCliente + ".dat", new String[] { idCliente, nombreCliente });
    }

    public boolean existeIdCliente(String idCliente) {
        File archivoCliente = new File(path + "/" + idCliente + ".dat");

        return archivoCliente.exists();
    }
}