package org.example.repository.jdbc;

import lombok.SneakyThrows;
import org.example.model.Well;
import org.example.repository.WellRepository;
import org.example.util.JDBCUtils;
import org.example.util.ResultSetConverter;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class JDBCWellRepositoryImpl implements WellRepository {
    private static final String table = "wells";

    @Override
    @SneakyThrows
    public List<Well> getAll() {
        String sql = String.format("SELECT * FROM '%s'", table);

        PreparedStatement statement = JDBCUtils.getPreparedStatement(sql);
        ResultSet resultSet = statement.executeQuery();
        return ResultSetConverter.convertToWellsList(resultSet);
    }

    @Override
    public Well create(Well well) {
        String sql = String.format("INSERT INTO '%s' ('name') VALUES (?)", table);

        try (PreparedStatement statement = JDBCUtils.getPreparedStatement(sql)) {
            statement.setString(1, well.getName());
            statement.execute();
            return getByName(well.getName());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Well getByName(String wellName) {
        String sql = String.format("SELECT * FROM '%s' WHERE name = ?", table);

        try (PreparedStatement statement = JDBCUtils.getPreparedStatement(sql)) {
            statement.setString(1, wellName);
            ResultSet resultSet = statement.executeQuery();
            return ResultSetConverter.convertToWell(resultSet);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

