package org.example.repository;

import org.example.dto.DBInfo;

public interface DbRepository extends GenericRepository<DBInfo, Long> {
    public DBInfo getAllWellsWithEquipments();
}
