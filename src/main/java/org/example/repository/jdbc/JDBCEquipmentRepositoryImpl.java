package org.example.repository.jdbc;

import lombok.SneakyThrows;
import org.example.model.Equipment;
import org.example.repository.EquipmentRepository;
import org.example.util.JDBCUtils;
import org.example.util.ResultSetConverter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class JDBCEquipmentRepositoryImpl implements EquipmentRepository {
    private static final String table = "equipments";

    @Override
    @SneakyThrows
    public List<Equipment> getAll() {
        String sql = String.format("SELECT * FROM '%s'", table);

        PreparedStatement statement = JDBCUtils.getPreparedStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        return ResultSetConverter.convertToEquipmentsList(resultSet);
    }

    @Override
    @SneakyThrows
    public Equipment create(Equipment equipment) {
        insertEquipment(equipment);
        return getByName(equipment.getName());
    }

    private void insertEquipment(Equipment equipment) {
        String sql = String.format("INSERT INTO '%s' ('name', 'well_id') VALUES (?, ?)", table);

        try (PreparedStatement statement = JDBCUtils.getPreparedStatement(sql)) {
            statement.setString(1, equipment.getName());
            statement.setLong(2, equipment.getWellId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private Equipment getByName(String equipmentName) {
        String sql = String.format("SELECT * FROM '%s' WHERE name = ?", table);

        try (PreparedStatement statement = JDBCUtils.getPreparedStatement(sql)) {
            statement.setString(1, equipmentName);
            ResultSet resultSet = statement.executeQuery();
            return ResultSetConverter.convertToEquipment(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
