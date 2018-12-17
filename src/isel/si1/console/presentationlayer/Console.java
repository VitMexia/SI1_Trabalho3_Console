package isel.si1.console.presentationlayer;

//import pt.isel.si1.demo1.app.console.presentationlayer.MainMenu.MenuItem;

import javax.naming.ConfigurationException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.InvalidPropertiesFormatException;
import java.util.Scanner;

//import org.apache.commons.configuration.ConfigurationException;

public class Console {
	
	private MainMenu mainMenu;
	private Actions actions;
	private Boolean running;
	
	public Console() throws NoSuchMethodException, ConfigurationException, InvalidPropertiesFormatException, IOException
	{
		mainMenu = new MainMenu();
		actions = new Actions();
		running = true;
	}
	
	public void run() throws IOException, IllegalArgumentException, IllegalAccessException, InvocationTargetException
	{
		Scanner input = new Scanner(System.in);
		int opcao = -1;

		do{
			ShowMenu();
			System.out.print("Please select an option:");

			if (input.hasNextInt()) 
			{
				opcao = input.nextInt();
				input.nextLine();
				
				if(opcao >= mainMenu.menuItems.size())
				{
					System.out.println("Unkown option - > " + opcao);
					continue;
				}
			}
			else 
			{
				String str = input.nextLine();
				System.out.println("Unkown option - > " + str);
				continue;
			}
			
			try
			{
				mainMenu.menuItems.get(opcao).method.invoke(actions, new Object[]{input, this});
			}
			catch(InvocationTargetException exception)
			{
				System.out.println("Error. Unable to perform the option " + opcao);
				System.out.println("Reason: " + exception.getCause().getMessage());
				System.out.println();
			}
			
		}
		while(running);
	}
	
	public void Terminate() 
	{
		running = false;
	}

	private void ShowMenu()
	{
		System.out.println();
		System.out.println("---------------------------------------------------------------");
		System.out.println("-------------------------MainMenu----------------------------------");

		int numberOfOptions = mainMenu.menuItems.size();
	    for(int option = 0; option < numberOfOptions; ++option)
		{
	    	MainMenu.MenuItem item = mainMenu.menuItems.get(option);
	     	System.out.println(option + " - " + item.description);
	    }
	    System.out.println("---------------------------------------------------------------");
	    System.out.println();
	}
}
