package view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.*;

public class Card extends JPanel{
    private JTextPane subjectLabel;
    private JLabel dateTimeLabel;
    private JLabel senderLabel;
    private JPanel westSide;
    public Card(String sender, String date, String subject){

        this.setLayout(new BorderLayout());

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

        westSide = new JPanel(new SpringLayout());
        westSide.setPreferredSize(new Dimension(20,20));

        this.add(senderLabel,BorderLayout.NORTH);
        this.add(subjectLabel,BorderLayout.CENTER);
        this.add(dateTimeLabel,BorderLayout.EAST);
        this.add(westSide,BorderLayout.WEST);

        this.setVisible(true);
        Border color = BorderFactory.createLineBorder(Color.RED);
        Border round = BorderFactory.createEmptyBorder(10,10,10,10);
        Border border = BorderFactory.createCompoundBorder(color,round);
        this.setBorder(border);
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(10,30));

    }

}