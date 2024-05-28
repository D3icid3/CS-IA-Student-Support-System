package MVC.Controller;

public interface ChangeActivePanelListener {

    void onChangeActivePanel(PanelName panel);


    /**
     * enumeration, collection of constants
     * this will help the card layout to switch between screens
     */

    enum PanelName {
        LOGIN, SUBJECT_LIST;
    }

}
