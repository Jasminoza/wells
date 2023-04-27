package org.example.util;

import org.example.dto.DBInfo;
import org.example.dto.EquipmentDto;
import org.example.dto.WellDto;

public class XMLCreator {
    XMLCreator() {
    }

    public static String createXMLFromDBInfo(DBInfo dbInfo) {
        StringBuilder sb = new StringBuilder();

        sb.append("<dbInfo>");
        sb.append("\n");

        for (WellDto wellDto : dbInfo.getWellDtoList()) {
            sb.append("\t");
            sb.append(String.format("<well name=\"%s\" id=\"%s\">", wellDto.getName(), wellDto.getId()));
            sb.append("\n");

            for (EquipmentDto equipmentDto : wellDto.getEquipmentDtoList()) {
                sb.append("\t");
                sb.append("\t");
                sb.append(String.format("<equipment name=\"%s\" id=\"%s\"/>", equipmentDto.getName(), equipmentDto.getId()));
                sb.append("\n");
            }

            sb.append("\t");
            sb.append("</well>");
            sb.append("\n");
        }

        sb.append("</dbInfo>");

        return sb.toString();
    }
}
