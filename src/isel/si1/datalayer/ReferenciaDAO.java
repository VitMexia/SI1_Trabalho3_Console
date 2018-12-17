package isel.si1.datalayer;

import isel.si1.datalayer.common.BaseDAO;
import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.Referencia;
import isel.si1.model.Utilizador;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReferenciaDAO extends BaseDAO implements IReferenciaDAO {
    @Override
    public List<Referencia> GetReferencias() throws DatabaseException {
        Connection conn = null;

        try {
            String statementQuery = "SELECT Referência from Tipo Group by Referência";
            Referencia item;
            ArrayList<Referencia> container = new ArrayList<>();

            conn = getConnectionFactory().getConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(statementQuery);

            while (rs.next()) {
                item = new Referencia();
                item.referencia = rs.getString(1);

                container.add(item);
            }

            return container;
        } catch (Exception exception) {
            throw new DatabaseException(
                    "Unable to list Referencia. \nCause: "
                            + exception.getMessage(), exception);
        } finally {
            closeConnection(conn);
        }
    }
}
