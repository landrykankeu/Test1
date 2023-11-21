package view;

import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePartHeader;
import controller.Controller;
import model.ClassGmail;
<<<<<<< Updated upstream:src/main/java/EmailWindow.java
import view.Card;
=======
import org.checkerframework.checker.units.qual.C;
>>>>>>> Stashed changes:src/main/java/view/EmailWindow.java

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;
import java.util.concurrent.Flow;


public class EmailWindow extends JFrame{
    private JMenuBar oneMenuBar;
    private JPanel body;
    private JPanel westSide;
    private  JPanel estSide;
    private JPanel southSide;
    private JProgressBar progressBar;
    private ClassGmail model;
    public EmailWindow() throws GeneralSecurityException, InterruptedException, IOException {
    // La barre de menu

        oneMenuBar =  new JMenuBar();
        model = new ClassGmail();
        JPanel mainPanel =  (JPanel) this.getContentPane();
        this.setVisible(true);
        mainPanel.setLayout(new BorderLayout());

        JButton btnHam = new JButton("");
        // Le menu Home
        JMenu menuHome = new JMenu("Home");

            JMenu newMailMenu  = new JMenu("New Mail");
            newMailMenu.add("Mail");
            newMailMenu.add("Event");
            newMailMenu.add("Document");
            newMailMenu.add("SpreadSheet");
            newMailMenu.add("Presentation");

        JMenu deleteMenu  = new JMenu("Empty others");
        deleteMenu.add("Empty Others");
        deleteMenu.add("Ignore");

        JButton btn1 = new JButton("Archive");
        JButton btn2 = new JButton("Report");
        JButton btn3 = new JButton("Move to");
        JButton btn4 = new JButton("Reply all");
        JButton btn5 = new JButton("Mark as all read");
        JButton btn6 = new JButton("...");

        menuHome.add(newMailMenu);
        menuHome.add(deleteMenu);
        menuHome.add(btn1);
        menuHome.add(btn2);
        menuHome.add(btn3);
        menuHome.add(btn4);
        menuHome.add(btn5);
        menuHome.add(btn6);

<<<<<<< Updated upstream:src/main/java/EmailWindow.java
        JMenu menuView = new JMenu("view.View");
=======
        JMenu menuView = new JMenu("view");
>>>>>>> Stashed changes:src/main/java/view/EmailWindow.java
        JMenu menuHelps = new JMenu("Help");
        oneMenuBar.add(btnHam);
        oneMenuBar.add(menuHome);
        oneMenuBar.add(menuView);
        oneMenuBar.add(menuHelps);

        mainPanel.add(oneMenuBar,BorderLayout.NORTH);

        // la partie Ouest du panel main

        westSide = new JPanel(new CardLayout());
        DefaultMutableTreeNode explorerTree  = new DefaultMutableTreeNode("");
            DefaultMutableTreeNode favoritesNode  = new DefaultMutableTreeNode("Favorites");
                favoritesNode.add(new DefaultMutableTreeNode("inbox"));
                favoritesNode.add(new DefaultMutableTreeNode("Sent Items"));
                favoritesNode.add(new DefaultMutableTreeNode("Drafts"));
            explorerTree.add(favoritesNode);
                DefaultMutableTreeNode accountNode  = new DefaultMutableTreeNode("Account");
                accountNode.add(new DefaultMutableTreeNode("inbox"));
                accountNode.add(new DefaultMutableTreeNode("Sent Items"));
                accountNode.add(new DefaultMutableTreeNode("Drafts"));
                accountNode.add(new DefaultMutableTreeNode("Junks mails"));
                accountNode.add(new DefaultMutableTreeNode("Delete items"));
                accountNode.add(new DefaultMutableTreeNode("Archive"));
                accountNode.add(new DefaultMutableTreeNode("Note_0"));
            explorerTree.add(accountNode);

        DefaultTreeModel treeModel = new DefaultTreeModel(explorerTree);

        JTree gTree =  new JTree(treeModel);
        westSide.add(gTree);
        mainPanel.add(westSide,BorderLayout.WEST);

        // la corps de l'application
        body =  new JPanel(new BorderLayout());
        mainPanel.add(body,BorderLayout.CENTER);

        // la partie Est du main panel
        estSide = new JPanel(new BorderLayout());
        Border border =  BorderFactory.createMatteBorder(0,3,0,0,Color.GRAY);
        estSide.setBorder(border);
        estSide.setPreferredSize(new Dimension(324,600));
        estSide.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                try {
                    showFullMail();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        mainPanel.add(estSide,BorderLayout.EAST);

        // la barre de message
        southSide =  new JPanel(new FlowLayout());
        southSide.setBorder( BorderFactory.createMatteBorder(3,0,0,0,Color.GRAY));
        progressBar =  new JProgressBar();
        progressBar.setStringPainted(true);
        southSide.add(progressBar);
        mainPanel.add(southSide,BorderLayout.SOUTH);
        loadMails();

        this.setSize( 800, 700 );
        this.setLocationRelativeTo( null );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void loadMails() throws GeneralSecurityException, InterruptedException {
        // Onglet du corps du main panel
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        JTabbedPane tab =  new JTabbedPane();
        JPanel focusedPanel =  new JPanel(new GridLayout(0,1));
        JScrollPane scrollPanel = new JScrollPane(focusedPanel);
        scrollPanel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        tab.add("Focused",scrollPanel);

        // Chargement des messages depuis l'API avec jauge d'évolution
         try{
             List<Message> messages  = model.getMessages();
             progressBar.setMaximum(messages.size());
             progressBar.setMinimum(0);
             int i = 1;
             for (Message message : messages){
                 progressBar.setValue(i);

                 // Etat Wait du curseur
                 String id = message.getId();
                 MessagePartHeader sender = message.getPayload().getHeaders().stream().filter(messagePartHeader -> messagePartHeader.getName().equals("From")).findFirst().orElse(null);
                 MessagePartHeader date = message.getPayload().getHeaders().stream().filter(messagePartHeader -> messagePartHeader.getName().equals("Date")).findFirst().orElse(null);
                 MessagePartHeader text = message.getPayload().getHeaders().stream().filter(messagePartHeader -> messagePartHeader.getName().equals("Subject")).findFirst().orElse(null);
                 Card cardMail =  new Card(sender.getValue(),date.getValue(),text.getValue(),id);
                 i++;
                 cardMail.addMouseListener(new MouseAdapter(){
                     public void mouseEntered(MouseEvent e) {
                         cardMail.setBackground(Color.GRAY);
                     }
                     public void mouseExited(MouseEvent e) {
                         cardMail.setBackground(Color.lightGray
                         );
                     }


                     public void mouseClicked(MouseEvent e) {
                         cardMail.addMouseListener(new Controller(model,cardMail));
                     }
                 });

                 focusedPanel.add(cardMail);
             }
         }catch (NullPointerException | IOException e){
             System.out.println(e.getMessage());
         }finally {
             this.setCursor(Cursor.getDefaultCursor());
         }

        progressBar.setVisible(false);
        JPanel otherPanel =  new JPanel();
        tab.add("Other",otherPanel);
        body.add(tab,BorderLayout.CENTER);
    }
    public void showFullMail() throws IOException {
        JEditorPane editorPane = new JEditorPane();
        String content = model.getFullMessage();
        if (content != null){
            editorPane.setContentType("text/html");
            editorPane.setText(content);
            editorPane.setPreferredSize(new Dimension(324,600));

        }else editorPane.setVisible(false);

        estSide.add(new JScrollPane(editorPane),BorderLayout.CENTER);
    }
}
