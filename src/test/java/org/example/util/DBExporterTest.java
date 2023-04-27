package org.example.util;

import org.example.dto.EquipmentDto;
import org.example.dto.WellDto;
import org.example.model.Equipment;
import org.example.model.Well;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DBExporterTest {

    @Test
    void getWellDtoListFromWellsList() {
        List<Well> allWells = getWellsList();

        List<WellDto> wellDtoList = DBExporter.getWellDtoListFromWellsList(allWells);

        assertEquals(allWells.size(), wellDtoList.size());
        assertEquals(allWells.get(0).getId(), wellDtoList.get(0).getId());
        assertEquals(allWells.get(0).getName(), wellDtoList.get(0).getName());
        assertEquals(allWells.get(1).getId(), wellDtoList.get(1).getId());
        assertEquals(allWells.get(1).getName(), wellDtoList.get(1).getName());
    }

    @Test
    void getEquipmentDtoListFromEquipmentList() {
        List<Equipment> allEquipments = getEquipmentsList();

        List<EquipmentDto> equipmentDtoList = DBExporter.getEquipmentDtoListFromEquipmentList(allEquipments);

        assertEquals(allEquipments.size(), equipmentDtoList.size());
        assertEquals(allEquipments.get(0).getId(), equipmentDtoList.get(0).getId());
        assertEquals(allEquipments.get(0).getName(), equipmentDtoList.get(0).getName());

        assertEquals(allEquipments.get(1).getId(), equipmentDtoList.get(1).getId());
        assertEquals(allEquipments.get(1).getName(), equipmentDtoList.get(1).getName());
    }

    private static List<Well> getWellsList() {
        List<Well> allWells = new ArrayList<>();

        Well well1 = new Well();
        well1.setId(1L);
        well1.setName("One");

        Well well2 = new Well();
        well2.setId(2L);
        well2.setName("Two");

        allWells.add(well1);
        allWells.add(well2);

        return allWells;
    }

    private static List<Equipment> getEquipmentsList() {
        List<Equipment> allEquipments = new ArrayList<>();

        Equipment equipment1 = new Equipment();
        equipment1.setId(1L);
        equipment1.setName("One");
        equipment1.setWellId(1L);

        Equipment equipment2 = new Equipment();
        equipment2.setId(2L);
        equipment2.setName("Two");
        equipment2.setWellId(1L);

        allEquipments.add(equipment1);
        allEquipments.add(equipment2);

        return allEquipments;
    }
}