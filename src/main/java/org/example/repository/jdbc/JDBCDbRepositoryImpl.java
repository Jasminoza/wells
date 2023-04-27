package org.example.repository.jdbc;

import jdk.jshell.spi.ExecutionControl;
import lombok.SneakyThrows;
import org.example.dto.DBInfo;
import org.example.dto.EquipmentDto;
import org.example.dto.WellDto;
import org.example.model.Equipment;
import org.example.model.Well;
import org.example.repository.DbRepository;
import org.example.repository.EquipmentRepository;
import org.example.repository.WellRepository;

import java.util.List;

import static org.example.util.DBExporter.getEquipmentDtoListFromEquipmentList;
import static org.example.util.DBExporter.getWellDtoListFromWellsList;


public class JDBCDbRepositoryImpl implements DbRepository {

    private EquipmentRepository equipmentRepository;
    private WellRepository wellRepository;

    public JDBCDbRepositoryImpl() {
        this.equipmentRepository = new JDBCEquipmentRepositoryImpl();
        this.wellRepository = new JDBCWellRepositoryImpl();
    }

    @Override
    @SneakyThrows
    public List<DBInfo> getAll() {
        throw new ExecutionControl.NotImplementedException("NOT IMPLEMENTED");
    }

    @Override
    @SneakyThrows
    public DBInfo create(DBInfo dbInfo) {
        throw new ExecutionControl.NotImplementedException("NOT IMPLEMENTED");
    }

    public DBInfo getAllWellsWithEquipments() {
        DBInfo dbInfo = new DBInfo();

        List<Well> allWells = wellRepository.getAll();
        List<WellDto> wellDtoList = getWellDtoListFromWellsList(allWells);

        List<Equipment> allEquipments = equipmentRepository.getAll();

        for (WellDto wellDto : wellDtoList) {
            List<Equipment> equipmentList = allEquipments
                    .stream()
                    .filter(equipment -> equipment.getWellId().equals(wellDto.getId()))
                    .toList();
            List<EquipmentDto> equipmentDtoList = getEquipmentDtoListFromEquipmentList(equipmentList);
            wellDto.setEquipmentDtoList(equipmentDtoList);
        }

        dbInfo.setWellDtoList(wellDtoList);

        return dbInfo;
    }


}
