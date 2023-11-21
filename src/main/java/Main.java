<<<<<<< Updated upstream
import view.View;
=======
import view.EmailWindow;
>>>>>>> Stashed changes

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Main {
    public static void main(String[] args) throws Exception{
<<<<<<< Updated upstream
        UIManager.setLookAndFeel( new NimbusLookAndFeel());
        new View();
     // EmailWindow oneEmail = new EmailWindow();
     //oneEmail.loadMail();
=======
      UIManager.setLookAndFeel( new NimbusLookAndFeel());
      new EmailWindow();
>>>>>>> Stashed changes
    }
}