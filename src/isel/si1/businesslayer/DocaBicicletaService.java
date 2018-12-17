package isel.si1.businesslayer;

import isel.si1.datalayer.DocaBicicletaDAO;
import isel.si1.datalayer.IDocaBicicletaDAO;
import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.DocaBicicleta;

import java.util.List;

public class DocaBicicletaService implements IDocaBicicletaService {


    IDocaBicicletaDAO docaBicicletaDAO;


    public IDocaBicicletaDAO getDocaBicicletaDAO() {
        return docaBicicletaDAO;
    }

    public void setDocaBicicletaDAO(IDocaBicicletaDAO docaBicicletaDAO) {
        this.docaBicicletaDAO = docaBicicletaDAO;
    }

    public DocaBicicletaService() {

        docaBicicletaDAO = new DocaBicicletaDAO();
    }

    @Override
    public List<DocaBicicleta> GetDocaBicicleta(int id_Estacao) throws ServiceException {
        try {
            return docaBicicletaDAO.GetBicicletsDoca(id_Estacao);
        }
        catch (DatabaseException exception) {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }
}
