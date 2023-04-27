package org.example.util;

import org.example.dto.EquipmentDto;
import org.example.dto.WellDto;
import org.example.model.Equipment;
import org.example.model.Well;

import java.util.ArrayList;
import java.util.List;

public class DBExporter {

    public static List<WellDto> getWellDtoListFromWellsList(List<Well> allWells) {
        List<WellDto> wellDtoList = new ArrayList<>();

        for (Well well : allWells) {
            WellDto wellDto = new WellDto();
            wellDto.setId(well.getId());
            wellDto.setName(well.getName());
            wellDtoList.add(wellDto);
        }
        return wellDtoList;
    }

    public static List<EquipmentDto> getEquipmentDtoListFromEquipmentList(List<Equipment> allEquipments) {
        List<EquipmentDto> equipmentDtoList = new ArrayList<>();

        for (Equipment equipment : allEquipments) {
            EquipmentDto equipmentDto = new EquipmentDto();
            equipmentDto.setId(equipment.getId());
            equipmentDto.setName(equipment.getName());
            equipmentDtoList.add(equipmentDto);
        }
        return equipmentDtoList;
    }
}
