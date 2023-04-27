package org.example.view;

import org.example.controller.EquipmentController;
import org.example.controller.WellController;
import org.example.model.Well;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class WellView {
    private final Scanner scanner;
    private final WellController wellController;
    private final EquipmentController equipmentController;

    public WellView() {
        this.scanner = new Scanner(System.in);
        this.wellController = new WellController();
        this.equipmentController = new EquipmentController();
    }

    public void showEquipmentCountByWells() {
        System.out.println("Please enter wells names with delimiter ' ' or ','");
        String inputString = scanner.nextLine();
        String[] wellsStrings = inputString.split("[ ,]+");

        Map<String, Long> wellCounts = new HashMap<>();

        Arrays.stream(wellsStrings).forEach(wellName -> {
            Well wellFromRepo = wellController.getWellByName(wellName);

            if (wellFromRepo.getId() == 0) {
                System.out.printf("Well with name '%s' did not found, skipping.%n", wellName);
            } else {
                Long equipmentCount = equipmentController.getAllEquipments()
                        .stream()
                        .filter(equipment -> wellFromRepo.getId().equals(equipment.getWellId()))
                        .count();

                wellCounts.put(wellName, equipmentCount);
            }
        });

        printWellsAndEquipmentsCount(wellCounts);
    }

    private void printWellsAndEquipmentsCount(Map<String, Long> wellCounts) {
        if (wellCounts.isEmpty()) {
            System.out.printf("No wells found.%n%n");
        } else {
            System.out.println("Table of wells names and equipment count on it:");
            System.out.println(wellCounts);
            System.out.println();
        }
    }
}
