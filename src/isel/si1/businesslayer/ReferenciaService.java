package isel.si1.businesslayer;


import isel.si1.datalayer.IReferenciaDAO;
import isel.si1.datalayer.ReferenciaDAO;
import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.Referencia;

import java.util.List;

public class ReferenciaService implements IReferenciaService {

    IReferenciaDAO referenciaDAO;


    public IReferenciaDAO getUtilizadorDAO() {
        return referenciaDAO;
    }

    public void setUtilizadorDAO(IReferenciaDAO referenciaDAO) {
        this.referenciaDAO = referenciaDAO;
    }

    public ReferenciaService()
    {
        /* Creates a new DAO but allows for future refactoring to support Dependency Injection */
        referenciaDAO = new ReferenciaDAO();
    }

    @Override
    public List<Referencia> GetReferencias() throws ServiceException {
        try
        {
            return referenciaDAO.GetReferencias();
        }
        catch (DatabaseException exception)
        {
            throw new ServiceException(exception.getMessage(), exception);
        }
    }
}
