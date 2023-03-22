package comsion144.clases;

import comsion144.enumeracion.ResultadoEnum;

public class Partido {
    Equipo equipo1;
    Equipo equipo2;
    int golesEquipo1;
    int golesEquipo2;
    public ResultadoEnum resultado(Equipo equipo) {
        return ResultadoEnum.EMPATE;
                // Completar...
    }
}
