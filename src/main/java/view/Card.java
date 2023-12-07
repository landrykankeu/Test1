package view;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Card extends JPanel {
    private JTextPane subjectLabel;
    private JLabel dateTimeLabel;
    private JLabel senderLabel;
    private JPanel westSide;
    private JPanel southSide;
    private JButton deleteBTN;
    private  String id;
    public Card(String sender, String date, String subject, String id){

        this.setLayout(new BorderLayout());
        this.id = id;

        senderLabel = new JLabel(sender);
        senderLabel.setHorizontalAlignment(JLabel.LEFT);
        senderLabel.setFont(new Font("Arial Black",Font.BOLD,15));


        dateTimeLabel = new JLabel(date);
        dateTimeLabel.setPreferredSize(new Dimension(80,50));
        dateTimeLabel.setFont(new Font("Arial",Font.PLAIN,14));

        subjectLabel = new JTextPane();
        subjectLabel.setMargin(new Insets(3, 25, 3, 25));
        subjectLabel.setFont(new Font("Arial",Font.ITALIC,14));
        subjectLabel.setText(subject);
        subjectLabel.setEnabled(false);

        westSide = new JPanel(new SpringLayout());
        westSide.setEnabled(false);
        westSide.setPreferredSize(new Dimension(20,20));

        southSide = new JPanel(new FlowLayout());
        deleteBTN = new JButton("Delete");
        southSide.add(deleteBTN);
        southSide.setVisible(false);


        this.add(senderLabel,BorderLayout.NORTH);
        this.add(subjectLabel,BorderLayout.CENTER);
        this.add(dateTimeLabel,BorderLayout.EAST);
        this.add(westSide,BorderLayout.WEST);
        this.add(southSide,BorderLayout.SOUTH);

        this.setVisible(true);
        Border round = BorderFactory.createEmptyBorder(10,25,0,0);
        this.setBorder(round);
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(10,30));

    }
    public String getId(){
         return id;
    }
    public JButton getDeleteBTN(){
        return deleteBTN;
    }
}