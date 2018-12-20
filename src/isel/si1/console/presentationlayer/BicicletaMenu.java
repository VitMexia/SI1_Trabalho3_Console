package isel.si1.console.presentationlayer;

import isel.si1.businesslayer.BicicletaService;
import isel.si1.businesslayer.EstacaoService;
import isel.si1.businesslayer.ServiceException;
import isel.si1.model.Bicicleta;
import isel.si1.model.DocaBicicleta;
import isel.si1.model.Estacao;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BicicletaMenu {

    public class MenuItem
    {
        public String description;
        public int idBicicleta;


        public MenuItem(String  description, int idBicicleta){

            this.description = description;
            this.idBicicleta = idBicicleta;

        }

    }

    public ArrayList<BicicletaMenu.MenuItem> menuItems = null;



    public BicicletaMenu(int id_Estacao) throws ServiceException {

        menuItems = new ArrayList<BicicletaMenu.MenuItem>();

        BicicletaService bicicletaService = new BicicletaService();
        List<Bicicleta> bicicletaList = bicicletaService.getAvailableBicicletas(id_Estacao);

        Iterator<Bicicleta> itr = bicicletaList.iterator();

        while(itr.hasNext()){

            Bicicleta bicicleta = itr.next();

            BicicletaMenu.MenuItem item = new BicicletaMenu.MenuItem("Bicicleta "
                    + bicicleta.tipoBicicleta + ", Mudanças: " + bicicleta.designacao + ", Peso: "
                    + bicicleta.peso + ", Autonomia: "+ (bicicleta.autonomia == -1 ? "N/A" : bicicleta.autonomia)
                    + "Raio das Rodas: " + bicicleta.raioRodas +", Custo/30m: " + bicicleta.valorViagem
                    , bicicleta.id);
            menuItems.add(item);

        }


    }
}
