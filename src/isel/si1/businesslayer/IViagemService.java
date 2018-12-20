package isel.si1.businesslayer;

import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.Viagem;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public interface IViagemService extends IService {

    List<Viagem> getViagensBicicleta(int id_Passe, Date dataIncial, Date dataFinal) throws ServiceException;
    List<Viagem> getOpenedViagens() throws ServiceException;
    int createTrip(int id_Passe, int id_Bicicleta, int id_Estacao_inicial) throws ServiceException;
    int endTrip(int id_Passe, Timestamp data_Inicial, int id_Estacao_Final, int avaliação, String mensagem) throws ServiceException;
}
