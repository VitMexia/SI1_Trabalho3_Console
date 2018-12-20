package isel.si1.businesslayer;

import isel.si1.datalayer.IViagemDAO;
import isel.si1.datalayer.ViagemDAO;
import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.Viagem;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

public class ViagemService implements IViagemService {

    IViagemDAO viagemDAO;

    public IViagemDAO getViagemDAO() {
        return viagemDAO;
    }

    public void setViagemDAO(IViagemDAO viagemDAO) {
        this.viagemDAO = viagemDAO;
    }

    public ViagemService() {
        this.viagemDAO = new ViagemDAO();
    }

    @Override
    public List<Viagem> getViagensBicicleta(int id_Passe, Date dataIncial, Date dataFinal) throws ServiceException {
        try {
            return viagemDAO.getViagensBicicleta(id_Passe, dataIncial, dataFinal);
        }
        catch (DatabaseException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    @Override
    public int createTrip(int id_Passe,  int id_Bicicleta, int id_Estacao_inicial) throws ServiceException{
        try {
            return viagemDAO.createTrip(id_Passe, id_Bicicleta, id_Estacao_inicial);
        }
        catch (DatabaseException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    @Override
    public int endTrip(int id_Passe, Timestamp data_Inicial, int id_Estacao_Final, int avaliação, String mensagem) throws ServiceException {
        try {
            return viagemDAO.endTrip(id_Passe, data_Inicial, id_Estacao_Final, avaliação, mensagem);
        }
        catch (DatabaseException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }

    @Override
    public List<Viagem> getOpenedViagens() throws ServiceException {
        try {
            return viagemDAO.getOpenedViagens();
        }
        catch (DatabaseException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }
}
