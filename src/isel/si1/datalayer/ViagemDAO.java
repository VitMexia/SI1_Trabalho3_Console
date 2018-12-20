package isel.si1.datalayer;

import isel.si1.datalayer.common.BaseDAO;
import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.Bicicleta;
import isel.si1.model.Viagem;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ViagemDAO extends BaseDAO implements IViagemDAO {

    @Override
    public List<Viagem> getViagensBicicleta(int id_Passe, Date dataIncial, Date dataFinal) throws DatabaseException {
        Connection conn = null;
        ArrayList<Viagem> container = new ArrayList<>();

        try{
            String statementQuery = "Select Id_Passe, DataInicial, [Designação] ,[RaioRodas] ,[ValorViagem] ,[Peso] ,Tipo, Autonomia " +
                    "FROM (" +
                        "SELECT Id_Passe, DataInicial, [Designação] ,[RaioRodas] ,[ValorViagem] ,[Peso] ," +
                        "iif(bc.Id is not null, 'Classica', 'Electrica') Tipo ,iif(be.id is not null, be.autonomia, -1) Autonomia " +
                        "FROM VIAGEM V inner join (BICICLETA b " +
                            "left join CLASSICA bc on b.Id = bc.Id " +
                            "left join ELECTRICA be on b.id = be.Id) on v.id_bicicleta = b.id " +
                        "WHERE Id_Passe = ? and DataInicial between ? and ? " +
                    ") t " +
                    "order by DataInicial asc, Tipo  asc;";

            java.sql.Date sjDate = new java.sql.Date(dataIncial.getTime());
            java.sql.Date ejDate = new java.sql.Date(dataFinal.getTime());


            conn = getConnectionFactory().getConnection();

            Statement stmt = conn.createStatement();

            PreparedStatement preparedStatement = conn.prepareStatement(statementQuery);

            preparedStatement.setInt(1, id_Passe);
            preparedStatement.setDate(2, sjDate);
            preparedStatement.setDate(3, ejDate);


            ResultSet rs = preparedStatement.executeQuery();

            Viagem item;


            while (rs.next()) {
                item = new Viagem();

                item.id_Passe = rs.getInt(1);
                item.data_Inicial = rs.getTimestamp(2);
                item.bicicleta = new Bicicleta();
                item.bicicleta.designacao = rs.getString(3);
                item.bicicleta.raioRodas = rs.getDouble(4);
                item.bicicleta.valorViagem = rs.getDouble(5);
                item.bicicleta.peso = rs.getInt(6);
                item.bicicleta.tipoBicicleta = rs.getString(7);
                item.bicicleta.autonomia = rs.getInt(8);

                container.add(item);
            }


        }
        catch (Exception exception){

            throw new DatabaseException(
                    "Unable to getViagensBicicleta. \nCause: "
                            + exception.getMessage(), exception);
        }
        finally {
            closeConnection(conn);
        }
        return container;
    }

    @Override
    public int createTrip(int id_Passe, int id_Bicicleta, int id_Estacao_Inicial) throws DatabaseException  {
        Connection conn = null;

        Date date = new Date();

        java.sql.Timestamp jdate = new java.sql.Timestamp(date.getTime());



        try{
            String statementQuery = "IF NOT EXISTS " +
                    "(select Id_Passe " +
                    "from Viagem " +
                    "where Id_Passe = ? and DataFinal is null) " +
                    "Insert into Viagem (Id_Passe, DataInicial, Id_Bicicleta, Id_Estacao_Inicial)" +
                                    " Values(?,?,?,?) ";


            conn = getConnectionFactory().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(statementQuery);

            preparedStatement.setInt(1, id_Passe);
            preparedStatement.setInt(2, id_Passe);
            preparedStatement.setTimestamp(3, jdate);
            preparedStatement.setInt(4, id_Bicicleta);
            preparedStatement.setInt(5, id_Estacao_Inicial);


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
    public int endTrip(int id_Passe, Timestamp data_Inicial, int id_Estacao_Final, int avaliação, String mensagem)  throws DatabaseException {
        Connection conn = null;

        Date date = new Date();

        java.sql.Timestamp jdate = new java.sql.Timestamp(date.getTime());

        try{
            String statementQuery = "Update VIAGEM set Id_Estacao_Final = ?, " +
                                        "DataFinal = ?, Avaliação = iif( ? = -1, null, ?), " +
                                        "Menssagem = iif( ? != '' , ?, null) " +
                                    "WHERE Id_Passe = ? and DataInicial = ? " +
                                        "and DataFinal is null";


            conn = getConnectionFactory().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(statementQuery);

            preparedStatement.setInt(1, id_Estacao_Final);
            preparedStatement.setTimestamp(2, jdate);
            preparedStatement.setInt(3, avaliação);
            preparedStatement.setInt(4, avaliação);
            preparedStatement.setString(5, mensagem);
            preparedStatement.setString(6, mensagem);
            preparedStatement.setInt(7, id_Passe);
            preparedStatement.setTimestamp(8, data_Inicial);

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
                        "Unable to rollback on EndViagem. \nCause: "
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
    public List<Viagem> getOpenedViagens() throws DatabaseException {

        Connection conn = null;
        ArrayList<Viagem> container = new ArrayList<>();

        try{
            String statementQuery = "SELECT Id_Passe, DataInicial " +
                                    "FROM VIAGEM " +
                                    "WHERE DataFinal is null";


            conn = getConnectionFactory().getConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(statementQuery);

            Viagem item;


            while (rs.next()) {
                item = new Viagem();

                item.id_Passe = rs.getInt(1);
                item.data_Inicial = rs.getTimestamp(2);


                container.add(item);
            }


        }
        catch (Exception exception){

            throw new DatabaseException(
                    "Unable to getOpenedViagens. \nCause: "
                            + exception.getMessage(), exception);
        }
        finally {
            closeConnection(conn);
        }
        return container;
    }
}
