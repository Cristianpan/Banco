package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.crypto.SealedObject;

public class Connection {
    public static boolean writeObject(String direccionArchivo, Serializable objeto) {
        boolean sw = false;
        try {
            FileOutputStream fos = new FileOutputStream(direccionArchivo);
            ObjectOutputStream salida = new ObjectOutputStream(fos);
            salida.writeObject(Encrypter.encrypt(objeto, "AES", direccionArchivo));
            sw = true;
            salida.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sw;
    }

    public static <E> E readObject(String direccionArchivo, Class<E> claseObjetivo) {
        E objeto = null;
        try {
            FileInputStream fis = new FileInputStream(direccionArchivo);
            ObjectInputStream entrada = new ObjectInputStream(fis);
            SealedObject auxObjeto = (SealedObject) entrada.readObject();
            objeto = Encrypter.decrypt(auxObjeto, direccionArchivo, claseObjetivo);
            entrada.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return objeto;
    }

}
