import javax.swing.*;
import java.io.FileWriter;
import java.io.File;
import javax.swing.JFileChooser;

public class NewJFrame extends javax.swing.JFrame {

    private javax.swing.JTextArea textArea;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenu menuFile;
    private javax.swing.JMenu menuEdit;
    private javax.swing.JMenuItem menuSave;

    public NewJFrame() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        textArea = new javax.swing.JTextArea();
        JScrollPane scrollPane = new JScrollPane(textArea);

        menuBar = new javax.swing.JMenuBar();
        menuFile = new javax.swing.JMenu();
        menuEdit = new javax.swing.JMenu();
        menuSave = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NewJFrame");

        menuFile.setText("File");

        menuSave.setText("Save");
        menuSave.addActionListener(evt -> saveFile());

        menuFile.add(menuSave);

        menuEdit.setText("Edit");

        menuBar.add(menuFile);
        menuBar.add(menuEdit);

        setJMenuBar(menuBar);

        add(scrollPane);

        setSize(400,300);
        setLocationRelativeTo(null);
    }

    private void saveFile() {

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

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(() -> {
            new NewJFrame().setVisible(true);
        });

    }
}