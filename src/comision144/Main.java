package comision144;

import comision144.clases.Partido;
import comision144.clases.Pronostico;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
        int [] dimensionResultado;
        int [] dimensionPronosticos;

        String archivoResultados;
        String archivoPronosticos;
        Scanner CapturaArchivo = new Scanner(System.in);


        // Carga de Archivos por argumento
        if (args.length==2) {
            archivoResultados = args[0];
            archivoPronosticos = args[1];
        }
        else{
            // Entrada de archivos por consola
            System.out.println("Directorio donde se encuentra el archivo resultados.csv: ");
            archivoResultados = CapturaArchivo.nextLine()+"\\resultados.csv";
            System.out.println("Directorio donde se encuentra el archivo pronosticos.csv: ");
            archivoPronosticos = CapturaArchivo.nextLine()+"\\pronosticos.csv";
/*          // Entrada por ventana grafica
            archivoResultados = JOptionPane.showInputDialog("Carpeta del archivo resultados.csv") + "\\resultados.csv";
            archivoPronosticos= JOptionPane.showInputDialog("Path a la carpeta partidos.csv") + "\\pronosticos.csv";
*/
        }

        dimensionResultado = analizarArchivos(archivoResultados); //Determina las caracteristicas de los archivo y
        dimensionPronosticos = analizarArchivos(archivoPronosticos); // determina la validez de los archivos de entrada.

        if ((dimensionResultado[0]>1) && dimensionPronosticos[0]>1){ // Si los archivos son validos continuar
            var resultados  = cargarArchivoDeResultados(archivoResultados,dimensionResultado);
            var pronosticos = cargarArchivoDePronosticos(archivoPronosticos,dimensionPronosticos);



            System.out.println("Fin");

        }
    }

    public static int[] analizarArchivos (String archivo) throws IOException {
        int numeroDeFilas = 0;
        int numeroDeColumnas=0;
        if (Files.exists(Paths.get(archivo))) {
            for (String ignored : Files.readAllLines(Paths.get(archivo))) numeroDeFilas++;
        }
        if (numeroDeFilas>1){
            for (String texto : Files.readAllLines(Paths.get(archivo))) {
                String[] vectorAux = texto.split(";");  //Separa las columnas de cada fila
                numeroDeColumnas=vectorAux.length;
            }
        }
        return new int[]{numeroDeFilas,numeroDeColumnas};


    }

    public static String[][] pasarArchivoAMatriz(String archivo,int columnas) throws IOException {
        // Determinar numero de filas del archivo
        int numeroDeFilas = 0;
        for (String ignored : Files.readAllLines(Paths.get(archivo))) numeroDeFilas++;

        var matriz= new String[numeroDeFilas][columnas];

        // Convertir vector de texto separado por ";" en matriz de 2x2
        int contadorFila = 0;
        var vectorAux = new String[columnas];  //Vector auxiliar para cargar las columnas
        for (String texto : Files.readAllLines(Paths.get(archivo))) {
            vectorAux = texto.split(";");  //Separa las columnas de cada fila
            // Convierte vector de elementos de la fila a columnas de la matriz
            if (columnas >= 0) System.arraycopy(vectorAux, 0, matriz[contadorFila], 0, columnas);
            contadorFila++;
        }
        return matriz;
    }

    public static Pronostico[] cargarArchivoDePronosticos(String archivo,int []dimension) throws IOException {

        Pronostico[] informacionArchivo = new Pronostico[dimension[0]-1];
        int contadorFila = 0;
        //String vectorAux[];  //Vector auxiliar para cargar las columnas

        for (String texto : Files.readAllLines(Paths.get(archivo))) { // Extrae filas del archivo
            String vectorAux[] = texto.split(";");  //Separa las columnas de cada fila
            if(contadorFila>0) {    // Para evitar cargar en un objeto el encabezado de la tabla
                informacionArchivo[contadorFila-1]= new Pronostico(vectorAux);
                System.out.println("borrar esta linea");
            }
            contadorFila++;
        }
        return informacionArchivo;
    }

    public static Partido[] cargarArchivoDeResultados (String archivo,int []dimension)throws IOException{
        Partido[] informacionArchivo = new Partido[dimension[0]-1];
        int contadorFila = 0;
        //String vectorAux[];  //Vector auxiliar para cargar las columnas

        for (String texto : Files.readAllLines(Paths.get(archivo))) { // Extrae filas del archivo
            String vectorAux[] = texto.split(";");  //Separa las columnas de cada fila
            if(contadorFila>0) {    // Para evitar cargar en un objeto el encabezado de la tabla
                informacionArchivo[contadorFila-1]= new Partido(vectorAux);
            }
            contadorFila++;
        }
        return informacionArchivo;

    }
}


