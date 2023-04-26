package org.example.controller;

import org.example.model.Equipment;
import org.example.model.Well;
import org.example.service.EquipmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EquipmentController {
    private final EquipmentService equipmentService;

    public EquipmentController() {
        this.equipmentService = new EquipmentService();
    }

    public List<Equipment> getAllEquipments() {
        return equipmentService.getAll();
    }

    public Equipment createEquipment(String name, Well well) {
        Equipment equipment = new Equipment();
        equipment.setName(name);
        equipment.setWellId(well.getId());
        return equipmentService.create(equipment);
    }

    public List<Equipment> createMultiplyEquipments(long neededEquipmentCount, Well well) {
        Optional<Equipment> equipmentWithMaxId = java.util.Optional.ofNullable(getAllEquipments().stream()
                .max((a, b) -> (a.getId() > b.getId()) ? 1 : -1)
                .orElse(null));

        List<Equipment> createdEquipments = new ArrayList<>();

        long nextId = equipmentWithMaxId
                .map(equipment -> equipment.getId() + 1L)
                .orElse(1L);

        for (long i = 1; i <= neededEquipmentCount; i++) {
            Equipment currentEquipment = createEquipment("EQ" + nextId, well);
            createdEquipments.add(currentEquipment);
            nextId++;
        }

        return createdEquipments;
    }
}

