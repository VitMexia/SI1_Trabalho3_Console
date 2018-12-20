package isel.si1.console.presentationlayer;

import isel.si1.businesslayer.ServiceException;
import isel.si1.businesslayer.ViagemService;
import isel.si1.model.Viagem;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

public class OpenTripsMenu{

    public class MenuItem
    {
        public String description;
        public int id_Passe;
        public Timestamp data_inicial;

        public MenuItem(String  description, int id_Passe, Timestamp data_inicial){

            this.description = description;
            this.id_Passe = id_Passe;
            this.data_inicial = data_inicial;
        }
    }

    public ArrayList<OpenTripsMenu.MenuItem> menuItems = null;

    public OpenTripsMenu() throws ServiceException {

        menuItems = new ArrayList<OpenTripsMenu.MenuItem>();

        ViagemService viagemService = new ViagemService();

        List<Viagem> refs = viagemService.getOpenedViagens();
        Iterator<Viagem> itr = refs.iterator();



        while(itr.hasNext()){

            Viagem viagem = itr.next();

            OpenTripsMenu.MenuItem item = new OpenTripsMenu.MenuItem( "Número Passe: " + viagem.id_Passe
                    + ", Data/Hora começo: " + viagem.data_Inicial, viagem.id_Passe, viagem.data_Inicial );
            menuItems.add(item);

        }




    }
}
