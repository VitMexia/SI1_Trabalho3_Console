package isel.si1.datalayer;

import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.DocaBicicleta;

import java.util.List;

public interface IDocaBicicletaDAO {

    List<DocaBicicleta> GetBicicletsDoca(int id_Estacao) throws DatabaseException;
}
