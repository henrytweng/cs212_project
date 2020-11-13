import java.awt.GridLayout;
import javax.swing.*;
import java.util.StringTokenizer;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class CarGUI extends JFrame {
    private static final long serialVersionUID = 1L;
    private JTextArea leftTextArea;
    private JTextArea rightTextArea;
    private SortedCarList soList;
    private UnsortedCarList unList;
    
    public CarGUI () { this ("No title"); }

    public CarGUI (String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(400, 200);
        this.setLocation(100, 100);
        this.createMenu();

        this.setLayout(new GridLayout(1,2));
        leftTextArea = new JTextArea(20, 10);
        rightTextArea = new JTextArea(20, 10);
        this.getContentPane().add(leftTextArea);
        this.getContentPane().add(rightTextArea);
    }

    //Creates the menu bar with File and Edit menus
    private void createMenu() {
        JMenuBar menuBar = new JMenuBar();
        
        JMenu fileMenu = new JMenu("File");
        JMenuItem open = new JMenuItem("Open");
        JMenuItem quit = new JMenuItem("Quit");
        FileMenuHandler fmh = new FileMenuHandler(this);
        open.addActionListener(fmh);
        quit.addActionListener(fmh);
        fileMenu.add(open);
        fileMenu.addSeparator();
        fileMenu.add(quit);

        JMenu editMenu = new JMenu("Edit");
        JMenuItem search = new JMenuItem("Search");
        EditMenuHandler emh = new EditMenuHandler(this);
        search.addActionListener(emh);
        editMenu.add(search);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        this.setJMenuBar(menuBar);
    }

    public void showGui() {
        this.pack();
        this.setVisible(true);
    }

    // Displays all cars with the argument car make. If no cars are of that make, the lists are unchanged and a message dialog notifies the user.
    public void search(String make) {
        try {
            //Do not search if list has not been instantiated.
            if (soList == null) throw new IllegalArgumentException();

            boolean isInList = false;
            CarNode p = soList.first;

            //Searches through the SortedCarList for the first instance of argument make, if it exists.
            while (p.next != null && !isInList) {
                p = p.next;
                if (p.data.getMake().equals(make)) isInList = true;
            }
            if (!isInList) throw new IllegalArgumentException();
            UnsortedCarList newUnList = new UnsortedCarList();
            SortedCarList newSoList = new SortedCarList();

            //Proceed from the first instance of argument make.
            while (p != null) {
                if (p.data.getMake().equals(make)) {
                  newUnList.add(p.data);
                  newSoList.add(p.data);
                }
                p = p.next;
            }
            unList = newUnList;
            soList = newSoList;
            displayList();
        }
        catch (IllegalArgumentException e)
        {
            JOptionPane.showMessageDialog(null, "Sorry, the car make " + make + " could not be found in the displayed list.");
        }
    }

    public void readFile(File file) {
        unList = new UnsortedCarList();
        soList = new SortedCarList();
        //Add input from file to both of our lists using a StringTokenizer
        try {
            Scanner reader = new Scanner(file);
            String line = null;
            while (reader.hasNextLine()) 
            {
              line = reader.nextLine();
              StringTokenizer tok = new StringTokenizer(line, ",");
              if (!(isValidCar(line))) System.out.println("Invalid input: " + line);
              else 
              {
                Car myCar = new Car();
                myCar.setMake(tok.nextToken());
                myCar.setModel(tok.nextToken());
                myCar.setYear(Integer.parseInt(tok.nextToken()));
                myCar.setMileage(Integer.parseInt(tok.nextToken()));
                unList.add(myCar);
                soList.add(myCar);
              }
            }
            reader.close();
            displayList();
        }
        catch(FileNotFoundException e) {
            System.out.println("The input file could not be found or is invalid.");
            e.printStackTrace(); 
        }
    }

    //Displays the car lists from search and readFile methods.
    private void displayList() {
        //Clears the previous input
        leftTextArea.setText(null);
        rightTextArea.setText(null);

        //Check if the list length is greater than number of rows in a TextArea
        if (unList.getLength() > this.leftTextArea.getRows()) this.leftTextArea.setRows(unList.length + 1);
        if (soList.getLength() > this.rightTextArea.getRows()) this.rightTextArea.setRows(soList.length + 1);

        leftTextArea.append("Unsorted Car List: \n\n");
        leftTextArea.append(unList.toString());
        rightTextArea.append("Sorted Car List: \n\n");
        rightTextArea.append(soList.toString());    
    }

    //Returns true if the input line is of valid form for Car object instantiation
    private static boolean isValidCar(String line) {
        StringTokenizer tok = new StringTokenizer(line, ",");
        if (tok.countTokens() != 4) return false;
        //Advance past first two tokens
        tok.nextToken(); tok.nextToken(); 
        String s3 = tok.nextToken(); 
        String s4 = tok.nextToken();
        try {
            Integer.parseInt(s3);
            Integer.parseInt(s4);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
      }
}