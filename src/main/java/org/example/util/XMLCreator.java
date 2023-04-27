package org.example.util;

import org.example.dto.DBInfo;
import org.example.dto.EquipmentDto;
import org.example.dto.WellDto;

public class XMLCreator {
    XMLCreator() {
    }

    public static String createXMLFromDBInfo(DBInfo dbInfo) {
        StringBuilder sb = new StringBuilder();
        appendDbOpenTag(sb);
        appendWellsTags(dbInfo, sb);
        appendDbClosingTag(sb);

        return sb.toString();
    }

    private static void appendWellsTags(DBInfo dbInfo, StringBuilder sb) {
        dbInfo.getWellDtoList().forEach(wellDto -> {
            appendWellOpenTag(sb, wellDto);
            appendWellsEquipments(sb, wellDto);
            appendWellClosingTag(sb);
        });
    }

    private static void appendWellsEquipments(StringBuilder sb, WellDto wellDto) {
        wellDto.getEquipmentDtoList().forEach(equipmentDto -> appendEquipmentTag(sb, equipmentDto));
    }

    private static void appendDbClosingTag(StringBuilder sb) {
        sb.append("</dbInfo>");
    }

    private static void appendWellClosingTag(StringBuilder sb) {
        sb.append("\t");
        sb.append("</well>");
        sb.append("\n");
    }

    private static void appendEquipmentTag(StringBuilder sb, EquipmentDto equipmentDto) {
        sb.append("\t");
        sb.append("\t");
        sb.append(String.format("<equipment name=\"%s\" id=\"%s\"/>", equipmentDto.getName(), equipmentDto.getId()));
        sb.append("\n");
    }

    private static void appendWellOpenTag(StringBuilder sb, WellDto wellDto) {
        sb.append("\t");
        sb.append(String.format("<well name=\"%s\" id=\"%s\">", wellDto.getName(), wellDto.getId()));
        sb.append("\n");
    }

    private static void appendDbOpenTag(StringBuilder sb) {
        sb.append("<dbInfo>");
        sb.append("\n");
    }
}
