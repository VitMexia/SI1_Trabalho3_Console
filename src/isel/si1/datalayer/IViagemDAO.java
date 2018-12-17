package isel.si1.datalayer;

import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.Viagem;

import java.util.Date;
import java.util.List;

public interface IViagemDAO {

    List<Viagem> GetViagens(String email, Date dataIncial, Date dataFinal) throws DatabaseException;
    int CreateTrip(int id_Passe, Date dataIncial, int id_Bicicleta, int id_Estacao_inicial);
    int EndTrip(Date dataFinal, int id_Estacao_Final, int avaliação, String mensagem);
}
