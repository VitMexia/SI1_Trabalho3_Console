package isel.si1.datalayer;

import isel.si1.datalayer.common.BaseDAO;
import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.Viagem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ViagemDAO extends BaseDAO implements IViagemDAO {

    @Override
    public List<Viagem> getViagens(String email, Date dataIncial, Date dataFinal) throws DatabaseException {
        return null;
    }

    @Override
    public int createTrip(int id_Passe, int id_Bicicleta, int id_Estacao_Inicial) throws DatabaseException  {
        Connection conn = null;

        Date date = new Date();

        java.sql.Date jdate = new java.sql.Date(date.getTime());

        try{
            String statementQuery = "Insert into Viagem (Id_Passe, DataInicial, Id_Bicicleta, Id_Estacao_Inicial) Values(?,?,?,?)";

            conn = getConnectionFactory().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(statementQuery);

            preparedStatement.setInt(1, id_Passe);
            preparedStatement.setDate(2, jdate);
            preparedStatement.setInt(3, id_Bicicleta);
            preparedStatement.setInt(4, id_Estacao_Inicial);

            int inserted = preparedStatement.executeUpdate();
            conn.commit();
            return inserted;


        }
        catch (Exception exception){
            try {
                conn.rollback();
            }
            catch(SQLException ex){
                throw new DatabaseException(
                        "Unable to rollback on CreatViagem. \nCause: "
                                + exception.getMessage(), exception);
            }

            if(exception.getMessage().contains("Violation of PRIMARY KEY")){
                return 0;
            }

            throw new DatabaseException(
                    "Unable to CreteViagem. \nCause: "
                            + exception.getMessage(), exception);
        }
        finally {
            closeConnection(conn);
        }
    }

    @Override
    public int endTrip(Date dataFinal, int id_Estacao_Final, int avaliação, String mensagem)  throws DatabaseException {
        return 0;
    }
}
