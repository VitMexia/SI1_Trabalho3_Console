package isel.si1.businesslayer;

import isel.si1.model.DocaBicicleta;

import java.util.List;

public interface IDocaBicicletaService extends IService {

        List<DocaBicicleta> GetDocaBicicleta(int id_Estacao) throws ServiceException;


}
