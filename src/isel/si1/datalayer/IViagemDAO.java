package isel.si1.datalayer;

import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.Viagem;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface IViagemDAO {

    List<Viagem> getViagensBicicleta(int id_Passe, Date dataIncial, Date dataFinal) throws DatabaseException;
    List<Viagem> getOpenedViagens() throws DatabaseException;
    int createTrip(int id_Passe, int id_Bicicleta, int id_Estacao_inicial) throws DatabaseException;
    int endTrip(int id_Passe, Timestamp data_Inicial, int id_Estacao_Final, int avaliação, String mensagem) throws DatabaseException;


}
