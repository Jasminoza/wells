package org.example.controller;

import org.example.model.Well;
import org.example.service.WellService;

import java.util.List;

public class WellController {
    private final WellService wellService;

    public WellController() {
        this.wellService = new WellService();
    }

    public Well createWell(String name) {
        Well well = new Well();
        well.setName(name);
        return wellService.create(well);
    }

    public List<Well> getAllWells() {
        return wellService.getAll();
    }

    public void deleteWell(Long id) {
        wellService.delete(id);
    }

    public Well updateWell(Long id, String name) {
        Well well = wellService.getById(id);
        well.setName(name);
        return wellService.update(well);
    }

    public Well getWellById(Long id) {
        return wellService.getById(id);
    }

    public Well getWellByName(String wellName) {
        return wellService.getWellByName(wellName);
    }
}
