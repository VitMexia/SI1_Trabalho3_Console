package isel.si1.datalayer.common;

public class DatabaseException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DatabaseException(String description)
	{
		super(description);
	}
	
	public DatabaseException(Exception exception)
	{
		super(exception);
	}
	
	public DatabaseException(String description, Exception exception)
	{
		super(description, exception);
	}
}
