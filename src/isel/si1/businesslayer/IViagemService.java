package isel.si1.businesslayer;

import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.Viagem;

import java.util.Date;
import java.util.List;

public interface IViagemService extends IService {

    List<Viagem> getViagens(String email, Date dataIncial, Date dataFinal) throws ServiceException;
    int createTrip(int id_Passe, int id_Bicicleta, int id_Estacao_inicial) throws ServiceException;
    int endTrip(Date dataFinal, int id_Estacao_Final, int avaliação, String mensagem) throws ServiceException;
}
