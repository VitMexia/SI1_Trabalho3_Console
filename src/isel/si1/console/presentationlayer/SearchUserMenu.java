package isel.si1.console.presentationlayer;

import isel.si1.businesslayer.ServiceException;

import java.util.ArrayList;

public class SearchUserMenu {

    public class MenuItem
    {
        public String description;

        public MenuItem(String  description){
            this.description = description;
        }
    }

    public ArrayList<MenuItem> menuItems = null;

    public SearchUserMenu() throws ServiceException {


        menuItems = new ArrayList<MenuItem>();


        //MenuItem item = new MenuItem("Apagar Pessoa");
        MenuItem item2 = new MenuItem("Search Utilizador");
        MenuItem item3 = new MenuItem("Search Funcionário");

         //menuItems.add(item);
         menuItems.add(item2);
         menuItems.add(item3);




    }
}
