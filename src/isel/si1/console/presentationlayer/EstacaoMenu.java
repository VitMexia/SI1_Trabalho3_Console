package isel.si1.console.presentationlayer;

import isel.si1.businesslayer.EstacaoService;
import isel.si1.businesslayer.ReferenciaService;
import isel.si1.businesslayer.ServiceException;
import isel.si1.model.Estacao;
import isel.si1.model.Referencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EstacaoMenu {

    public class MenuItem
    {
        public String description;
        public int idEstacao;


        public MenuItem(String  description, int idEstacao){

            this.description = description;
            this.idEstacao = idEstacao;

        }

    }

    public ArrayList<EstacaoMenu.MenuItem> menuItems = null;

    public EstacaoMenu() throws ServiceException {


        EstacaoService estacaoService = new EstacaoService();

        List<Estacao> refs = estacaoService.GetStations();
        Iterator<Estacao> itr = refs.iterator();

        menuItems = new ArrayList<EstacaoMenu.MenuItem>();

        while(itr.hasNext()){

            Estacao estacao = itr.next();

            EstacaoMenu.MenuItem item = new EstacaoMenu.MenuItem( estacao.id + ", " + estacao.localizacao, estacao.id );
            menuItems.add(item);

        }




    }
}
