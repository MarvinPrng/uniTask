package ilias;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class IliasService {
    KursRaumService kursRaumService = new KursRaumService();
    ArrayList<Raum> allRooms = new ArrayList<Raum>();

    public IliasService() {
        Raum raum1 = new Raum("Software Technik");
        Raum raum2 = new Raum("SWT Organisation");
        raum1.addUsers("12345", "12221");
        raum2.addUsers("12221");
        allRooms.add(raum1);
        allRooms.add(raum2);
    }

    public class KursRaumService {
        MaterialSharingService materialSharingService = new MaterialSharingService();
        ForumService forumService = new ForumService();

        private KursRaumService() {

        }

        protected ArrayList<String> openRoom(Raum raum) {
            ArrayList<String> raumObjekte = new ArrayList<String>();
            raumObjekte.addAll(materialSharingService.loadMaterialNames(raum.name));
            raumObjekte.addAll(forumService.loadForums());
            return raumObjekte;
        }

        protected String readMaterial(Raum room, String material) {
            return materialSharingService.readMaterial(room.name, material);
        }

        protected void addMaterial(Raum room, String material, String content) {
            materialSharingService.uploadMaterial(room.name, material, content);
        }

        public class MaterialSharingService {
            ArrayList<Material> sharedMaterial = new ArrayList<Material>();

            private MaterialSharingService() {

            }
            private ArrayList<String> loadMaterialNames(String roomName) {
                loadMaterial(roomName);
                ArrayList<String> sharedMaterialName = new ArrayList<String>();
                for (Material material : sharedMaterial) {
                    sharedMaterialName.add(material.name);
                }
                return sharedMaterialName;
            }

            private void loadMaterial(String roomName) {
                sharedMaterial.clear();
                Map<String, ArrayList<String[]>> roomMaterial;
                roomMaterial = loadRoomMaterial();
                ArrayList<String[]> elements = roomMaterial.get(roomName);
                if (elements != null) {
                    for (String[] element : elements) {
                        Material material = new Material();
                        material.addMaterial(element[0], element[1]);
                        sharedMaterial.add(material);
                    }
                }
            }

            private Map<String, ArrayList<String[]>> loadRoomMaterial() {
                Map<String, ArrayList<String[]>> roomDataMap = new HashMap<>();
                try (BufferedReader reader = new BufferedReader(
                        new FileReader("swt-group-2B/klipsias/src/ilias/database/material.txt"))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        String[] parts = line.split("\\|");
                        String room = parts[0].trim();
                        String[] elements = parts[1].split("¡");
                        roomDataMap.computeIfAbsent(room, k -> new ArrayList<>()).add(elements);
                    }
                } catch (IOException | ArrayIndexOutOfBoundsException e) {
                    e.printStackTrace();
                }
                return roomDataMap;
            }

            private String readMaterial (String roomName, String material) {
                loadMaterial(roomName);
                String content = "";
                for (Material file : sharedMaterial) {
                    if (file.name.equals(material)) {
                        content = file.content;
                    }
                }
                return content;
            }

            private void uploadMaterial (String roomName, String material, String content) {
                Map<String, ArrayList<String[]>> roomMaterial;
                roomMaterial = loadRoomMaterial();
                roomMaterial.computeIfAbsent(roomName, k -> new ArrayList<>()).add(new String []{material, content});
                addMaterial(roomName, material, content);
            }

            private void addMaterial(String roomName, String material, String content) {
                try (BufferedWriter writer = new BufferedWriter(new FileWriter("swt-group-2B/klipsias/src/ilias/database/material.txt", true))) {
                    writer.newLine();
                    writer.write(roomName + "|" + material + "¡" + content);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        public class Material {
            String name;
            String content;

            private Material() {

            }

            protected void addMaterial(String name, String content) {
                this.name = name;
                this.content = content;
            }
        }

    }

    public class ForumService {
        ArrayList<String> allForums = new ArrayList<String>();

        private ForumService() {

        }

        private ArrayList<String> loadForums() {
            return allForums;
        }

    }

    public ArrayList<String> accessibleRooms(String userId) {
        ArrayList<String> rooms = new ArrayList<String>();
        for (Raum raum : allRooms) {
            for (String user : raum.allowedUsers) {
                if (user.equals(userId)) {
                    rooms.add(raum.name);
                }
            }
        }
        return rooms;
    }

    public ArrayList<String> openRoom(String room) {

        for (Raum raum : allRooms) {
            if (raum.name.equals(room)) {
                return kursRaumService.openRoom(raum);
            }
        }
        return new ArrayList<String>(Arrays.asList("No room"));

    }

    public String readMaterial(String room, String material) {
        for (Raum raum : allRooms) {
            if (raum.name.equals(room)) {
                return kursRaumService.readMaterial(raum, material);
        }
    }
    return "No Material";
}

    public void addMaterial (String room, String material, String content) {
        for (Raum raum : allRooms) {
            if (raum.name.equals(room)) {
                kursRaumService.addMaterial(raum, material, content);
        }
    }      
    }

    protected class Raum {
        String name;
        ArrayList<String> allowedUsers = new ArrayList<String>();

        protected Raum(String name) {
            this.name = name;
        }

        protected void addUsers(String... user) {
            for (String newUser : user) {
                allowedUsers.add(newUser);
            }
        }

    }
}