package isel.si1.datalayer;

import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.Viagem;

import java.util.Date;
import java.util.List;

public interface IViagemDAO {

    List<Viagem> getViagens(String email, Date dataIncial, Date dataFinal) throws DatabaseException;
    int createTrip(int id_Passe, int id_Bicicleta, int id_Estacao_inicial) throws DatabaseException;
    int endTrip(Date dataFinal, int id_Estacao_Final, int avaliação, String mensagem) throws DatabaseException;


}
