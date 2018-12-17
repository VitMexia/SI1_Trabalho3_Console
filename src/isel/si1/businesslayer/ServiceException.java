package isel.si1.businesslayer;

public class ServiceException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ServiceException(String description)
	{
		super(description);
	}
	
	public ServiceException(Exception exception)
	{
		super(exception);
	}
	
	public ServiceException(String description, Exception exception)
	{
		super(description, exception);
	}
}