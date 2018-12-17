package isel.si1.businesslayer;

import isel.si1.datalayer.EstacaoDAO;
import isel.si1.datalayer.IEstacaoDAO;
import isel.si1.datalayer.IUtilizadorDAO;
import isel.si1.datalayer.UtilizadorDAO;
import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.Estacao;
import isel.si1.model.Utilizador;

import java.util.List;

public class EstacaoService implements IEstacaoService {

    IEstacaoDAO estacaoDAO;


    public IEstacaoDAO getEstacaoDAO() {
        return estacaoDAO;
    }

    public void setEstacaoDAO(IEstacaoDAO estacaoDAO) {
        this.estacaoDAO = estacaoDAO;
    }

    public EstacaoService() {

        estacaoDAO = new EstacaoDAO();
    }

    @Override
    public List<Estacao> GetStations() throws ServiceException {

        try {
            return estacaoDAO.GetStations();
        }
        catch (DatabaseException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }
}

