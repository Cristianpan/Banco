package utils;

import java.io.File;

public class Ereaser {

    public static void deleteFile (String pathName) {
        File archivo = new File(pathName); 
        if (archivo.isDirectory()){
            for (File archivos : archivo.listFiles()) {
                archivos.delete(); 
            }
        }
        archivo.delete(); 
    } 
    
}
