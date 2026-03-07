import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.File;
import javax.swing.JFileChooser;

public class NewJFrame extends JFrame {

    private JTabbedPane tabbedPane;

    private JMenuBar menuBar;
    private JMenu menuFile;
    private JMenu menuEdit;

    private JMenuItem menuNew;
    private JMenuItem menuSave;
    private JMenuItem menuClose;

    private JMenuItem menuBold;
    private JMenuItem menuItalic;

    private boolean isBold = false;
    private boolean isItalic = false;

    public NewJFrame() {
        initComponents();
    }

    private void initComponents() {

        tabbedPane = new JTabbedPane();

        menuBar = new JMenuBar();
        menuFile = new JMenu("File");
        menuEdit = new JMenu("Edit");

        menuNew = new JMenuItem("New File");
        menuSave = new JMenuItem("Save");
        menuClose = new JMenuItem("Close");

        menuBold = new JMenuItem("Bold");
        menuItalic = new JMenuItem("Italic");

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Simple Note");

    

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JPanel red = new JPanel();
        red.setBackground(Color.RED);
        red.setPreferredSize(new Dimension(15,15));

        JPanel orange = new JPanel();
        orange.setBackground(Color.ORANGE);
        orange.setPreferredSize(new Dimension(15,15));

        JPanel green = new JPanel();
        green.setBackground(Color.GREEN);
        green.setPreferredSize(new Dimension(15,15));

        topPanel.add(red);
        topPanel.add(orange);
        topPanel.add(green);

    
        menuNew.addActionListener(e -> createNewTab());
        menuSave.addActionListener(e -> saveFile());
        menuClose.addActionListener(e -> closeTab());

        menuFile.add(menuNew);
        menuFile.add(menuSave);
        menuFile.add(menuClose);

      

        menuBold.addActionListener(e -> {
            isBold = !isBold;
            updateFont();
        });

        menuItalic.addActionListener(e -> {
            isItalic = !isItalic;
            updateFont();
        });

        menuEdit.add(menuBold);
        menuEdit.add(menuItalic);

        menuBar.add(menuFile);
        menuBar.add(menuEdit);


        JPanel headerPanel = new JPanel(new BorderLayout());

        headerPanel.add(topPanel, BorderLayout.NORTH);
        headerPanel.add(menuBar, BorderLayout.SOUTH);



        setLayout(new BorderLayout());

        add(headerPanel, BorderLayout.NORTH);
        add(tabbedPane, BorderLayout.CENTER);

        createNewTab(); // tab pertama otomatis

        setSize(500,350);
        setLocationRelativeTo(null);
    }

 
    private void createNewTab(){

        JTextArea textArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        tabbedPane.addTab("New File", scrollPane);
        tabbedPane.setSelectedComponent(scrollPane);
    }

    private JTextArea getCurrentTextArea(){

        JScrollPane pane = (JScrollPane) tabbedPane.getSelectedComponent();

        if(pane == null) return null;

        JViewport viewport = pane.getViewport();

        return (JTextArea) viewport.getView();
    }


    private void updateFont(){

        JTextArea textArea = getCurrentTextArea();

        if(textArea == null) return;

        int style = Font.PLAIN;

        if(isBold && isItalic)
            style = Font.BOLD | Font.ITALIC;
        else if(isBold)
            style = Font.BOLD;
        else if(isItalic)
            style = Font.ITALIC;

        Font current = textArea.getFont();

        textArea.setFont(new Font(
                current.getName(),
                style,
                current.getSize()
        ));
    }


    private void saveFile(){

        JTextArea textArea = getCurrentTextArea();

        if(textArea == null) return;

        JFileChooser chooser = new JFileChooser();

        int result = chooser.showSaveDialog(this);

        if(result == JFileChooser.APPROVE_OPTION){

            File file = chooser.getSelectedFile();

            try{
                FileWriter writer = new FileWriter(file);
                writer.write(textArea.getText());
                writer.close();

                JOptionPane.showMessageDialog(this,"File berhasil disimpan");

            }catch(Exception e){
                JOptionPane.showMessageDialog(this,"Gagal menyimpan file");
            }
        }
    }

   
    private void closeTab(){

        int index = tabbedPane.getSelectedIndex();

        if(index != -1)
            tabbedPane.remove(index);
    }


    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new NewJFrame().setVisible(true);
        });

    }
}
