package isel.si1.businesslayer;

import isel.si1.datalayer.IUtilizadorDAO;
import isel.si1.datalayer.UtilizadorDAO;
import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.Utilizador;

import java.util.List;

public class UtilizadorService implements IUtilizadorService {

	IUtilizadorDAO utilizadorDAO;


	public IUtilizadorDAO getUtilizadorDAO() {
		return utilizadorDAO;
	}

	public void setUtilizadorDAO(IUtilizadorDAO utilizadorDAO) {
		this.utilizadorDAO = utilizadorDAO;
	}

	public UtilizadorService()
	{
		/* Creates a new DAO but allows for future refactoring to support Dependency Injection */
		utilizadorDAO = new UtilizadorDAO();
	}
	
	@Override
	public List<Utilizador> GetUtilizadores() throws ServiceException {
		try 
		{
			return utilizadorDAO.GetUtilizadores();
		} 
		catch (DatabaseException exception) 
		{
			throw new ServiceException(exception.getMessage(), exception);
		}
	}

	@Override
	public int InsertPerson(String Email, int nif, String nome) throws ServiceException {
			try {
				return utilizadorDAO.InsertPerson(Email, nif,nome);

			}
			catch(DatabaseException exception) {

				throw new ServiceException(exception.getMessage(), exception);

			}

	}

	@Override
	public int InsertUser(String Email, String referencia) throws ServiceException {
		try {
			return utilizadorDAO.InsertUtilizador(Email, referencia);

		}
		catch(DatabaseException exception) {

			throw new ServiceException(exception.getMessage(), exception);

		}

	}

	@Override
	public int DeleteUser(String Email, String userType) throws ServiceException {
		try {
			return utilizadorDAO.DeleteUser(Email, userType);

		}
		catch(DatabaseException exception) {

			throw new ServiceException(exception.getMessage(), exception);

		}
	}
}
