package isel.si1.businesslayer;


import isel.si1.model.Estacao;

import java.util.List;

public interface IEstacaoService extends IService {

    List<Estacao> GetStations() throws ServiceException;
}
