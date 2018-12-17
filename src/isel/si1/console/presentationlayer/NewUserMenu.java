package isel.si1.console.presentationlayer;

import isel.si1.businesslayer.ReferenciaService;
import isel.si1.businesslayer.ServiceException;
import isel.si1.model.Referencia;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class NewUserMenu {

    public class MenuItem
    {
        public String description;

        public MenuItem(String  description){
            this.description = description;
        }
    }

    public ArrayList<MenuItem> menuItems = null;

    public NewUserMenu() throws ServiceException {

        ReferenciaService referenciaService = new ReferenciaService();

        List<Referencia>  refs = referenciaService.GetReferencias();
        Iterator<Referencia> itr = refs.iterator();

        menuItems = new ArrayList<MenuItem>();

        while(itr.hasNext()){
            MenuItem item = new MenuItem( itr.next().referencia);
            menuItems.add(item);

        }


    }
}
