package isel.si1.businesslayer;

import isel.si1.model.Referencia;


import java.util.List;

public interface IReferenciaService extends IService {
    List<Referencia> GetReferencias() throws ServiceException;
}
