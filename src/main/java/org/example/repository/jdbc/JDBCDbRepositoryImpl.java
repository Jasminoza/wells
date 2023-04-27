package org.example.repository.jdbc;

import org.example.dto.DBInfo;
import org.example.dto.EquipmentDto;
import org.example.dto.WellDto;
import org.example.model.Equipment;
import org.example.model.Well;
import org.example.repository.DbRepository;
import org.example.repository.EquipmentRepository;
import org.example.repository.WellRepository;

import java.util.List;

import static org.example.util.DTOFromEntityMapper.getEquipmentDtoListFromEquipmentList;
import static org.example.util.DTOFromEntityMapper.getWellDtoListFromWellsList;


public class JDBCDbRepositoryImpl implements DbRepository {

    private final EquipmentRepository equipmentRepository;
    private final WellRepository wellRepository;

    public JDBCDbRepositoryImpl() {
        this.equipmentRepository = new JDBCEquipmentRepositoryImpl();
        this.wellRepository = new JDBCWellRepositoryImpl();
    }

    public DBInfo getAllWellsWithEquipments() {
        DBInfo dbInfo = new DBInfo();

        List<Well> allWells = wellRepository.getAll();
        List<WellDto> wellDtoList = getWellDtoListFromWellsList(allWells);

        List<Equipment> allEquipments = equipmentRepository.getAll();
        wellDtoList = getWellDtoListWithUsedEquipments(wellDtoList, allEquipments);

        dbInfo.setWellDtoList(wellDtoList);

        return dbInfo;
    }

    private List<WellDto> getWellDtoListWithUsedEquipments(List<WellDto> wellDtoList, List<Equipment> allEquipments) {
        List<WellDto> updatedWellDtoList = List.copyOf(wellDtoList);

        updatedWellDtoList.forEach(wellDto -> {
            List<Equipment> equipmentList = getEquipmentListForWellDto(allEquipments, wellDto);
            List<EquipmentDto> equipmentDtoList = getEquipmentDtoListFromEquipmentList(equipmentList);
            wellDto.setEquipmentDtoList(equipmentDtoList);
        });

        return updatedWellDtoList;
    }

    private List<Equipment> getEquipmentListForWellDto(List<Equipment> allEquipments, WellDto wellDto) {
        return allEquipments
                .stream()
                .filter(equipment -> equipment.getWellId().equals(wellDto.getId()))
                .toList();
    }


}
