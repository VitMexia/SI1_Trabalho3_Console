package isel.si1.console.presentationlayer;

import isel.si1.businesslayer.ReferenciaService;
import isel.si1.businesslayer.ServiceException;
import isel.si1.model.Referencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeleteUserMenu {

    public class MenuItem
    {
        public String description;
        public String type;


        public MenuItem(String  description, String type){

            this.description = description;
            this.type = type;
        }

    }

    public ArrayList<MenuItem> menuItems = null;

    public DeleteUserMenu() throws ServiceException {


        menuItems = new ArrayList<MenuItem>();


        //MenuItem item = new MenuItem("Apagar Pessoa");
        MenuItem item2 = new MenuItem("Utilizador", "PASSEUTILIZADOR");
        MenuItem item3 = new MenuItem("Funcionario", "Funcionario");

         //menuItems.add(item);
         menuItems.add(item2);
         menuItems.add(item3);




    }
}
