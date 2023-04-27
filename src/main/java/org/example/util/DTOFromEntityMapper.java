package org.example.util;

import org.example.dto.EquipmentDto;
import org.example.dto.WellDto;
import org.example.model.Equipment;
import org.example.model.Well;

import java.util.ArrayList;
import java.util.List;

public class DTOFromEntityMapper {

    private DTOFromEntityMapper() {}

    public static List<WellDto> getWellDtoListFromWellsList(List<Well> allWells) {
        List<WellDto> wellDtoList = new ArrayList<>();

        allWells.forEach(well -> {
            WellDto wellDto = new WellDto();
            wellDto.setId(well.getId());
            wellDto.setName(well.getName());
            wellDtoList.add(wellDto);
        });

        return wellDtoList;
    }

    public static List<EquipmentDto> getEquipmentDtoListFromEquipmentList(List<Equipment> allEquipments) {
        List<EquipmentDto> equipmentDtoList = new ArrayList<>();

        allEquipments.forEach(equipment -> {
            EquipmentDto equipmentDto = new EquipmentDto();
            equipmentDto.setId(equipment.getId());
            equipmentDto.setName(equipment.getName());
            equipmentDtoList.add(equipmentDto);
        });

        return equipmentDtoList;
    }
}
