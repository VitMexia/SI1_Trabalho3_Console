package isel.si1.datalayer;

import isel.si1.datalayer.common.BaseDAO;
import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.Bicicleta;
import isel.si1.model.DocaBicicleta;
import isel.si1.model.Estacao;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BicicletaDAO extends BaseDAO implements IBicicletaDAO{
    @Override
    public List<Bicicleta> getAvailableBicicletas(int id_Estacao) throws DatabaseException {

        Connection conn = null;

        try {


            String statementQuery = " SELECT b.Id ,[Designação] ,[RaioRodas] ,[ValorViagem] ,[Peso] " +
                    ",iif(bc.Id is not null, 'Classica', 'Electrica') Tipo ,iif(be.id is not null, be.autonomia, -1) Autonomia " +
                    "FROM [BICICLETA] b left join CLASSICA bc on b.Id = bc.Id left join ELECTRICA be on b.id = be.Id " +
                    "where b.id in ( SELECT Id_Bicicleta " +
                    "From  DOCA_BICICLETA db inner join DOCA d " +
                    "on db.Id_Estação = d.Id_Estação and db.Numero_Doca = d.Numero " +
                    "where db.Id_Estação = ? and Estado = ? )";



            Bicicleta item;
            ArrayList<Bicicleta> container = new ArrayList<>();

            conn = getConnectionFactory().getConnection();


            PreparedStatement preparedStatement = conn.prepareStatement(statementQuery);
            preparedStatement.setInt(1, id_Estacao);
            preparedStatement.setString(2, "Ocupado");

            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                item = new Bicicleta();
                item.id = rs.getInt(1);
                item.designacao = rs.getString(2);
                item.raioRodas =rs.getDouble(3);
                item.valorViagem = rs.getDouble(4);
                item.peso = rs.getInt(5);
                item.tipoBicicleta = rs.getString(6);
                item.autonomia = rs.getInt(7);

                container.add(item);
            }

            return container;
        } catch (Exception exception) {
            throw new DatabaseException(
                    "Unable to list Bicicleta. \nCause: "
                            + exception.getMessage(), exception);
        } finally {
            closeConnection(conn);
        }
    }
}
