import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Card extends JPanel{
    private JPanel cardPanel;
    private JTextPane fewWordLabel;
    private JLabel dateTimeLabel;
    private JLabel senderLabel;
    private JPanel westSide;
    public Card(String sender, String date, String text){
        cardPanel =new JPanel(new BorderLayout());

        senderLabel = new JLabel(sender);
        senderLabel.setHorizontalAlignment(JLabel.CENTER);
        senderLabel.setFont(new Font("Arial Black",Font.BOLD,16));

        dateTimeLabel = new JLabel(date);
        dateTimeLabel.setPreferredSize(new Dimension(60,20));
        dateTimeLabel.setFont(new Font("Arial",Font.PLAIN,16));

        fewWordLabel = new JTextPane();
        fewWordLabel.setMargin(new Insets(3, 25, 3, 25));
        fewWordLabel.setFont(new Font("Arial",Font.ITALIC,14));
        fewWordLabel.setText(text);

        westSide = new JPanel(new SpringLayout());
        westSide.setPreferredSize(new Dimension(75,75));
        cardPanel.add(senderLabel,BorderLayout.NORTH);
        cardPanel.add(fewWordLabel,BorderLayout.EAST);
        cardPanel.add(fewWordLabel,BorderLayout.CENTER);
        cardPanel.add(senderLabel,BorderLayout.WEST);

        cardPanel.setVisible(true);
        Border color = BorderFactory.createLineBorder(Color.RED);
        Border round = BorderFactory.createEmptyBorder(10,10,10,10);
        Border border = BorderFactory.createCompoundBorder(color,round);
        cardPanel.setBorder(border);

    }

}