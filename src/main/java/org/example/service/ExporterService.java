package org.example.service;

import lombok.AllArgsConstructor;
import org.example.dto.DBInfo;
import org.example.repository.DbRepository;
import org.example.repository.jdbc.JDBCDbRepositoryImpl;

@AllArgsConstructor
public class ExporterService {
    private final DbRepository dbRepository;

    public ExporterService() {
        this.dbRepository = new JDBCDbRepositoryImpl();
    }

    public DBInfo getAllWellsWithEquipments() {
        return dbRepository.getAllWellsWithEquipments();
    }
}
