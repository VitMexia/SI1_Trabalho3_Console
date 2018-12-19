package isel.si1.datalayer;

import isel.si1.datalayer.common.BaseDAO;
import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.DocaBicicleta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DocaBicicletaDAO extends BaseDAO implements IDocaBicicletaDAO {
    @Override
    public List<DocaBicicleta> GetBicicletsDoca(int id_Estacao) throws DatabaseException {
        Connection conn = null;

        try {
            String statementQuery = "SELECT db.Id_Estação, Numero_Doca, Id_Bicicleta " +
                    "from  DOCA_BICICLETA db inner join DOCA d " +
                    "on db.Id_Estação = d.Id_Estação and db.Numero_Doca = d.Numero " +
                    "where db.Id_Estação = ? and Estado = ?";
            DocaBicicleta item;
            ArrayList<DocaBicicleta> container = new ArrayList<>();

            conn = getConnectionFactory().getConnection();

           // Statement stmt = conn.createStatement();

            PreparedStatement preparedStatement = conn.prepareStatement(statementQuery);
            preparedStatement.setInt(1, id_Estacao);
            preparedStatement.setString(2, "Ocupado");


            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                item = new DocaBicicleta();
                item.id_Estacao = rs.getInt(1);
                item.Numero = rs.getInt(2);
                item.id_Bicicleta =rs.getInt(3);

                container.add(item);
            }

            return container;
        } catch (Exception exception) {
            throw new DatabaseException(
                    "Unable to list ESTACAO. \nCause: "
                            + exception.getMessage(), exception);
        } finally {
            closeConnection(conn);
        }
    }
}
