package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Equipment;
import org.example.repository.EquipmentRepository;
import org.example.repository.jdbc.JDBCEquipmentRepositoryImpl;

import java.util.List;

@AllArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public EquipmentService() {
        this.equipmentRepository = new JDBCEquipmentRepositoryImpl();
    }

    public List<Equipment> getAll() {
        return equipmentRepository.getAll();
    }

    public Equipment create(Equipment equipment) {
        return equipmentRepository.create(equipment);
    }
}
