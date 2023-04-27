package org.example.util;

import lombok.SneakyThrows;
import org.example.model.Equipment;
import org.example.model.Well;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.StringWriter;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultSetConverter {

    public static Equipment convertToEquipment(ResultSet resultSet) throws SQLException {
        if (resultSet.isBeforeFirst()) {
            resultSet.next();
        }
        Equipment equipment = new Equipment();
        equipment.setId(resultSet.getLong("id"));
        equipment.setName(resultSet.getString("name"));
        equipment.setWellId(resultSet.getLong("well_id"));
        return equipment;
    }

    public static List<Equipment> convertToEquipmentsList(ResultSet resultSet) throws SQLException {
        List<Equipment> allEquipments = new ArrayList<>();

        while (resultSet.next()) {
            Equipment equipment = convertToEquipment(resultSet);
            allEquipments.add(equipment);
        }

        return allEquipments;
    }

    public static Well convertToWell(ResultSet resultSet) throws SQLException {
        if (resultSet.isBeforeFirst()) {
            resultSet.next();
        }
        Well well = new Well();
        well.setId(resultSet.getLong("id"));
        well.setName(resultSet.getString("name"));
        return well;
    }

    @SneakyThrows
    public static List<Well> convertToWellsList(ResultSet resultSet) {
        List<Well> wellList = new ArrayList<>();

        while (resultSet.next()) {
            Well well = convertToWell(resultSet);
            wellList.add(well);
        }

        return wellList;
    }
}