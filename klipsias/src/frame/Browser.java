package frame;

import javax.swing.*;

import klipsias.KlipsiasService;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class Browser extends JFrame {
    private KlipsiasService klipsiasService = new KlipsiasService();
    private String userId;
    private ArrayList<String> accessibleRooms;
    private Scanner scanner = new Scanner(System.in);
    private boolean english = false;
    private userHelp userHelp;

    String user = "Benutzer:";
    String password = "Passwort:";
    String loginFailed = "Login fehlgeschlagen";
    String loginSucces = "Login erfolgreich. Du wirst zur Startseite weitergeleitet";
    String dashboad = "Willkommen bei deinem persönlichem Dashboad";
    String chooseCourse = "In welchen Kurs Raum möchtest du?";
    String roomNoElements = " hat keine Elemente";
    String inputMistake = "Fehlerhafte Eingabe";
    String noDocument = "Diese Dokument wurde nicht gefunden!";
    String chooseFile = "Wähle eine Datei:";
    String writeContent = "Bitte Inhalt angeben:";
    String toBeImplemented = "Die Einstellungen müssen noch programmiert werden.";
    String logoutMessage = "Abmeldung erfolgreich!";
    String uploadSuccesful = "Dokument erfolgreich hochgeladen";
    String uploadFailed = "Dokument Upload fehlgeschlagen. Bitte überprüfe deine Datei!";
    String uploadMaterial = "Um ein Material hochzuladen bitte folgendes schreiben: 'uploadFile'";
    String rektoratDashboad = "Willkommen im Rektorat Bereich!";
    String rektoratOptionen = "Um einen neuen Benutzer anzulegen bitte folgendes schreiben: 'newUser'";
    String newUserUsername = "Der Benutzername des neuen Benutzers:";
    String newUserPassword = "Das Passwort des neuen Benutzers:";

    private Browser() {
        startKlipsias();
        login();
        if (userId.equals("00001")) {
            loadRektorat();
        } else {
        loadStartseite();
        userNavigates();
        }
    }

    private void startKlipsias() {
        setTitle("Kilpsias");
        setSize(800, 800);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JLabel placeholderLabel = new JLabel(
                "Dies ist ein Platzhalter! Der momentane Code wird über die Konsole gesteuert.");
        placeholderLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        placeholderLabel.setHorizontalAlignment(JLabel.CENTER);
        add(placeholderLabel, BorderLayout.CENTER);
        try {
            BufferedImage logoImage = ImageIO.read(new File("swt-group-2B/klipsias/images/logoUniKoeln.png"));
            setIconImage(logoImage);
            JLabel logoLabel = new JLabel(new ImageIcon(logoImage));
            add(logoLabel, BorderLayout.NORTH);
        } catch (IOException e) {
            e.printStackTrace();
        }
        setVisible(true);
    }

    private void login() {

        System.out.println(user);
        String user = scanner.nextLine();
        if (checkUserInput(user)) {
            processUserInput(user);
            login();
        } else {
            System.out.println(password);
            String password = scanner.nextLine();
            if (checkUserInput(password)) {
                processUserInput(password);
                login();
            } else {
                userId = klipsiasService.login(user, password);

                if (userId == "00000") {
                    System.out.println(loginFailed);
                    login();
                } else {
                    System.out.println(loginSucces);
                }
            }
        }
    }

    private void loadStartseite() {
        System.out.println(dashboad);
        accessibleRooms = klipsiasService.loadStartseite(userId);
        System.out.println(chooseCourse);
        for (String room : accessibleRooms) {
            System.out.println(room);
        }
    }

    private void userNavigates() {
        String actualRoom = scanner.nextLine();
        if (checkUserInput(actualRoom)) {
            processUserInput(actualRoom);
            loadStartseite();
            userNavigates();
        } else {
            ArrayList<String> roomInventory = klipsiasService.openRoom(actualRoom);
            if (roomInventory.size() == 0) {
                System.out.println(actualRoom + roomNoElements);
                System.out.println(uploadMaterial);
                String nextAction = scanner.nextLine();
                if (checkUserInput(nextAction)) {
                    processUserInput(nextAction);
                    loadStartseite();
                    userNavigates();
                } else {
                    if (nextAction.equals("uploadFile")) {
                        addMaterial(actualRoom);
                    } else {
                        loadStartseite();
                        userNavigates();
                    }
                }
            } else if (roomInventory.get(0).equals("No room")) {
                System.out.println(inputMistake);
                loadStartseite();
                userNavigates();
            } else {
                System.out.println(actualRoom + ":");
                for (String element : roomInventory) {
                    System.out.println(element);
                }
                String nextAction = scanner.nextLine();
                if (checkUserInput(nextAction)) {
                    processUserInput(nextAction);
                    loadStartseite();
                    userNavigates();
                } else {
                    if (nextAction.equals("uploadFile")) {
                        addMaterial(actualRoom);
                        loadStartseite();
                        userNavigates();
                    } else {
                        String content = klipsiasService.readMaterial(actualRoom, nextAction);
                        if (content.equals("No Material")) {
                            System.out.println(noDocument);
                            loadStartseite();
                            userNavigates();
                        } else {
                            System.out.println(content);
                            loadStartseite();
                            userNavigates();
                        }
                    }
                }
            }
        }

    }

    private void addMaterial(String materialRoom) {
        System.out.println(chooseFile);
        String materialName = scanner.nextLine();
        if (checkUserInput(materialName)) {
            processUserInput(materialName);
            addMaterial(materialRoom);
        } else {
            System.out.println(writeContent);
            String materialContent = scanner.nextLine();
            if (checkUserInput(materialContent)) {
                processUserInput(materialContent);
                addMaterial(materialRoom);
            } else {
                if (klipsiasService.addMaterial(materialRoom, materialName, materialContent)) {
                    System.out.println(uploadSuccesful);
                } else {
                    System.out.println(uploadFailed);
                }
            }
        }
    }

    private void loadRektorat() {
        System.out.println(rektoratDashboad);
        System.out.println(rektoratOptionen);
        String userInput = scanner.nextLine();
        if (checkUserInput(userInput)) {
            processUserInput(userInput);
            loadRektorat();
        } else {
            System.out.println(newUserUsername);
            String userName = scanner.nextLine();
            System.out.println(newUserPassword);
            String userPassword = scanner.nextLine();
            klipsiasService.createNewUser(userName, userPassword);
            loadRektorat();
        }
    }

    private void changeLanguage() {
        if (!english) {
            user = "Benutzer:";
            password = "Passwort:";
            loginFailed = "Login fehlgeschlagen";
            loginSucces = "Login erfolgreich. Du wirst zur Startseite weitergeleitet";
            dashboad = "Willkommen bei deinem persönlichem Dashboad";
            chooseCourse = "In welchen Kurs Raum möchtest du?";
            roomNoElements = " hat keine Elemente";
            inputMistake = "Fehlerhafte Eingabe";
            noDocument = "Diese Dokument wurde nicht gefunden!";
            chooseFile = "Wähle eine Datei:";
            writeContent = "Bitte Inhalt angeben:";
            toBeImplemented = "Die Einstellungen müssen noch programmiert werden.";
            logoutMessage = "Abmeldung erfolgreich!";
            uploadSuccesful = "Dokument erfolgreich hochgeladen";
            uploadFailed = "Dokument Upload fehlgeschlagen. Bitte überprüfe deine Datei!";
            uploadMaterial = "Um ein Material hochzuladen bitte folgendes schreiben: 'uploadFile'";
            rektoratDashboad = "Willkommen im Rektorat Bereich!";
            rektoratOptionen = "Um einen neuen Benutzer anzulegen bitte folgendes schreiben: 'newUser'";
            newUserUsername = "Der Benutzername des neuen Benutzers:";
            newUserPassword = "Das Passwort des neuen Benutzers:";
        } else if (english) {
            user = "User:";
            password = "Password:";
            loginFailed = "Login failed";
            loginSucces = "Login succesful";
            dashboad = "Welcome to your dashboard";
            chooseCourse = "Which course you would like to open?";
            roomNoElements = " does not have any elements";
            inputMistake = "Wrong Input";
            noDocument = "This document was not found!";
            chooseFile = "Choose a file:";
            writeContent = "Enter the files content:";
            toBeImplemented = "The settings are still under constructions.";
            logoutMessage = "Logout succesful!";
            uploadSuccesful = "Document Upload succesful!";
            uploadFailed = "Document Upload failed. Please check your file!";
            uploadMaterial = "To upload a material please type: 'uploadFile'";
            rektoratDashboad = "Welcome to the rectorate dashboard!";
            rektoratOptionen = "To create a new user please type the following: 'newUser'";
            newUserUsername = "The username of the new user:";
            newUserPassword = "The password of the new user:";
        }
    }

    private void switchLanguage(String language) {
        if (language.equals("eng")) {
            english = true;
        } else {
            english = false;
        }
        changeLanguage();
    }

    private void printHelp() {
        if (english) {
            System.out.println(
                    "Help: \nThe user see all his options to acces to in the console. To do a action the user need to type it without typing mistakes.\nBesides the listed Options the user can always type the following:");
            System.out.println(
                    "'eng' to switch to english\n 'ger' to switch to german\n 'help' to open the help menu\n 'settings' to open the settings\n 'logout' to close the program and logout");
        } else {
            System.out.println(
                    "Hilfe: \nDer Benutzer sieht all seine Optionen in der Konsole. Um eine Aktion auszuführen muss es in der Konsole eins zu eins abgetippt werden.\nDaneben hat der Benutzer immer die Möglichkeit die folgenden Optionen zu nehmen:");
            System.out.println(
                    "'eng' klipsias auf english stellen\n 'ger' auf deutsch stellen\n 'help' das Hilfemenü zu öffnen\n 'settings' die Einstellungen zu öffnen\n 'logout' das Programm zu schließen und sich aus Klipsias auszuloggen");
        }
    }

    private void logout() {
        System.out.println(logoutMessage);
        scanner.close();
        System.exit(0);
    }

    private void processUserInput(String input) {
        String userInput = input.toUpperCase();
        userHelp requiredHelp = null;
        for (userHelp userHelpEnum : frame.userHelp.values()) {
            if (userHelpEnum.name().equals(userInput)) {
                requiredHelp = userHelpEnum;
                break;
            }
        }
        switch (requiredHelp) {
            case ENG:
                switchLanguage("eng");
                break;
            case GER:
                switchLanguage("ger");
                break;
            case HELP:
                printHelp();
                break;
            case SETTINGS:
                System.out.println(toBeImplemented);
                break;
            case LOGOUT:
                logout();
                break;
            default:
                System.out.println("Error: Input does not match any enums although it should");
                break;
        }
    }

    private boolean checkUserInput(String input) {
        for (userHelp userHelpEnum : frame.userHelp.values()) {
            if (userHelpEnum.name().equals(input.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Browser());
    }
}

enum userHelp {
    ENG,
    GER,
    HELP,
    SETTINGS,
    LOGOUT,
}
