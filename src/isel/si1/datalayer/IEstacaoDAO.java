package isel.si1.datalayer;

import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.Estacao;

import java.util.List;

public interface IEstacaoDAO {

    List<Estacao> GetStations() throws DatabaseException;
}
