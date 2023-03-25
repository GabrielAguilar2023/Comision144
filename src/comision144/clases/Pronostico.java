package comision144.clases;

import comision144.enumeracion.ResultadoEnum;

public class Pronostico {
    public Partido partidoSobreElQueSeApuesta;
    public Equipo equipoSobreElQueSeApuesta;
    public ResultadoEnum resultado;

    // |    Equipo1    |     Gana1     |    Empata     |     Gana2     |    Equipo2    |
    // | prediccion[0] | prediccion[1] | prediccion[2] | prediccion[3] | prediccion[4] |

    public Pronostico (String [] prediccion,Partido partidoExistenteEnArchivoResultados) {
        this.partidoSobreElQueSeApuesta = partidoExistenteEnArchivoResultados;
        this.equipoSobreElQueSeApuesta = new Equipo("");

        if (prediccion[1].equals("X")) {
            this.equipoSobreElQueSeApuesta.nombre = prediccion[0];
            this.resultado = ResultadoEnum.GANADOR;
        }else{
            if (prediccion[2].equals("X")){
                this.equipoSobreElQueSeApuesta.nombre = prediccion[0];
                this.resultado= ResultadoEnum.EMPATE;
            }else{
                if(prediccion[3].equals("X")){
                    this.equipoSobreElQueSeApuesta.nombre =prediccion[4];
                    this.resultado=ResultadoEnum.GANADOR;
                }
            }
        }
    }


    public int puntos (){

            if ((this.partidoSobreElQueSeApuesta.golesEquipo1) > (this.partidoSobreElQueSeApuesta.golesEquipo2)) {
                if ((this.partidoSobreElQueSeApuesta.equipo1.equals(this.equipoSobreElQueSeApuesta.nombre)) && (this.resultado.equals(ResultadoEnum.GANADOR))) {
                    return 1;
                }
            } else {
                if ((this.partidoSobreElQueSeApuesta.golesEquipo1) < (this.partidoSobreElQueSeApuesta.golesEquipo2)) {
                    if ((this.partidoSobreElQueSeApuesta.equipo2.nombre.equals(this.equipoSobreElQueSeApuesta.nombre)) && (this.resultado.equals(ResultadoEnum.GANADOR))) {
                        return 1;
                    }
                } else {
                    if (this.resultado.equals(ResultadoEnum.EMPATE)) {
                        return 1;
                    }
                }
            }

        return 0;
    }
}

