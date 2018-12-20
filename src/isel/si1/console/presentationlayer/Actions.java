package isel.si1.console.presentationlayer;

import isel.si1.businesslayer.*;
import isel.si1.model.DocaBicicleta;
import isel.si1.model.Utilizador;
import isel.si1.model.Viagem;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;



public class Actions {

	private IUtilizadorService utilizadorService;

	public IUtilizadorService getUtilizadoresService() {
		return utilizadorService;
	}


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



	public void ListUtilizadores(Scanner input, Console console) throws ServiceException {
		System.out.println("\nListing Utilizadores.");

		List<Utilizador> users = utilizadorService.getUtilizadores();
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

	public void getTrips(Scanner input, Console console) throws ServiceException, ParseException {

		System.out.print("\nChoose User you want to see trips from:\n");

		ListUserMenus listUserMenus = new ListUserMenus();

		for (int option = 0; option < listUserMenus.menuItems.size(); ++option ) {
			ListUserMenus.MenuItem item = listUserMenus.menuItems.get(option);
			System.out.println(option + " - " + item.description);
		}

		int useroption = -1;
		int id_Passe = -1;
		String emailUser = "";
		Date startTS;
		Date endTS;

		if(input.hasNextInt()){

			useroption = input.nextInt();

			id_Passe = listUserMenus.menuItems.get(useroption).id_Passe;
			emailUser = listUserMenus.menuItems.get(useroption).emailUser;
		}

		if(emailUser != "") {

			DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			//Date date = format.parse(string);

			System.out.print("\nPlease provide Initial Date (yyyy-mm-dd):\n");

			input.nextLine();
			startTS = format.parse(input.nextLine());


			System.out.print("\nPlease provide Final Date (yyyy-mm-dd):\n");

			endTS  = format.parse(input.nextLine());

			ViagemService viagemService = new ViagemService();

			List<Viagem> viagem = viagemService.getViagensBicicleta(id_Passe, startTS, endTS);
			Iterator<Viagem> it = viagem.iterator();

			if(!it.hasNext())
			{
				System.out.println("No available Viagens for the time frame!");
			}
			else
			{
				Utilities.PrintTableHeaderForViagemBicicleta();

				while (it.hasNext())
				{
					Utilities.PrintViagemBicicleta(it.next(), emailUser);
				}
			}

		}


		}

	public void createTrip(Scanner input, Console console) throws ServiceException {

		System.out.print("\nWho are you?:\n");

		ListUserMenus listUserMenus = new ListUserMenus();

		for (int option = 0; option < listUserMenus.menuItems.size(); ++option ) {
			ListUserMenus.MenuItem item = listUserMenus.menuItems.get(option);
			System.out.println(option + " - " + item.description);
		}

		int useroption = -1;
		int id_passe = -1;

		if(input.hasNextInt()){

			useroption = input.nextInt();

			id_passe = listUserMenus.menuItems.get(useroption).id_Passe;
		}

		if(id_passe != -1) {


			System.out.print("\nPlease select the start station:\n");

			EstacaoMenu estacaoMenu = new EstacaoMenu();

			for (int option = 0; option < estacaoMenu.menuItems.size(); ++option) {
				EstacaoMenu.MenuItem item = estacaoMenu.menuItems.get(option);
				System.out.println(option + " - " + item.description);
			}
			int opcao = -1;

			int estacaoID = -1;

			if (input.hasNextInt()) {
				opcao = input.nextInt();

				estacaoID = estacaoMenu.menuItems.get(opcao).idEstacao;
			}

			if (estacaoID != -1) {

				DocaBicicletaService docaBicicletaService = new DocaBicicletaService();
				List<DocaBicicleta> bicicletasEmEstacao = docaBicicletaService.GetDocaBicicleta(estacaoID);

				if (bicicletasEmEstacao.size() > 0) {

					BicicletaMenu bicicletaMenu = new BicicletaMenu(estacaoID);

					if (bicicletaMenu.menuItems.size() > 0) {

						System.out.print("Select one of the available Biciletas:\n");

						for (int option = 0; option < bicicletaMenu.menuItems.size(); ++option) {
							BicicletaMenu.MenuItem item = bicicletaMenu.menuItems.get(option);
							System.out.println(option + " - " + item.description);
						}

						opcao = input.nextInt();

						int bicicletaID = bicicletaMenu.menuItems.get(opcao).idBicicleta;

						ViagemService viagemService = new ViagemService();
						int createdtrip = viagemService.createTrip(id_passe, bicicletaID, estacaoID);

						if(createdtrip <1){
							System.out.print("Viagem Failed to be created!");
						}
						else {
							System.out.print("Viagem started!");
						}
					}

				} else {

					System.out.print("No Bicicletas available at this Station!");
				}

			}
		}

	}

	public void closeTrip(Scanner input, Console console) throws ServiceException {

		System.out.print("\nPlease select the trip that is ending:\n");

		OpenTripsMenu openTripsMenu = new OpenTripsMenu();

		int opcao = -1;
		int idPasse = -1;
		Timestamp dataInicial;

		for (int option = 0; option < openTripsMenu.menuItems.size(); ++option) {
			OpenTripsMenu.MenuItem item = openTripsMenu.menuItems.get(option);
			System.out.println(option + " - " + item.description);
		}

		opcao = input.nextInt();

		if(opcao>-1){

			idPasse = openTripsMenu.menuItems.get(opcao).id_Passe;
			dataInicial = openTripsMenu.menuItems.get(opcao).data_inicial;

			System.out.print("\nPlease select the end station:\n");

			EstacaoMenu estacaoMenu = new EstacaoMenu();

			for (int option = 0; option < estacaoMenu.menuItems.size(); ++option) {
				EstacaoMenu.MenuItem item = estacaoMenu.menuItems.get(option);
				System.out.println(option + " - " + item.description);
			}
			int opcao2 = -1;

			int estacaoID = -1;

			if (input.hasNextInt()) {
				opcao2 = input.nextInt();

				estacaoID = estacaoMenu.menuItems.get(opcao).idEstacao;
			}

			if(estacaoID > -1) {

				ViagemService viagemService = new ViagemService();

				int endTrip = -1;
				int star = -1;
				String review = "";

				idPasse = openTripsMenu.menuItems.get(opcao).id_Passe;

				Boolean evaluation = Utilities.YesOrNoQuestion(input, "Do you want to leave an evaluation?\n");

				if (evaluation) {


					while(star <1 || star >5){
						System.out.println("Please provide a rating from 1 to 5: \n");
						if (input.hasNextInt()) {
							star = input.nextInt();
						}
					}

					Boolean message = Utilities.YesOrNoQuestion(input, "Do you want to leave a review?\n");

					if(message){
						while(review == ""){
							System.out.println("Please ,feel free to write a review: \n");

							review = input.nextLine();
						}
						endTrip = viagemService.endTrip(idPasse, dataInicial,estacaoID,star, review);
					}
					else{
						endTrip = viagemService.endTrip(idPasse, dataInicial,estacaoID,star, "");
					}

				}
				else {

					endTrip = viagemService.endTrip(idPasse, dataInicial,estacaoID,-1, "");
				}

				if(endTrip >0){
					System.out.println("Trip Ended!");
				}
				else {
					System.out.println("Unable to end trip! please try again!");
				}
			}
		}


	}

}





























