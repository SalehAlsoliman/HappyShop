package ci553.happyshop.client.customer;

import java.io.IOException;
import java.sql.SQLException;
import javax.sound.sampled.*;
import java.io.File;

public class CustomerController {
    public CustomerModel cusModel;
    public CustomerView cusView;

    public void doAction(String action) throws SQLException, IOException {
        switch (action) {
            case "Search":
                // Use cusView (not view) to match your class variable
                String idInput = cusView.getIdInput();
                String nameInput = cusView.getNameInput();

                if (!idInput.isEmpty()) {
                    cusModel.search(idInput);
                } else if (!nameInput.isEmpty()) {
                    cusModel.searchByName(nameInput);
                }
                playFeedbackSound();
                break;
            case "Add to Trolley":
                cusModel.addToTrolley();
                playFeedbackSound();
                break;
            case "Cancel":
                cusModel.cancel();
                break;
            case "Check Out":
                cusModel.checkOut();
                playFeedbackSound();
                break;
            case "OK & Close":
                cusModel.closeReceipt();
                break;
        }
    }
    private void playFeedbackSound() {
        try {
            File soundFile = new File("sounds/click.wav");
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(soundFile);
            Clip clip = AudioSystem.getClip();
            clip.open(audioIn);
            clip.start();
        } catch (Exception e) {
            //prevent crash
            System.err.println("Audio error: " + e.getMessage());
        }
    }
}
