package org.springframework.samples.petclinic.migration.owner;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.migration.SQLiteConnector;
import org.springframework.samples.petclinic.migration.helper.SQLiteHelper;
import org.springframework.samples.petclinic.owner.Owner;
import org.springframework.samples.petclinic.toggles.Toggles;
import org.springframework.util.DigestUtils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;


@Slf4j
public class OwnerSQLiteRepository {
    private final String TABLE_NAME = "owners";
    private OwnerConverter ownerConverter;

    private final String CREATE_TABLE =
        "CREATE TABLE " + TABLE_NAME + " (" +
            "id         INTEGER PRIMARY KEY AUTOINCREMENT," +
            "first_name VARCHAR(30)," +
            "last_name  VARCHAR_IGNORECASE(30)," +
            "address    VARCHAR(255)," +
            "city       VARCHAR(80)," +
            "telephone  VARCHAR(20)" +
            ");";

    private final String CREATE_INDEX = "CREATE INDEX owners_last_name ON owners (last_name);";
    private final String DROP_TABLE = "DROP TABLE IF EXISTS owners;";
    private final String INSERT = "INSERT INTO owners (first_name, last_name, address, city, telephone) VALUES (?, ?, ?, ?, ?);";
    private final String INSERT_WITH_ID = "INSERT INTO owners (id, first_name, last_name, address, city, telephone) VALUES (?, ?, ?, ?, ?, ?);";
    private final String UPDATE = "UPDATE owners SET first_name = ?, last_name = ?, address = ?, city = ?, telephone = ? WHERE id = ?;";


    @Autowired
    public OwnerSQLiteRepository(OwnerConverter ownerConverter){
        this.ownerConverter = ownerConverter;
    }

    public List<Owner> findAll() throws SQLException {
        Optional<ResultSet> resultSet = Optional.empty();
        List<Owner> owners = new ArrayList<>();
        try {
            resultSet = Optional.of(SQLiteHelper.fetchFindAllQuery(TABLE_NAME));
            owners = ownerConverter.ownerListConverter(resultSet.get());
            return owners;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            resultSet.get().close();
        }
        return owners;
    }

    public Optional<Owner> findById(Integer id) throws SQLException {
        Optional<ResultSet> resultSet = Optional.empty();
        Owner owner;
        try {
            resultSet = Optional.of(SQLiteHelper.fetchFindByIdQuery(TABLE_NAME, id));
            owner = ownerConverter.ownerConverter(resultSet.get());
            return Optional.of(owner);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            resultSet.get().close();
        }
        return Optional.empty();
    }

    public List<Owner> findByLastName(String lastName) throws SQLException {
        Optional<ResultSet> resultSet = Optional.empty();
        List<Owner> owners = new ArrayList<>();
        try {
            resultSet = Optional.of(SQLiteHelper.fetchFindByLastName(TABLE_NAME, lastName));
            owners = ownerConverter.ownerListConverter(resultSet.get());
            return owners;
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        finally {
            resultSet.get().close();
        }
        return owners;
    }

    public void dropTable() throws SQLException {
        Connection connection = SQLiteConnector.getDBConnection();
        Statement statement = connection.createStatement();
        statement.execute(DROP_TABLE);
        statement.close();
        connection.close();
    }

    public void createTable() throws SQLException {
        Connection connection = SQLiteConnector.getDBConnection();
        Statement statement = connection.createStatement();
        statement.execute(CREATE_TABLE);
        statement.execute(CREATE_INDEX);
        statement.close();
        connection.close();
    }

    public void saveOrUpdate(Owner owner, String action) throws SQLException {
        Connection connection = SQLiteConnector.getDBConnection();
        PreparedStatement preparedStatement;

        if (action.equals("save")){
            preparedStatement = connection.prepareStatement(INSERT);
        }else {
            preparedStatement = connection.prepareStatement(UPDATE);
        }

        preparedStatement.setString(1, owner.getFirstName());
        preparedStatement.setString(2, owner.getLastName());
        preparedStatement.setString(3, owner.getAddress());
        preparedStatement.setString(4, owner.getCity());
        preparedStatement.setString(5, owner.getTelephone());

        preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
    }

    public void saveOrUpdate(List<Owner> owners) {
        owners.forEach(owner -> {
            try {
                saveOrUpdate(owner, "save");
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        });
    }

    public void forkliftOwners(List<Owner> owners) {
        if (Toggles.FORKLIFT) {
            saveOrUpdate(owners);
        }
    }

    public int consistencyChecker(List<Owner> owners){
        AtomicInteger inconsistencyCounter = new AtomicInteger();
        //log.info("Consistency checker for owners");
        List<Owner> updatedOwners;
        try{
            updatedOwners = findAll();
            if(owners.size() > updatedOwners.size()){
                restartForkLift(owners);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        owners.forEach(oldOwner -> {
            try{
                Owner newOwnerDb = findById(oldOwner.getId()).orElse(new Owner());
                if (!consistentOwners(oldOwner, newOwnerDb)){
                    inconsistencyCounter.getAndIncrement();
                    //logger.info("inconsistency found, inconsistency count :" + inconsistencyCounter);
                    saveOrUpdate(oldOwner, "update");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        });
        return inconsistencyCounter.get();
    }

    public int consistency(List<Owner> owners) {
        if(Toggles.CONSISTENCY) {
            return consistencyChecker(owners);
        }
        else if (Toggles.LONG_TERM_CONSISTENCY_CHECKER) {
            longTermConsistency(owners);
        }
        return 0;
    }

    public boolean longTermConsistency(List<Owner> owners) {
        try {
            List<Owner> sqliteOwners = findAll();
            String oldHash = "";
            String newHash = "";

            for(Owner oldOwner : owners) {
                oldHash = DigestUtils.md5DigestAsHex((oldHash + oldOwner.getFirstName()).getBytes());
            }

            for(Owner newOwner : sqliteOwners) {
                newHash = DigestUtils.md5DigestAsHex((newHash + newOwner.getFirstName()).getBytes());
            }

            if (!oldHash.equals(newHash)) {
                return true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    private boolean consistentOwners(Owner mysqlOwner, Owner sqliteOwner) {
        return (
            mysqlOwner.getAddress().equals(sqliteOwner.getAddress()) &&
            mysqlOwner.getCity().equals(sqliteOwner.getCity()) &&
            mysqlOwner.getTelephone().equals(sqliteOwner.getTelephone()) &&
            mysqlOwner.getFirstName().equals(sqliteOwner.getFirstName()) &&
            mysqlOwner.getLastName().equals(sqliteOwner.getLastName())
        );
    }

    private void restartForkLift(List<Owner> owners) {
        try {
            dropTable();
            createTable();
            saveOrUpdate(owners);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }


}
