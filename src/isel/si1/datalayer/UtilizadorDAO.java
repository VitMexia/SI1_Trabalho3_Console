package isel.si1.datalayer;

import isel.si1.datalayer.common.BaseDAO;
import isel.si1.datalayer.common.DatabaseException;
import isel.si1.model.Utilizador;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UtilizadorDAO extends BaseDAO implements IUtilizadorDAO {
    @Override
    public List<Utilizador> GetUtilizadores() throws DatabaseException {
        Connection conn = null;

        try {
            String statementQuery = "SELECT p.Email ,NIF ,Nome ,Id_Passe ,Saldo ,Referência ,Data_Registo_Aquisição FROM PASSEUTILIZADOR pu inner join PESSOA p on pu.Email = p.Email";
            Utilizador item;
            ArrayList<Utilizador> container = new ArrayList<>();

            conn = getConnectionFactory().getConnection();

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(statementQuery);

            while (rs.next()) {
                item = new Utilizador();
                item.email = rs.getString(1);
                item.nif = rs.getInt(2);
                item.nome = rs.getString(3);
                item.id_Passe = rs.getInt(4);
                item.saldo = rs.getInt(5);
                item.referencia = rs.getString(6);
                item.data_Registo_Aquisicao = rs.getDate(7);
                container.add(item);
            }

            return container;
        } catch (Exception exception) {
            throw new DatabaseException(
                    "Unable to list Utilizadores. \nCause: "
                            + exception.getMessage(), exception);
        } finally {
            closeConnection(conn);
        }
    }

    @Override
    public int InsertPerson(String Email, int nif, String nome) throws DatabaseException {
        Connection conn = null;
        try{
            String statementQuery = "Insert into PESSOA (Email, NIF, Nome) Values(?,?,?)";

            conn = getConnectionFactory().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(statementQuery);

            preparedStatement.setString(1, Email);
            preparedStatement.setInt(2, nif);
            preparedStatement.setString(3, nome);

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
                "Unable to rollback on InsertPerson. \nCause: "
                        + exception.getMessage(), exception);
            }

            if(exception.getMessage().contains("Violation of PRIMARY KEY")){
                return 0;
            }

            throw new DatabaseException(
                    "Unable to InsertPerson. \nCause: "
                            + exception.getMessage(), exception);
        }
        finally {
            closeConnection(conn);
        }

    }

    @Override
    public int InsertUtilizador(String Email, String referencia) throws DatabaseException {
        Connection conn = null;
        try{

            conn = getConnectionFactory().getConnection();

            java.sql.Date javaDate = new java.sql.Date(System.currentTimeMillis());

            String passeUtilizador = "Insert into PASSEUTILIZADOR(Id_Passe, Email, Data_Registo_Aquisição, Referência) " +
                                                            "Values(((Select Max(Id_Passe) from PASSEUTILIZADOR)+1), ?, ?,?)";
            PreparedStatement preparedStatement1 = conn.prepareStatement(passeUtilizador);

            preparedStatement1.setString(1, Email);
            preparedStatement1.setDate(2, javaDate);
            preparedStatement1.setString(3, referencia);

            int inserted2 = preparedStatement1.executeUpdate();
            conn.commit();
            return inserted2;

        }
        catch (Exception exception){
            try {
                conn.rollback();
            }
            catch(SQLException ex){
                throw new DatabaseException(
                        "Unable to rollback on InsertUser. \nCause: "
                                + exception.getMessage(), exception);
            }

            if(exception.getMessage().contains("Violation of PRIMARY KEY")){
                return 0;
            }

            throw new DatabaseException(
                    "Unable to InsertUser. \nCause: "
                            + exception.getMessage(), exception);
        }
        finally {
            closeConnection(conn);
        }

    }

    @Override
    public int DeleteUser(String Email, String userType) throws DatabaseException {

        Connection conn = null;

        try{
            String statementQuery = "";

            if(userType == "Funcionario"){

                statementQuery = "delete from Funcionario  where email = ?";
            }
            else
            {
                statementQuery = "delete from PasseUtilizador  where email = ?";
            }


            conn = getConnectionFactory().getConnection();
            PreparedStatement preparedStatement = conn.prepareStatement(statementQuery);

           // preparedStatement.setString(1, userType);
            preparedStatement.setString(1, Email);

            int deleted = preparedStatement.executeUpdate();
            conn.commit();
            return deleted;


        }
        catch (Exception exception){
            try {
                conn.rollback();
            }
            catch(SQLException ex){
                throw new DatabaseException(
                        "Unable to rollback on delete "+ userType+". \nCause: "
                                + exception.getMessage(), exception);
            }

            if(exception.getMessage().contains("REFERENCE constraint") ){
                throw new DatabaseException(
                        "Unable to delete this "+ userType+ " due to being associated with a Viagem or a Reposição "
                                , null);
            }

            throw new DatabaseException(
                    "Unable to InsertPerson. \nCause: "
                            + exception.getMessage(), exception);
        }
        finally {
            closeConnection(conn);
        }
    }
}
