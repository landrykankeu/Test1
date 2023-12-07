package view;

import com.google.api.services.gmail.model.Message;
import com.google.api.services.gmail.model.MessagePartHeader;
import controller.Controller;
import model.ClassGmail;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.concurrent.Flow;

public class View extends JFrame implements Flow.Subscriber {
    private  static  JPanel mainPanel;
    private  static  JPanel body;
    private static JPanel westSide;
    private  static JPanel estSide;
    private static  JPanel southSide;
    private static JProgressBar progressBar;
    private static JEditorPane editorPane;
    private final ClassGmail model;
    private final Controller controller;
    public View(Controller c,ClassGmail m) throws GeneralSecurityException, IOException, InterruptedException {
        model = m;
        controller = c;
        model.subscribe(this);
        mainPanel =  (JPanel) this.getContentPane();
        mainPanel.setLayout(new BorderLayout());

        // Conctrution de la structure de la vue
        setNorth();
        setSouth();
        setEast();
        setWest();
        setBody();

        this.setVisible(true);
        this.setSize( 800, 700 );
        this.setLocationRelativeTo( null );
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void displayMails() throws GeneralSecurityException, IOException, InterruptedException {
        // Onglet du corps du main panel
        this.setCursor(Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
        JTabbedPane tab =  new JTabbedPane();
        JPanel focusedPanel =  new JPanel(new GridLayout(0,1));
        // Chargement des messages depuis l'API avec jauge d'évolution
        java.util.List<Message> messages = model.getMessages();
        progressBar.setMaximum(messages.size());
        progressBar.setMinimum(0);
        JRadioButton  radioButtons=  new JRadioButton();
        int i = 1;
        for (Message message :messages){
            progressBar.setValue(i);

            // Etat Wait du curseur
            MessagePartHeader sender = message.getPayload().getHeaders().stream().filter(messagePartHeader -> messagePartHeader.getName().equals("From")).findFirst().orElse(null);
            MessagePartHeader date = message.getPayload().getHeaders().stream().filter(messagePartHeader -> messagePartHeader.getName().equals("Date")).findFirst().orElse(null);
            MessagePartHeader text = message.getPayload().getHeaders().stream().filter(messagePartHeader -> messagePartHeader.getName().equals("Subject")).findFirst().orElse(null);
            String  id  = message.getId();
            Card cardMail =  new Card(sender.getValue(),date.getValue(),text.getValue(),id);
            i++;
            controller.addCard(cardMail);
            cardMail.addMouseListener(controller);
            focusedPanel.add(cardMail);
        }
        tab.add("Focused",new JScrollPane(focusedPanel));
        this.setCursor(Cursor.getDefaultCursor());
        progressBar.setVisible(false);
        JPanel otherPanel =  new JPanel();
        tab.add("Other",otherPanel);
        body.add(tab,BorderLayout.CENTER);
    }
    private static void  setNorth(){
        // La barre de menu
        JMenuBar oneMenuBar =  new JMenuBar();
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

        JMenu menuView = new JMenu("view.View");
        JMenu menuHelps = new JMenu("Help");
        oneMenuBar.add(btnHam);
        oneMenuBar.add(menuHome);
        oneMenuBar.add(menuView);
        oneMenuBar.add(menuHelps);

        mainPanel.add(oneMenuBar,BorderLayout.NORTH);
    }
    private static  void setSouth(){
        southSide =  new JPanel(new FlowLayout());
        southSide.setBorder( BorderFactory.createMatteBorder(3,0,0,0,Color.GRAY));
        progressBar =  new JProgressBar();
        progressBar.setStringPainted(true);
        southSide.add(progressBar);
        mainPanel.add(southSide,BorderLayout.SOUTH);
    }
    private static void setWest(){

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

    }
    private static void setEast() {
        estSide = new JPanel( new SpringLayout());
        editorPane = new JEditorPane();
        editorPane.setContentType("text/html");
        editorPane.setPreferredSize(new Dimension(324,600));
        Border border =  BorderFactory.createMatteBorder(0,3,0,0,Color.GRAY);
        estSide.setBorder(border);
        estSide.setPreferredSize(new Dimension(324,600));
        estSide.add(new JScrollPane(editorPane));
        mainPanel.add(estSide,BorderLayout.EAST);
    }
    private static void setBody(){
        body =  new JPanel(new BorderLayout());
        mainPanel.add(body,BorderLayout.CENTER);
    }
    public void onSubscribe(Flow.Subscription subscription) {
    }
    public void onNext(Object item) {}
    public void onError(Throwable throwable) {}
    public void onComplete(){
        try {
            editorPane.setText(model.getFullMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}


