package controller;

import model.ClassGmail;
import view.Card;
import view.View;

import javax.mail.MessagingException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

import static javax.swing.JOptionPane.ERROR_MESSAGE;


public class  Controller implements ActionListener, MouseListener {
    private View view;
    private ClassGmail model;
    private ArrayList<Card> cards;
    private static  Card previousCard;
    public Controller(ClassGmail model) throws GeneralSecurityException, IOException, InterruptedException, MessagingException {
        cards =  new ArrayList<>();
        this.model= model;
        view =  new View(this,model);
        view.displayMails();
        //this.model.sendEmail("kankeulandry26@gmail.com","Test Envoi d'email'","Loremp ipsum cnsdiub zeibuoaz eziubovazec zeaic");
    }
    public void addCard(Card c){
        cards.add(c);
    }
    public void mouseClicked(MouseEvent e){
        if (previousCard!=null) previousCard.setBackground(Color.WHITE);
        for (Card card : cards) {
            if (card.equals(e.getSource())) {
                try {
                    previousCard = card;
                    model.setMessage(card.getId());
                    card.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
                    card.setBackground(Color.CYAN);
                    if (JOptionPane.showConfirmDialog(null,"Voulez-vous vraiment supprimer ce message ??") == 0) {
                        model.deleteMail(card.getId());
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,ex.getMessage());
                }
                break;
            }
        }
    }
    public void mousePressed(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseEntered(MouseEvent e) {
        for (Card card : cards) {
            if (card.equals(e.getSource())) {
                card.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
                break;
            }
        }
    }
    public void mouseExited(MouseEvent e) {
        for (Card card : cards) {
            if (card.equals(e.getSource())) {
                card.setBorder(BorderFactory.createLineBorder(Color.WHITE,1));
                break;
            }
        }
    }
    public void actionPerformed(ActionEvent e) {

    }
}
