package isel.si1.datalayer;

import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.Bicicleta;
import isel.si1.model.DocaBicicleta;
import isel.si1.model.Estacao;


import java.util.List;

public interface IBicicletaDAO {
    List<Bicicleta> getAvailableBicicletas(int id_Estacao) throws DatabaseException;
}
