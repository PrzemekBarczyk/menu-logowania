package main.loginmenu;

import main.posloginmenu.PostloginMenuController;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LoginMenuController {

    private LoginMenuView theView;
    private LoginMenuModel theModel;

    private EventHandler<ActionEvent> actionEventHandler;
    private EventHandler<KeyEvent> keyEventHandler;

    public LoginMenuController() {

        this.theView = new LoginMenuView();
        this.theModel = new LoginMenuModel();

        this.createEventHandlers();
        this.theView.addEventHandlers(actionEventHandler, keyEventHandler);
    }

    private void createEventHandlers() {

        actionEventHandler = event -> {

            Object evt = event.getSource();

            if (evt == theView.getCheckBox()) { // zaznaczono/odznaczono checkBox
                theModel.handleCheckBox(theView.getCheckBox().isSelected());
            }
            else { // naciśnięto loginButton
                theModel.handleLoginButton(theView.getUsernameField().getText(), theView.getPasswordField().getText());
                checkLoginData();
            }
        };

        keyEventHandler = event -> {

            if (event.getCode() == KeyCode.ENTER) { // naciśnięto ENTER w polu loginu lub hasła

                if (event.getSource() == theView.getUsernameField()) { // pole loginu
                    theView.getPasswordField().requestFocus();
                }
                else { // pole hasła
                    theModel.handleLoginButton(theView.getUsernameField().getText(), theView.getPasswordField().getText());
                    checkLoginData();
                }
            }
        };
    }

    private void checkLoginData() {

        if (theModel.getIsLoginCorrect()) { // sprawdza czy dane logowania są poprawne
            new PostloginMenuController("Success Login");
        }
        else {
            new PostloginMenuController("Fail Login");
        }
    }

    public Scene getScene() {
        return theView.getScene();
    }
}
