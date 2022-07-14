package ZadPD4;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    public static void main(String[] args) {
        new Main();
    }
//Person static Parameters
    static String setName;
    static int setHeight;
    static int setWeight;
    static String setSize;

//Person Creation Class
    public class Person {
        String name;
        int height;
        int weight;
        String size;
        public Person(String name, int height, int weight, String size){
            this.name = name;
            this.height = height;
            this.weight = weight;
            this.size = size;
        }

        @Override
        public String toString(){
            return name + " (h=" +height + ", w=" + weight + ", size=" + size + ")";
        }


    }
    //Sliders Maker Class
    public static class sliderMaker implements ChangeListener {

        int min;
        int max;
        JSlider slajder;

        sliderMaker(int m, int x){
            this.min = m;
            this.max = x;
            slajder = new JSlider(min,max);
            slajder.setPaintTicks(true);
            slajder.setMinorTickSpacing(2);
            slajder.setPaintTrack(true);
            slajder.setMajorTickSpacing(10);
            slajder.setPaintLabels(true);
            slajder.addChangeListener(this);
        }

        @Override
        public void stateChanged(ChangeEvent e) {

        }
    }

    Main(){
        super("PERSONS");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        //components
        DefaultListModel<Person> ListModel = new DefaultListModel<Person>();
        JList<Person> list = new JList<Person>(ListModel);
        JScrollPane scrollPane1 = new JScrollPane(list,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane1.setPreferredSize(new Dimension(650, 250));
        JButton button1 = new JButton("Add person");
        JButton button2 = new JButton("Exit");
        JTextField textField1 = new JTextField();
        textField1.setPreferredSize(new Dimension(250,30));
        JLabel label1 = new JLabel("name:");
        JLabel label2 = new JLabel("size:");
        JPanel panel1 = new JPanel();
        JPanel panel2_1 = new JPanel(new GridLayout(1,1,1,1));
        JPanel panel2_2 = new JPanel(new GridLayout(1,1,1,1));
        JPanel panel2_3 = new JPanel(new GridLayout(2,1,1,1));
        JPanel panel3 = new JPanel();
        list.setCellRenderer(new MyListRenderer());


        //sliders borders
        Border empty = BorderFactory.createEmptyBorder(10,10,10,10 );
        Border blackline = BorderFactory.createLineBorder(Color.BLACK);
        CompoundBorder line = new CompoundBorder(empty, blackline);
        Border border1 = BorderFactory.createTitledBorder(line, "Height [cm]");
        Border border2 = BorderFactory.createTitledBorder(line, "Weight [kg]");
        panel2_1.setBorder(border1);
        panel2_2.setBorder(border2);




        //buttons
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (textField1.getText().equals("")){
                    JOptionPane.showMessageDialog(new JFrame(), "Nie podano imienia", "ERROR", JOptionPane.ERROR_MESSAGE);
                } else {
                    setName = textField1.getText();

                    //Person Creation
                    ListModel.add(0, new Person(setName, setHeight, setWeight, setSize));
                }

            }
        });


        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });


        //sliders
        sliderMaker slider1 = new sliderMaker(100,200);
        sliderMaker slider2 = new sliderMaker(40,120);
        setHeight = slider1.slajder.getValue();
        slider1.slajder.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setHeight = slider1.slajder.getValue();
            }
        });
        setWeight = slider2.slajder.getValue();
        slider2.slajder.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                setWeight = slider2.slajder.getValue();
            }
        });


        //combobox setup
        String[] sizes = {"XS", "S", "M", "L", "XL"};
        JComboBox<String> comboBox = new JComboBox<>(sizes);
        setSize = (String) comboBox.getSelectedItem();
        comboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == comboBox){
                    setSize = (String) comboBox.getSelectedItem();
                }
            }
        });



        //setting up components
        panel1.add(scrollPane1);
        panel2_1.add(slider1.slajder);
        panel2_2.add(slider2.slajder);
        panel2_3.add(panel2_1);
        panel2_3.add(panel2_2);
        panel3.add(label1);
        panel3.add(textField1);
        panel3.add(label2);
        panel3.add(comboBox);
        panel3.add(Box.createHorizontalStrut(20));
        panel3.add(button1);
        panel3.add(Box.createHorizontalStrut(70));
        panel3.add(button2);

        //adding components
        add(panel1, BorderLayout.NORTH);
        add(panel2_3, BorderLayout.CENTER);
        add(panel3, BorderLayout.SOUTH);
        pack();
        this.setSize(700,500);
        this.setVisible(true);

    }


}

//Jlist color row
class MyListRenderer extends DefaultListCellRenderer {

    @Override
    public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {

        JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        if (value.toString().contains("XL")){
            label.setBackground(Color.RED);
        } else if (value.toString().contains("XS")){
            label.setBackground(Color.GREEN);
        }

        return label;
    }

}
















