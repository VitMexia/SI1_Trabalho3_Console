package isel.si1.console.presentationlayer;

import isel.si1.businesslayer.*;
import isel.si1.model.DocaBicicleta;
import isel.si1.model.Utilizador;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;



public class Actions {
	
	//private IStudentService studentService;
	private IUtilizadorService utilizadorService;
	
	
	//public IStudentService getStudentService() {
//		return studentService;
//	}
	public IUtilizadorService getUtilizadoresService() {
		return utilizadorService;
	}

	//public void setStudentService(IStudentService studentService) {
//		this.studentService = studentService;
//	}

	public void setUtilizadorService(IUtilizadorService utilizadorService) {
		this.utilizadorService = utilizadorService;
	}

	public Actions()
	{
		/* Creates a new StudentService but allows for future refactoring to support Dependency Injection */
		//studentService = new StudentService();
		utilizadorService = new UtilizadorService();
	}
	
	/*MainMenu actions menu methods*/
	
	/*Option 0*/
	public void Quit(Scanner input, Console console) 
	{
		Boolean exit = Utilities.YesOrNoQuestion(input, "Are you sure?");
		if(exit) 
		{
			System.out.println("Thank you for using the program! Bye!");
			console.Terminate();
		}
	}
	
	/*Option 1*/
	public void About(Scanner input, Console console) 
	{
//		Configuration.AboutConfiguration about = Configuration.getInstance().About;
//
//		System.out.println("");
//		System.out.println(about.SchoolName);
//		System.out.println(about.DepartmentName);
//		System.out.println(about.GroupNumber + "# Group number of " + about.Curse);
//		System.out.println("");
	}

	/*Option 2*/
//	public void ListStudents(Scanner input, Console console) throws ServiceException
//	{
//		System.out.println("\nListing the Students.");
//
//		List<Student> curses = studentService.GetStudents();
//		Iterator<Student> it = curses.iterator();
//
//		if(!it.hasNext())
//		{
//			System.out.println("No available students!");
//		}
//		else
//		{
//			Utilities.PrintTableHeaderForStudents();
//			while (it.hasNext())
//			{
//				Utilities.PrintStudent(it.next());
//			}
//		}
//		System.out.println("");
//	}

	public void ListUtilizadores(Scanner input, Console console) throws ServiceException
	{
		System.out.println("\nListing Utilizadores.");

		List<Utilizador> users = utilizadorService.GetUtilizadores();
		Iterator<Utilizador> it = users.iterator();

		if(!it.hasNext())
		{
			System.out.println("No available Utilizadores!");
		}
		else
		{
			Utilities.PrintTableHeaderForUsers();
			while (it.hasNext())
			{
				Utilities.PrintUtilizador(it.next());
			}
		}
		System.out.println("End");
	}
	
	public void XXX(Scanner input, Console console){
	    System.out.println("XXX");
	}
	
	//TODO
	// Insert more action menu items. 
	// The methods must have the following signature "public void [METHODNAME](Scanner input) throws BusinessException"

	/*Option 3*/
	public void OptionNumber3(Scanner input, Console console) throws ServiceException
	{
		System.out.println();
		System.out.println("Not yet implemented!!!");
		System.out.println();
	}

	public void AddUser(Scanner input, Console console) throws ServiceException{

		//Scanner input = new Scanner(System.in);

		System.out.print("Please provide the necessary information to create a New User:\n");

		System.out.print("Email:");
		String email  = input.nextLine();
		System.out.print(email+"\n");

		System.out.print("NIF:");
		int nif = input.nextInt();
		System.out.print(nif+"\n");



		input.nextLine();
		System.out.print("Nome:");
		String name  = input.nextLine();
		System.out.print(name+"\n");

		System.out.print("Select the Passe type from the list below\n");

		NewUserMenu newUserMenu = new NewUserMenu();



		for (int option = 0; option < newUserMenu.menuItems.size(); ++option ) {
			NewUserMenu.MenuItem item = newUserMenu.menuItems.get(option);
			System.out.println(option + " - " + item.description);
		}

		int opcao = -1;
		String referencia = "";

		if (input.hasNextInt())
		{
			opcao = input.nextInt();

			referencia = newUserMenu.menuItems.get(opcao).description;

		}
		else
		{
			String str = input.nextLine();
			System.out.println("Unkown option - > " + str);

		}

		UtilizadorService utilizadorService = new UtilizadorService();
		int i = utilizadorService.InsertPerson(email,nif,name);
		int o = utilizadorService.InsertUser(email, referencia);

		if(i>0){
			System.out.print("New Person Created!\n");
		}else{
			System.out.print("Using Existing Person!\n");
		}
		if(o >0){
			System.out.print("New User Created!");
		}else{
			System.out.print("User Already Exists!");
		}

	}
	public void DeleteEmployee(Scanner input, Console console) throws ServiceException {

		System.out.print("Please select the Pessoa type to delete:\n");

		DeleteUserMenu deleteUserMenu = new DeleteUserMenu();

		for (int option = 0; option < deleteUserMenu.menuItems.size(); ++option ) {
			DeleteUserMenu.MenuItem item = deleteUserMenu.menuItems.get(option);
			System.out.println(option + " - " + item.description);
		}

		int opcao = -1;
		String usertType = "";
		String userDescription = "";
		String emailToDelete = "";

		if (input.hasNextInt())
		{
			opcao = input.nextInt();

			usertType = deleteUserMenu.menuItems.get(opcao).type;
			userDescription = deleteUserMenu.menuItems.get(opcao).description;
		}

		input.nextLine();
		UtilizadorService utilizadorService = new UtilizadorService();

		int deleted = 0;

		if(usertType != ""){

			System.out.print("What " + userDescription + " do you want to Delete (Provide email)?");
			emailToDelete = input.nextLine();

			deleted = utilizadorService.DeleteUser(emailToDelete, usertType);
		}

		System.out.print(deleted + " " + userDescription + " deleted!");


	}


	public void CreateTrip(Scanner input, Console console) throws ServiceException {

		System.out.print("\nPlease select the start station:\n");

		EstacaoMenu estacaoMenu = new EstacaoMenu();

		for (int option = 0; option < estacaoMenu.menuItems.size(); ++option ) {
			EstacaoMenu.MenuItem item = estacaoMenu.menuItems.get(option);
			System.out.println(option + " - " + item.description);
		}
		int opcao = -1;

		int estacaoID = -1;

		if (input.hasNextInt())
		{
			opcao = input.nextInt();

			estacaoID = estacaoMenu.menuItems.get(opcao).idEstacao;
		}

		if(estacaoID != -1){

			DocaBicicletaService docaBicicletaService = new DocaBicicletaService();

			List<DocaBicicleta> bicicletasEmEstacao = docaBicicletaService.GetDocaBicicleta(estacaoID);

			if(bicicletasEmEstacao.size() > 0){

				System.out.print("blabla");
			}

		}

	}

}





























