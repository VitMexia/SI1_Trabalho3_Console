package isel.si1.console.presentationlayer;

import isel.si1.businesslayer.ServiceException;
import isel.si1.businesslayer.UtilizadorService;
import isel.si1.model.Utilizador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListUserMenus {

    public class MenuItem
    {
        public String description;
        public int id_Passe;


        public MenuItem(String  description, int id_Passe){

            this.description = description;
            this.id_Passe = id_Passe;
        }

    }

    public ArrayList<ListUserMenus.MenuItem> menuItems = null;

    public ListUserMenus() throws ServiceException {

        menuItems = new ArrayList<ListUserMenus.MenuItem>();

        UtilizadorService utilizadorService = new UtilizadorService();
        List<Utilizador> utilizadorList = utilizadorService.getUtilizadores();

        Iterator<Utilizador> itr = utilizadorList.iterator();

        while (itr.hasNext()) {

            Utilizador utilizador = itr.next();

            ListUserMenus.MenuItem item = new ListUserMenus.MenuItem("Email: " + utilizador.email
                    + ", Name: " + utilizador.nome
                    , utilizador.id_Passe);
            menuItems.add(item);

        }
    }
}
