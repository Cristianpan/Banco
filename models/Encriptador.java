package models;

import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SealedObject;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encriptador {

    public SealedObject encrypt(Serializable objeto, String algoritmo, String clave)
            throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, IllegalBlockSizeException, IOException {
        SealedObject sealedObject;

        SecretKey secretKey = obtenerSecretKey(clave, algoritmo);
        Cipher cipher = Cipher.getInstance(algoritmo);
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        sealedObject = new SealedObject(objeto, cipher);

        return sealedObject;
    }

    public <E> E decrypt(SealedObject objeto, String clave, Class<E> claseObjeto) throws NoSuchAlgorithmException, InvalidKeyException, ClassNotFoundException, IOException {
        E obj = null; 
        SecretKey secretKey = obtenerSecretKey(clave, objeto.getAlgorithm()); 
        obj = (E) objeto.getObject(secretKey); 
        return obj;         
    }

    private SecretKey obtenerSecretKey(String clave, String algoritmo)
            throws NoSuchAlgorithmException, UnsupportedEncodingException {
        byte[] claveEncriptacion = clave.getBytes("UTF-8");

        MessageDigest sha = MessageDigest.getInstance("SHA-1");

        claveEncriptacion = sha.digest(claveEncriptacion);
        claveEncriptacion = Arrays.copyOf(claveEncriptacion, 16);

        SecretKeySpec secretKey = new SecretKeySpec(claveEncriptacion, algoritmo);

        return secretKey;
    }
}
