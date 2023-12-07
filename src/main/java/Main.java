import controller.Controller;
import model.ClassGmail;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Main {
    public static void main(String[] args) throws Exception{
      UIManager.setLookAndFeel( new NimbusLookAndFeel());
      new Controller(new ClassGmail());
    }
}