package isel.si1.console.presentationlayer;

import isel.si1.model.Utilizador;

import java.util.Scanner;


public class Utilities {
	public static int GetInt(Scanner input, String caption, String onErrorCaption)
	{
		int result;
		do {
			System.out.print(caption);
			if (input.hasNextInt()) 
			{
				result = input.nextInt();
				input.nextLine();
				
				return result;
			}
			else 
			{
				input.nextLine();
				System.out.println(onErrorCaption);
				System.out.println();
			}
		}
		while(true);
	}
	
	public static  String GetString(Scanner input, String caption)
	{
		System.out.print(caption);
		return input.nextLine();
	}
	
	public static  boolean AreYouSure(Scanner input)
	{
		return YesOrNoQuestion(input, "Are you sure");
	}
	
	public static  boolean YesOrNoQuestion(Scanner input, String questionCaption)
	{
		String question = null;
		while(true)
		{
			question = GetString(input, questionCaption + "[(Y)es/(N)o]:");
			if(question.toUpperCase().equals("Y"))
			{
				return true;
			} 
			else if (question.toUpperCase().equals("N"))
			{
				return false;
			}
		}
	}

	/*
	 * 0 no
	 * 1 yes
	 * -1 quit 
	 * */
	public static  int YesOrNoQuitQuestion(Scanner input, String questionCaption)
	{
		String question = null;
		while(true)
		{
			question = GetString(input, questionCaption + "[(Y)es/(N)o/(Q)uit]:");
			
			if(question.toUpperCase().equals("Y"))
			{
				return 1;
			}
			else if (question.toUpperCase().equals("N"))
			{
				return 0;
			}
			else if (question.toUpperCase().equals("Q"))
			{
				return -1;
			}
		}
	}
	
	
	/* Student*/
	public static void PrintTableHeaderForUsers()
	{
		System.out.printf("%-15s%-25s%-20s%-15s%-15s%-25s%-10s\n","Nome", "Email", "NIF", "ID_PASSE", "Saldo", "Referencia", "Data Registo/Aquisicao");
		System.out.println("********************************************************************************************************************************************");
	}


	public static void PrintUtilizador(Utilizador utilizador)
	{
//		String name = student.Name.length()>15?student.Name.substring(0, 14):student.Name;
//		String address = student.Address.length()>20?student.Address.substring(0, 19):student.Address;
		System.out.printf("%-15s%-25s%-20d%-15d%-15f%-25s%-10s\n",
					utilizador.nome, utilizador.email, utilizador.nif,
					utilizador.id_Passe, utilizador.saldo, utilizador.referencia,
					utilizador.data_Registo_Aquisicao.toString() );
	}
}
