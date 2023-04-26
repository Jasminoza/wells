package org.example.service;

import lombok.AllArgsConstructor;
import org.example.model.Equipment;
import org.example.model.Well;
import org.example.repository.EquipmentRepository;
import org.example.repository.hibernate.HibernateEquipmentRepositoryImpl;

import java.util.List;

@AllArgsConstructor
public class EquipmentService {
    private final EquipmentRepository equipmentRepository;

    public EquipmentService() {
        this.equipmentRepository = new HibernateEquipmentRepositoryImpl();
    }

   public List<Equipment> getAll() {
        return equipmentRepository.getAll();
   }

   public Equipment create(Equipment equipment) {
        return equipmentRepository.create(equipment);
   }

   public Equipment getById(Long id) {
        return equipmentRepository.getById(id);
   }

    public Equipment update(Equipment equipment) {
        return equipmentRepository.update(equipment);
    }

    public void delete(Long id) {
        equipmentRepository.delete(id);
    }


}
