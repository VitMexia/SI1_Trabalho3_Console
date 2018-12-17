package isel.si1.datalayer;

import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.Referencia;



import java.util.List;

public interface IReferenciaDAO {
    List<Referencia> GetReferencias() throws DatabaseException;
}
