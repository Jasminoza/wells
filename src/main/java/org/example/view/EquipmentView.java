package org.example.view;

import org.example.controller.EquipmentController;
import org.example.controller.WellController;
import org.example.model.Equipment;
import org.example.model.Well;
import org.example.util.PrintUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class EquipmentView {
    private final Scanner scanner;
    private final EquipmentController equipmentController;
    private final WellController wellController;
    private final WellView wellView;

    public EquipmentView() {
        this.scanner = new Scanner(System.in);
        this.equipmentController = new EquipmentController();
        this.wellController = new WellController();
        this.wellView = new WellView();
    }

    public void showAllEquipments() {
        List<Equipment> equipments = equipmentController.getAllEquipments();

        if (equipments.isEmpty()) {
            PrintUtils.printEquipmentsListIsEmpty();
        } else {
            PrintUtils.printListInfo(equipments);
        }
    }

    public void getEquipmentById() {
        System.out.println("NOT IMPLEMENTED");
    }

    public void createEquipment() {
        List<Equipment> equipments = equipmentController.getAllEquipments();
        createNewEquipment(equipments);
    }

    public void deleteEquipment() {
        System.out.println("NOT IMPLEMENTED");
    }

    public void updateEquipment() {
        System.out.println("NOT IMPLEMENTED");
    }


    private void createNewEquipment(List<Equipment> equipments) {
        Well well = wellView.tryToGetWillById();

        if (well.getId() == null) {
            System.out.println("Cannot create equipment, wells list is empty");
            return;
        }

        createEquipmentsForWell(equipments, well);
    }

    private void createEquipmentsForWell(List<Equipment> equipments, Well well) {
        System.out.println("Enter equipment name: ");

        boolean nameIsAlreadyExist = true;
        String name;

        while (nameIsAlreadyExist) {
            name = scanner.nextLine();
            final String finalName = name;
            if (equipments.stream().anyMatch(equipment -> equipment.getName().equalsIgnoreCase(finalName))) {
                System.out.println("Equipment with such name is already exist. Please, enter another equipment name.");
            } else {
                nameIsAlreadyExist = false;
                Equipment equipment = equipmentController.createEquipment(name, well);
                System.out.print("Created equipment: ");
                PrintUtils.printEntityInfo(equipment);
            }
        }
    }

    public void createMultiplyEquipment() {
        System.out.println("Enter well name: ");
        String wellName = scanner.nextLine();

        System.out.println("Please, enter number of equipments you want to add to well: " + wellName);

        long neededEquipmentCount;
        boolean numberIsCorrect = false;

        while (!numberIsCorrect) {
            try {
                neededEquipmentCount = Long.parseLong(scanner.nextLine());

                if (neededEquipmentCount <= 0) {
                    System.out.println("Please, enter positive number.");
                    return;
                }

                if (neededEquipmentCount > 10) {
                    System.out.println("You cannot create more then 10 equipments at once.");
                    return;
                }

                numberIsCorrect = true;
                Well wellForEquipment = getWellForEquipment(wellName);
                List<Equipment> createdEquipments = equipmentController.createMultiplyEquipments(neededEquipmentCount, wellForEquipment);

                System.out.printf("Created equipments at well '%s':%n", wellForEquipment.getName());
                PrintUtils.printListInfo(createdEquipments);

            } catch (NumberFormatException e) {
                System.out.println("Please, enter correct number.");
            }
        }
    }

    private Well getWellForEquipment(String wellName) {
        Well wellFromRepo = wellController.getWellByName(wellName);
        return wellFromRepo != null ? wellFromRepo : wellController.createWell(wellName);
    }

    public void showEquipmentsByWell() {
        List<Equipment> equipments = equipmentController.getAllEquipments();

        if (equipments.isEmpty()) {
            PrintUtils.printEquipmentsListIsEmpty();
            return;
        }


        List<Well> wells = wellController.getAllWells();

        if (wells.isEmpty()) {
            PrintUtils.printWellsListIsEmpty();
            return;
        }

        PrintUtils.printListInfo(wells);
        System.out.println("Please enter wells names with delimiter ' ' or ','");

        String inputString = scanner.nextLine();

        String[] wellsStrings = inputString.split("[ ,]+");

        Map<String, Long> wellCounts = new HashMap<>();

        Arrays.stream(wellsStrings).forEach(wellName -> {

            Well wellFromRepo = wellController.getWellByName(wellName);
            if (wellFromRepo != null) {
                Long equipmentCount = equipmentController.getAllEquipments()
                        .stream()
                        .filter(eq -> wellFromRepo.getId().equals(eq.getWellId())).count();

                wellCounts.put(wellName, equipmentCount);
            } else {
                System.out.printf("Well with name '%s' did not found, skipping.%n", wellName);
            }
        });

        if (wellCounts.size() > 0) {
            System.out.println("Table of wells names and equipment count on it:");
            System.out.println(wellCounts);
        } else {
            System.out.println("No wells found.");
        }
    }
}