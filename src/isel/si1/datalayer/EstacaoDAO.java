package isel.si1.datalayer;

import isel.si1.datalayer.common.BaseDAO;
import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.Estacao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class EstacaoDAO extends BaseDAO implements IEstacaoDAO {


    @Override
    public List<Estacao> GetStations() throws DatabaseException {
        Connection conn = null;

        try {
            String statementQuery = "SELECT Id, Localização from ESTACAO";
            Estacao item;
            ArrayList<Estacao> container = new ArrayList<>();

            conn = getConnectionFactory().getConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(statementQuery);

            while (rs.next()) {
                item = new Estacao();
                item.id = rs.getInt(1);
                item.localizacao = rs.getString(2);
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
