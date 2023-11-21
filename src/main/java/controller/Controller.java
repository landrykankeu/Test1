package controller;

import model.ClassGmail;
import view.Card;
import view.View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Controller extends MouseAdapter implements ActionListener {
    private Card view;
    private ClassGmail model;
    public Controller(ClassGmail model, Card view){
        this.view=  view;
        this.model= model;
    }
    public void mouseClicked(MouseEvent e){
        try {
            model.setMessage(view.getId());
            JOptionPane.showMessageDialog(null,"Model à jour!","Alert",JOptionPane.WARNING_MESSAGE);
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    public void actionPerformed(ActionEvent e) {
    }
}
