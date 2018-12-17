package isel.si1.datalayer;

import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.Bicicleta;


import java.util.List;

public interface IBicicletaDAO {
    List<Bicicleta> getAvailableBicicleta(int id_Estacao) throws DatabaseException;
}
