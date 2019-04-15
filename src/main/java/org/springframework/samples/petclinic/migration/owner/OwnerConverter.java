package org.springframework.samples.petclinic.migration.owner;

import org.springframework.samples.petclinic.migration.builder.OwnerBuilder;
import org.springframework.samples.petclinic.owner.Owner;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OwnerConverter {

    public  Owner ownerConverter(ResultSet resultSet) throws SQLException {
        return OwnerBuilder.buildOwner(
            resultSet.getInt("id"),
            resultSet.getString("first_name"),
            resultSet.getString("last_name"),
            resultSet.getString("address"),
            resultSet.getString("city"),
            resultSet.getString("telephone")
        );
    }

    public List<Owner> ownerListConverter(ResultSet resultSet) throws SQLException {
        List<Owner> owners = new ArrayList<>();

        while (resultSet.next()) {
            owners.add(ownerConverter(resultSet));
        }

        return owners;
    }

}
