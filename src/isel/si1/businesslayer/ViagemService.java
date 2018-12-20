package isel.si1.businesslayer;

import isel.si1.datalayer.IViagemDAO;
import isel.si1.datalayer.ViagemDAO;
import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.Viagem;

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
    public List<Viagem> getViagens(String email, Date dataIncial, Date dataFinal) throws ServiceException {
        return null;
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
    public int endTrip(Date dataFinal, int id_Estacao_Final, int avaliação, String mensagem) throws ServiceException {
        return 0;
    }
}
