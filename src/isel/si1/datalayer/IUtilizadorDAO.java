package isel.si1.datalayer;



import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.Utilizador;

import java.util.List;

public interface IUtilizadorDAO {
    List<Utilizador> GetUtilizadores() throws DatabaseException;
    int InsertPerson(String Email, int nif, String nome) throws DatabaseException;
    int InsertUtilizador(String Email, String referencia) throws DatabaseException;
    int DeleteUser(String Email, String userType) throws DatabaseException;
    //List<>
}
