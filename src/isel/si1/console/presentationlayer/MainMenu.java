package isel.si1.console.presentationlayer;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Scanner;


public class MainMenu {

	public class MenuItem
	{
		public String description;
		public Method method;

		public MenuItem(String description, Method method)
		{
			this.description = description;
			this.method = method;
		}
	}
	
	public ArrayList<MenuItem> menuItems = null;
	
	@SuppressWarnings("rawtypes")
	public MainMenu() throws NoSuchMethodException
	{
		menuItems = new ArrayList<MenuItem>();
		Class params[] = {Scanner.class, Console.class};
	
		/*Items do menu*/
		menuItems.add(new MenuItem("Quit", Actions.class.getDeclaredMethod("Quit", params)));
		//menuItems.add(new MenuItem("About", Actions.class.getDeclaredMethod("About", params)));
		menuItems.add(new MenuItem("Listar Utilizadores",  Actions.class.getDeclaredMethod("ListUtilizadores", params)));
		menuItems.add(new MenuItem("Adicionar Utilizador",  Actions.class.getDeclaredMethod("AddUser", params)));
		menuItems.add(new MenuItem("Apagar Utilizador/Funcionario",  Actions.class.getDeclaredMethod("DeleteEmployee", params)));
		//menuItems.add(new MenuItem("Viagem Em Curso",  Actions.class.getDeclaredMethod("CurrentTrip", params)));
		menuItems.add(new MenuItem("Criar Viagem",  Actions.class.getDeclaredMethod("createTrip", params)));

	}
}
