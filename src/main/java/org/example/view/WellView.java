package org.example.view;

import org.example.controller.WellController;
import org.example.model.Well;

import java.util.List;
import java.util.Scanner;

public class WellView {
    private final Scanner scanner;
    private final WellController wellController;

    public WellView() {
        this.scanner = new Scanner(System.in);
        this.wellController = new WellController();
    }

    public void showAllWells() {
        List<Well> wells = wellController.getAllWells();

        if (wells.isEmpty()) {
            printWellsListIsEmpty();
        } else {
            printWellsListInfo(wells);
        }
    }

    public void getWellById() {
        List<Well> wells = wellController.getAllWells();

        if (wells.isEmpty()) {
            printWellsListIsEmpty();
        } else {
            printWellsListInfo(wells);
            tryToGetWillById(wells);
        }
    }

    public void createWell() {
        List<Well> wells = wellController.getAllWells();
        createNewWell(wells);
    }

    public void deleteWell() {
        List<Well> wells = wellController.getAllWells();

        if (wells.isEmpty()) {
            printWellsListIsEmpty();
        } else {
            printWellsListInfo(wells);
            tryToDeleteWellById(wells);
        }
    }

    public void updateWell() {
        List<Well> wells = wellController.getAllWells();

        if (wells.isEmpty()) {
            printWellsListIsEmpty();
        } else {
            printWellsListInfo(wells);
            tryToUpdateWell(wells);
        }
    }

    public Well tryToGetWillById() {
        List<Well> wells = wellController.getAllWells();

        if (wells.isEmpty()) {
            printWellsListIsEmpty();
            return new Well();
        } else {
            printWellsListInfo(wells);
            System.out.println("Please, enter id number of well you want to see: ");

            boolean idIsCorrect = false;
            Long id = null;

            while (!idIsCorrect) {
                try {
                    id = Long.parseLong(scanner.nextLine());
                    final Long finalId = id;
                    if (wells.stream().anyMatch(well -> well.getId().equals(finalId))) {
                        idIsCorrect = true;
                    } else {
                        printNoWellFoundById();
                    }
                } catch (NumberFormatException e) {
                    printEnterCorrectId();
                }
            }
            return wellController.getWellById(id);
        }
    }

    private static void printWellsListIsEmpty() {
        System.out.println("Wells list is empty.");
    }

    private static void printWellsListInfo(List<Well> wells) {
        System.out.println("Wells:\n===============================");
        wells.forEach(WellView::printWellInfo);
        System.out.println("===============================");
    }

    private static void printWellInfo(Well well) {
        System.out.println(well.toString());
    }

    private static void printEnterCorrectId() {
        System.out.println("Please, enter correct id.");
    }

    private static void printNoWellFoundById() {
        System.out.println("There is no well with such id. Please, try again.");
    }

    private void tryToGetWillById(List<Well> wells) {
        System.out.println("Please, enter id number of well you want to see: ");

        boolean idIsCorrect = false;
        Long id;

        while (!idIsCorrect) {
            try {
                id = Long.parseLong(scanner.nextLine());
                final Long finalId = id;
                if (wells.stream().anyMatch(well -> well.getId().equals(finalId))) {
                    idIsCorrect = true;
                    Well well = wellController.getWellById(id);
                    printWellInfo(well);
                } else {
                    printNoWellFoundById();
                }
            } catch (NumberFormatException e) {
                printEnterCorrectId();
            }
        }
    }

    private void createNewWell(List<Well> wells) {
        System.out.println("Enter well name: ");

        boolean nameIsAlreadyExist = true;
        String name;

        while (nameIsAlreadyExist) {
            name = scanner.nextLine();
            final String finalName = name;
            if (wells.stream().anyMatch(well -> well.getName().equalsIgnoreCase(finalName))) {
                System.out.println("Well with such name is already exist. Please, enter another well name.");
            } else {
                nameIsAlreadyExist = false;
                Well well = wellController.createWell(name);
                System.out.print("Created well: ");
                printWellInfo(well);
            }
        }
    }

    private void tryToDeleteWellById(List<Well> wells) {
        System.out.println("Enter id number to delete well: ");

        boolean idIsCorrect = false;
        Long id;

        while (!idIsCorrect) {
            try {
                id = Long.parseLong(scanner.nextLine());
                final Long finalId = id;
                if (wells.stream().anyMatch(well -> well.getId().equals(finalId))) {
                    idIsCorrect = true;
                    wellController.deleteWell(id);
                } else {
                    printNoWellFoundById();
                }
            } catch (NumberFormatException e) {
                printEnterCorrectId();
            }
        }
    }

    private void tryToUpdateWell(List<Well> wells) {
        System.out.println("Please, enter id number of well you want to update: ");

        boolean idIsCorrect = false;
        Long id;
        while (!idIsCorrect) {
            try {
                id = Long.parseLong(scanner.nextLine());
                final Long finalId = id;
                if (wells.stream().anyMatch(well -> well.getId().equals(finalId))) {
                    idIsCorrect = true;
                    System.out.println("Please, enter new name: ");
                    String name = scanner.nextLine();
                    Well updatedWell = wellController.updateWell(id, name);
                    printWellInfo(updatedWell);
                } else {
                    printNoWellFoundById();
                }
            } catch (NumberFormatException e) {
                printEnterCorrectId();
            }
        }
    }
}
