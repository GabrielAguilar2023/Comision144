package comision144.clases;
import comision144.enumeracion.ResultadoEnum;

public class Partido {
    public Equipo equipo1;     // Declaracion del Objeto
    public Equipo equipo2;     // Declaracion del Objeto
    public int golesEquipo1;
    public int golesEquipo2;
    public String idPartido;

//*******CONSTRUCTOR********
    public Partido(String [] datosEntrada) {
        this.equipo1 = new Equipo(datosEntrada[0]);   // Creacion del objeto
        this.equipo2 = new Equipo(datosEntrada[3]);   // Creacion del Objeto
        this.golesEquipo1 = Integer.parseInt(datosEntrada[1]);// pasa de texto a numero
        this.golesEquipo2 = Integer.parseInt(datosEntrada[2]);// pasa de texto a numero
        this.idPartido = datosEntrada[4];
    }

    public ResultadoEnum resultado (Equipo equipo) {
        return ResultadoEnum.GANADOR ;
        //todo
    }
}