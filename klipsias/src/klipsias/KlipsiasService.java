package klipsias;

import java.util.ArrayList;
import java.util.regex.Pattern;

import account.AccountService;
import ilias.IliasService;

public class KlipsiasService {
    private AccountService accountService = new AccountService();
    private IliasService iliasService = new IliasService();
    String[] forbiddenSymbols = {"|", "¡"};
    String materialPattern = "^[^.]+\\.(pdf|mp4|png|zip|jpeg|txt|jpg|docx|xlsx|pptx)$";
    
    public KlipsiasService() {

    }

    public String login(String username, String password) {
        return accountService.verifyLogin(username, password);
    }

    public ArrayList<String> loadStartseite(String userId) {
        return iliasService.accessibleRooms(userId);
    }

    public ArrayList<String> openRoom(String room) {
        return iliasService.openRoom(room);
    }

    public String readMaterial(String room, String material) {
        return iliasService.readMaterial(room, material);
    }

    public boolean addMaterial(String room, String material, String content) {
        if (securityCheck(material, content)) {
            iliasService.addMaterial(room, material, content);
            return true;
        }
        return false;
    }

    public void createNewUser(String userName, String userPassword) {
        accountService.createNewUser(userName, userPassword);
    }

    private boolean securityCheck(String material, String content) {
        if (!Pattern.matches(materialPattern, material)) {
            return false;
        }
        for (String symbol : forbiddenSymbols) {
            if (material.contains(symbol) || content.contains(symbol)) {
                return false;
            }
        }
        return true;
    }
}

