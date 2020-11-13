import javax.swing.JFileChooser;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FileMenuHandler implements ActionListener {
    private CarGUI gui;
    public FileMenuHandler(CarGUI gui) {
        this.gui = gui;
    }
    
    public void actionPerformed(ActionEvent event) {
        String menuName = event.getActionCommand();
        System.out.println("User option: " + menuName);
        if (menuName.equals("Open")) {
            JFileChooser fc = new JFileChooser();
            int status = fc.showOpenDialog(null);
            if (status == JFileChooser.APPROVE_OPTION) {
                this.gui.readFile(fc.getSelectedFile());
            }
            else if (status == JFileChooser.CANCEL_OPTION) {
                System.out.println("User cancelled.");
            }
        }
        else if (menuName.equals("Quit")) {
            System.exit(0);
        }

    } 
}