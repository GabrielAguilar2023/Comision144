package comision144.clases;

import comision144.enumeracion.ResultadoEnum;

public class Pronostico {
    public Partido partido;
    public Equipo equipo;
    public ResultadoEnum resultado;

    // |    Equipo1    |     Gana1     |    Empata     |     Gana2     |    Equipo2    |
    // | prediccion[0] | prediccion[1] | prediccion[2] | prediccion[3] | prediccion[4] |

    public Pronostico (String [] prediccion) {
        String vectorParaPartido[]={prediccion[0],"0","0",prediccion[4],"0"};
        this.partido = new Partido(vectorParaPartido);
        this.equipo = new Equipo("");

        if (prediccion[1].equals("X")) {
            this.equipo.nombre = prediccion[0];
            this.resultado = ResultadoEnum.GANADOR;
        }else{
            if (prediccion[2].equals("X")){
                this.equipo.nombre = prediccion[0];
                this.resultado= ResultadoEnum.EMPATE;
            }else{
                if(prediccion[3].equals("X")){
                    this.equipo.nombre =prediccion[4];
                    this.resultado=ResultadoEnum.GANADOR;
                }
            }
        }
    }
    //Coonstructor para vincular con los objetos del archivo resultados
    public Pronostico(Partido partido, String [] prediccion) {
        this.partido = partido;
        this.equipo = new Equipo("");

        if (prediccion[1].equals("X")) {
            this.equipo.nombre = prediccion[0];
            this.resultado= ResultadoEnum.GANADOR;
        }else{
            if (prediccion[2].equals("X")){
                this.equipo.nombre = prediccion[0];
                this.resultado= ResultadoEnum.EMPATE;
            }else{
                if(prediccion[3].equals("X")){
                    this.equipo.nombre =prediccion[4];
                    this.resultado=ResultadoEnum.GANADOR;
                }
            }
        }
    }

    public int puntos (){
        return 0;
        //ToDo
    }
}
