import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class EditMenuHandler implements ActionListener {
    private CarGUI gui;
    public EditMenuHandler(CarGUI gui) {
        this.gui = gui;
    }
    
    public void actionPerformed(ActionEvent event) {
        String menuName = event.getActionCommand();
        System.out.println("User option: " + menuName);
        if (menuName.equals("Search")) {
            String make = JOptionPane.showInputDialog("Enter a car make.");
            this.gui.search(make);
        } 
    }
}