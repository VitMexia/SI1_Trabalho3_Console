package isel.si1.businesslayer;

import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.Utilizador;

import java.util.List;

public interface IUtilizadorService extends IService {

	List<Utilizador> getUtilizadores() throws ServiceException;
	int InsertPerson(String Email, int nif, String nome) throws DatabaseException, ServiceException;
	int InsertUser(String Email, String referencia) throws ServiceException;
	int DeleteUser(String Email, String userType) throws  ServiceException;
}
