package org.example.view;

import java.util.Scanner;

public class MainView {
    private final WellView wellView;
    private final EquipmentView equipmentView;
    private final ExporterView exporterView;
    private final Scanner sc;

    public MainView() {
        this.wellView = new WellView();
        this.equipmentView = new EquipmentView();
        this.exporterView = new ExporterView();
        this.sc = new Scanner(System.in);
    }

    public void mainMenu() {
        String choice = "";
        while (!"0".equals(choice)) {
            System.out.println("""
                    \nHello. Enter a digit from the following:\s
                    1. to create equipments at well.\s
                    2. to show equipments count by well.\s
                    3. to export database to xml file.\s
                                        
                    0. to exit the program.
                    """);

            choice = sc.nextLine();
            switch (choice) {

                case "1" -> equipmentView.createMultiplyEquipment();
                case "2" -> wellView.showEquipmentCountByWells();
                case "3" -> exporterView.exportDatabaseToXML();

                case "0" -> {
                    System.out.println("Goodbye.");
                    System.exit(0);
                }
                default -> System.out.println("Please, enter a correct digit.");
            }
        }
    }
}
