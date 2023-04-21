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

// Entrada de archivos por consola
            System.out.println("Directorio donde se encuentra el archivo resultados.csv: ");
// El objeto Scanner CapturaArchivo agarra el texto ingresado por teclado de la ruta del directorio y le suma el nombre del archivo
            archivoResultados = CapturaArchivo.nextLine()+"/resultados.csv";
            System.out.println("Directorio donde se encuentra el archivo pronosticos.csv: ");
            archivoPronosticos = CapturaArchivo.nextLine()+"/pronosticos.csv";

// Determina la cantidad de filas y columnas de los archivos .csv dimension...[0] filas; dimension...[1] columnas
        dimensionResultado = analizarArchivos(archivoResultados);
        dimensionPronosticos = analizarArchivos(archivoPronosticos);

// Si los archivos existen y tienen mas de una fila de datos continuar
        if ((dimensionResultado[0]>1) && dimensionPronosticos[0]>1){
            System.out.println();
            System.out.println("----------- Archivo de Resultados ---------------------");
            var vectorDeObjetosdelArchivoresultados  = cargarArchivoDeResultados(archivoResultados,dimensionResultado);
            System.out.println("------------------ Pronosticos ------------------------");
            var vectorDeObjetosdelArchivoPronosticos = cargarArchivoDePronosticos(vectorDeObjetosdelArchivoresultados,archivoPronosticos,dimensionPronosticos);
            System.out.println();

            mostrarPuntajeObtenido(vectorDeObjetosdelArchivoPronosticos);
        }
    }


//********METODO**********
    public static int[] analizarArchivos (String archivo) throws IOException {
        int numeroDeFilas = 0;
        int numeroDeColumnas=0;
// Si el archivo existe entonces leerlo y obtener el numero de filas
        if (Files.exists(Paths.get(archivo))) {
            for (String ignored : Files.readAllLines(Paths.get(archivo))) numeroDeFilas++;
        }
        if (numeroDeFilas>1){
// Determina el numero de columnas de cada fila
            for (String texto : Files.readAllLines(Paths.get(archivo))) {
                String[] vectorAux = texto.split(";");  //Separa las columnas de cada fila
                numeroDeColumnas=vectorAux.length;
            }
        }
        return new int[]{numeroDeFilas,numeroDeColumnas};
    }


//********METODO**********
    public static Pronostico[] cargarArchivoDePronosticos(Partido[] vectorDeObjetosPartido,String archivo,int []dimension) throws IOException {
    Pronostico[] vectorDeObjetosPronostico = new Pronostico[dimension[0] - 1];
    int contadorFila = 0;
// Extrae filas y crea los objetos Pronostico relacionados con los objetos partido ya creados en el metodo cargarArchivoDeResultados
        for (String texto : Files.readAllLines(Paths.get(archivo))) { // Extrae filas del archivo
            String[] vectorAux = texto.split(";");  //Separa las columnas de cada fila
            System.out.println(texto);
            if (contadorFila > 0) {    // Para evitar cargar en un objeto el encabezado de la tabla
                for (int i = 0; i < vectorDeObjetosPartido.length; i++) {
                    if (vectorAux[5].equals(vectorDeObjetosPartido[i].idPartido)) {
// Aca es donde se pasa el objeto Partido al interior del objeto Pronostico
                        vectorDeObjetosPronostico[contadorFila - 1] = new Pronostico(vectorAux, vectorDeObjetosPartido[i]);
                    }
                }
            }
            contadorFila++;
        }
        return vectorDeObjetosPronostico;
    }


//********METODO**********
    public static Partido[] cargarArchivoDeResultados (String archivo, int []dimension)throws IOException{
        Partido[] vectorDeObjetosPartidoDelArchivoResultados = new Partido[dimension[0]-1];
        int contadorFila = 0;
// Lee el archivo resultados y separa los datos de cada linea
        for (String texto : Files.readAllLines(Paths.get(archivo))) { // Extrae filas del archivo
            System.out.println(texto);
// Separa los datos de cada linea
            String[] vectorAux = texto.split(";");  //Separa las columnas de cada fila
// Crea los objetos Partido a partir de los datos del Archivo resultados.csv
            if(contadorFila>0) {    // Para evitar cargar en un objeto el encabezado de la tabla
                vectorDeObjetosPartidoDelArchivoResultados[contadorFila-1]= new Partido(vectorAux);
            }
            contadorFila++;
        }
// Devuelve los objetos Partido en un vector donde cada elemento del vector es un partido jugado
        return vectorDeObjetosPartidoDelArchivoResultados;
    }


//********METODO**********
    public static void mostrarPuntajeObtenido(Pronostico[] vectorDePronosticoAMostrar){
        int puntaje=0;
// Recorre los objetos Pronostico y les va ejecutando el metodo puntos() a cada objeto y suma esos valores devueltos
        for (int i=0;i<vectorDePronosticoAMostrar.length;i++){
            puntaje += vectorDePronosticoAMostrar[i].puntos();
        }
        System.out.println( "El puntaje es "+ puntaje);
    }
}



