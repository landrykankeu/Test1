package controller;

<<<<<<< Updated upstream
import view.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Controller extends MouseAdapter implements ActionListener {
    public Controller(){

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getSource());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.getSource());
=======
import model.CallAPI;
import model.ClassGmail;
import view.Card;
import view.EmailWindow;

import javax.swing.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.logging.Logger;

public class Controller extends MouseAdapter implements ActionListener {
    private  Card view;
    private  ClassGmail model;
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

>>>>>>> Stashed changes
    }
}
