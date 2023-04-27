package org.example.view;

import org.example.controller.ExporterController;
import org.example.dto.DBInfo;
import org.example.util.XMLCreator;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class ExporterView {
    private final Scanner scanner;
    private final ExporterController exporterController;

    public ExporterView() {
        this.scanner = new Scanner(System.in);
        this.exporterController = new ExporterController();
    }

    public void exportDatabaseToXML() {
        System.out.println("If file exists, it will be overwritten! Please enter file name:");
        String outputFileName = scanner.nextLine() + ".xml";

        DBInfo dbInfo = exporterController.getAllWellsWithEquipments();
        String dbInfoAsXML = XMLCreator.createXMLFromDBInfo(dbInfo);

        saveToFile(outputFileName, dbInfoAsXML);
    }

    private void saveToFile(String outputFileName, String dbInfoAsXML) {
        try (PrintWriter out = new PrintWriter(outputFileName)) {
            out.println(dbInfoAsXML);
            System.out.printf("Database info successfully saved to '%s'.%n%n", outputFileName);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
