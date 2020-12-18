import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;


public class Main extends JFrame implements ActionListener {
    private JTextField invoer1,invoer2,invoer3;
    private JButton blader1,blader2,blader3,calculate;
    private BufferedReader inFile;
    private JTextArea text1,text2,text3,text4,text5;
    private HashSet<String> lijst1 = new HashSet<>();
    private HashSet<String> lijst2 = new HashSet<>();
    private HashSet<String> lijst3 = new HashSet<>();
    private JComboBox<String> dropdown;




    public static void main(String[] args) {
        Main frame = new Main();
        frame.setSize(1000, 800);
        frame.createGUI();
        frame.setVisible(true);
        frame.setTitle("compare three lists");
    }

    /**
     * this function creates a gui
     */
    public void createGUI() {
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container window = getContentPane();
        window.setLayout(new FlowLayout());
        // makes a label
        JLabel label2 = new JLabel("Bestand 1");
        window.add(label2);
        label2.setPreferredSize(new Dimension(200, 25));

        //makes the textfield
        invoer1 = new JTextField();
        window.add(invoer1);
        invoer1.setPreferredSize(new Dimension(600, 25));

        //makes the button to find file
        blader1 = new JButton("blader");
        window.add(blader1);
        blader1.addActionListener(this);
        blader1.setPreferredSize(new Dimension(150, 25));

        //makes a label
        JLabel label3 = new JLabel("Bestand 2");
        window.add(label3);
        label3.setPreferredSize(new Dimension(200, 25));

        //makes the textfield
        invoer2 = new JTextField();
        window.add(invoer2);
        invoer2.setPreferredSize(new Dimension(600, 25));

        //makes the button to find file
        blader2 = new JButton("blader");
        window.add(blader2);
        blader2.addActionListener(this);
        blader2.setPreferredSize(new Dimension(150, 25));

        // makes a label
        JLabel label4 = new JLabel("Bestand 3");
        window.add(label4);
        label4.setPreferredSize(new Dimension(200, 25));

        //makes the textfield
        invoer3 = new JTextField();
        window.add(invoer3);
        invoer3.setPreferredSize(new Dimension(600, 25));

        //makes the button to find file
        blader3 = new JButton("blader");
        window.add(blader3);
        blader3.addActionListener(this);
        blader3.setPreferredSize(new Dimension(150, 25));

        //make textarea 1
        text1 = new JTextArea(20,15);
        window.add(text1);
        JScrollPane scroller1 =new JScrollPane(text1,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        window.add(scroller1);

        //make textarea 2
        text2 = new JTextArea(20,15);
        window.add(text2);
        JScrollPane scroller2 = new JScrollPane(text2,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        window.add(scroller2);

        //make textarea 3
        text3 = new JTextArea(20,15);
        window.add(text3);
        JScrollPane scroller3 =new JScrollPane(text3,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        window.add(scroller3);

        // make calculate button
        calculate =new JButton("calculate");
        window.add(calculate);
        calculate.addActionListener(this);

        //make a textfiel
        text4 = new JTextArea(10,80);
        window.add(text4);

        //make a dropdown menu
        String[] choices = {"overeenkomst 1&2", "overeenkomst 1&3","overeenkomst 2&3"};
        dropdown =new JComboBox<>(choices);
        dropdown.setPreferredSize(new Dimension(500,25));
        window.add(dropdown);

        //make an textfield
        text5 = new JTextArea(10,80);
        window.add(text5);

    }

    /**
     * reads in the file and adds the information to the right textfield and the information to a hasmap
     * @param invoer , textfield the field with the exactpath of the file
     */
    public void readFile(JTextField invoer)  {
        try {
            inFile = new BufferedReader(new FileReader(invoer.getText()));
            String line;
            inFile.readLine();

            //reads the entire file
            while ((line = inFile.readLine()) != null) {
                //determines from what list the information comes
                if(invoer==invoer1){
                    text1.append(line+"\n"); //write the information to the field
                    lijst1.add(line); //adds the infromation to the hashmap
                }
                else if(invoer==invoer2){
                    text2.append(line+"\n");
                    lijst2.add(line);
                }
                else if(invoer==invoer3){
                    text3.append(line+"\n");
                    lijst3.add(line);
                }

              } }
        catch (IOException e) {
            JOptionPane.showMessageDialog(null,
                    e.getMessage());
        }
        finally {
            try {
                inFile.close();
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null,
                        e.getMessage());
            }
        }}

    /**
     * This function gets active when a button gets used. It takes care of the printing of the exact path on the right
     * field and the right information on the textareas as well as calculating the overlap of the diffrent lists
     * @param e ActionEvent, one of the buttons get clicked
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        File selectedFile;
        int reply;
        // this pops up a screen so you can chose a file when the button blader is pressed
        if (e.getSource() == blader1 || e.getSource() == blader2||e.getSource()==blader3){
            JFileChooser fileChooser = new JFileChooser();
            reply = fileChooser.showOpenDialog(this);
            if (reply == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();
                // this is to determine at what screen the absolutepath needs to be printed
                if (e.getSource() == blader1) {
                    invoer1.setText(selectedFile.getAbsolutePath());
                } else if (e.getSource() == blader2) {
                    invoer2.setText(selectedFile.getAbsolutePath());
                } else if(e.getSource()==blader3){
                    invoer3.setText(selectedFile.getAbsolutePath());
                }
                }
        }
        else if (e.getSource()==calculate){
            //empties the three textfield with the contents of the three lists
            text1.setText("");
            text2.setText("");
            text3.setText("");
            // reads in all four lists
            readFile(invoer1);
            readFile(invoer2);
            readFile(invoer3);
            // orginallijst1 now contains the information from lijst 1
            HashSet<String> originallijst1 =new HashSet<>(lijst1);
            lijst1.retainAll(lijst2);  //lijst1 now contains all the values that are in lijst 1 and 2
            HashSet<String> samevalues1_2 = new HashSet<>(lijst1); //this value stores diffrence between lijs1 and2
            lijst1.retainAll(lijst3); //lijst 1 now contains all the values that are in all lists
            lijst2.retainAll(lijst3); //lijst 2now contains all the values that are in lijst 3 and 2
            lijst3.retainAll(originallijst1); // lijst 3 now contains all the values that are in lijst 1 and 3

            //so now the lists contain the following:
            // lijst1: the values that are in all the lists
            // lijst2: the values that are in list 2 and 3
            //lijst3: the values that are in list 1 and 3
            //samevalues1_2: the values that are in list 1 and 2

            //sets the text for the text field with the genes that are in all lists
            text4.setText("overeenkomstige genen tussen alle lijsten \n");
            for(int i=0;i<lijst1.size();i++){
               text4.append(lijst1.toArray()[i]+"\n");
            }


            Object option=dropdown.getSelectedItem(); //selects the item that the dropdown menu has selected
            if(option.equals("overeenkomst 1&2")){

            //checks what it stores and fills in the desired information in the last textarea
                text5.setText("Values both in list 1 and 2 \n");
                for(int i=0;i<samevalues1_2.size();i++){
                    text5.append(samevalues1_2.toArray()[i]+"\n");
                }

            }
            else if (option.equals("overeenkomt 2&3")){
                text5.setText("Values both in list 2 and 3 \n");
                for(int i=0;i<lijst2.size();i++){
                    text5.append(lijst2.toArray()[i]+"\n");
                }

            }
            else if(option.equals("overeenkomst 1&3")){
                text5.setText("Values both from list 1 and 3 \n");
                for(int i=0;i<lijst3.size();i++){
                    text5.append(lijst3.toArray()[i]+"\n");
                }

            }

        }
}



}