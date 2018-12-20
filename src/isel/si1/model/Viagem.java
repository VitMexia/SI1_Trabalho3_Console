package isel.si1.model;

import java.sql.Timestamp;

public class Viagem {
    public int id_Passe;
    public Timestamp data_Inicial;
    public Timestamp data_Final;
    public int id_Bicileta;
    public int id_Estacao_Inicial;
    public int id_Estacao_Final;
    public Bicicleta bicicleta;
}
