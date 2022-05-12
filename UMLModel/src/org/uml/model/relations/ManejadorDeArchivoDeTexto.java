
package org.uml.model.relations;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ManejadorDeArchivoDeTexto 
{
    private static BufferedWriter bufferDeSalida;
        
    public static void crearArchivo(String nomArch) throws IOException {
       bufferDeSalida = new BufferedWriter(new FileWriter(new File(nomArch), true));
    }
    /**
     * Obtiene la siguiente línea de un archivo de texto. 
     *
     * @return Cadena en formato String. Si no hay más lineas o falla, devuelve una cadena vacía.
     */
   
    /**
     * Escribe una línea en un archivo de texto, en el renglón siguiente tras la última línea del archivo. 
     *
     * @param s Una cadena en formato String.
     */
    public static void escribirLinea (String s) throws IOException {
        bufferDeSalida.write(s + "\n");
        bufferDeSalida.flush();
    }
}
