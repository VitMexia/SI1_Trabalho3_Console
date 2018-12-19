package isel.si1.businesslayer;

import isel.si1.model.Bicicleta;
import isel.si1.model.DocaBicicleta;

import java.util.List;

public interface IBicicletaService extends IService {

        List<Bicicleta> getAvailableBicicletas(int id_Estacao) throws ServiceException;

}
