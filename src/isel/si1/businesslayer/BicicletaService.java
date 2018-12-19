package isel.si1.businesslayer;

import isel.si1.datalayer.BicicletaDAO;
import isel.si1.datalayer.IBicicletaDAO;
import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.Bicicleta;
import isel.si1.model.DocaBicicleta;

import java.util.List;

public class BicicletaService implements IBicicletaService {


    IBicicletaDAO bicicletaDAO;

    public IBicicletaDAO getBicicletaDAO(){return bicicletaDAO;}

    public void setBicicletaDAO(IBicicletaDAO bicicletaDAO)
    {this.bicicletaDAO = bicicletaDAO;}

    public BicicletaService(){
        bicicletaDAO = new BicicletaDAO();
    }

    @Override
    public List<Bicicleta> getAvailableBicicletas(int id_Estacao) throws ServiceException {
        try {
            return bicicletaDAO.getAvailableBicicletas(id_Estacao);
        }
        catch (DatabaseException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }
}
