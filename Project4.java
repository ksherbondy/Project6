/*
* File: Project4.java
* Author: Kris Sherbondy
* Date: 15 Oct 2017
* Purpose: This program is able to store data for several students. The user is 
* able to create, update, delete, and find students in a database. The database
* stores information and is able to display that data in a JOptionPane for the user
* to see. 
 */


package project4;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.HashMap;

public class Project4 extends JFrame {

    private String id;
    private String name;
    private String major;
    private String selection;

    private Project4() {
        
        super("Project 4");
        JPanel main = new JPanel();
        main.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        //creating J Labels, TextFields, and Buttons needed for the JPanel
        JLabel idLabel = new JLabel("Id:");
        JLabel nameLabel = new JLabel("Name:");
        JLabel majorLabel = new JLabel("Major:");
        JLabel selectLabel = new JLabel("Choose Selection:");

        JTextField idTxt = new JTextField(null);
        JTextField nameTxt = new JTextField(null);
        JTextField majorTxt = new JTextField(null);

        JButton processBtn = new JButton("Process Request");

        String[] selectStr = {"Insert", "Delete", "Find", "Update"};
        JComboBox<String> selectCombo = new JComboBox<>(selectStr);
        String[] gradeStr = {"A", "B", "C", "D", "F"};
        String[] creditStr = {"3", "6"};
        
        //Creating a HashMap 
        HashMap<String, Student> studentDb = new HashMap<>();

        //By using GridBagLayout w/ GridBagConstraints we are able to make the 
        //Panel work better if/when a window is resized moved, etc. 
        c.fill = GridBagConstraints.HORIZONTAL;

        c.insets = new Insets(0, 0, 5, 5);
        c.gridx = 0;
        c.gridy = 0;
        main.add(idLabel, c);

        c.insets = new Insets(0, 5, 5, 0);
        c.gridx = 1;
        c.gridy = 0;
        main.add(idTxt, c);

        c.insets = new Insets(5, 0, 5, 5);
        c.gridx = 0;
        c.gridy = 1;
        main.add(nameLabel, c);

        c.insets = new Insets(5, 5, 5, 0);
        c.gridx = 1;
        c.gridy = 1;
        main.add(nameTxt, c);

        c.insets = new Insets(5, 0, 5, 5);
        c.gridx = 0;
        c.gridy = 2;
        main.add(majorLabel, c);

        c.insets = new Insets(5, 5, 5, 0);
        c.gridx = 1;
        c.gridy = 2;
        main.add(majorTxt, c);

        c.insets = new Insets(5, 0, 5, 5);
        c.gridx = 0;
        c.gridy = 3;
        main.add(selectLabel, c);

        c.insets = new Insets(5, 5, 5, 0);
        c.gridx = 1;
        c.gridy = 3;
        main.add(selectCombo, c);

        c.insets = new Insets(5, 0, 0, 5);
        c.gridx = 0;
        c.gridy = 4;
        main.add(processBtn, c);

        add(main);

        //The ActionListeners asses what function has been selected by the user
        //It then goes through a series of try, switch, if and else statements to
        //process the data and do what the user is requesting.
        processBtn.addActionListener((ActionEvent e) -> {

            id = idTxt.getText();
            name = nameTxt.getText();
            major = majorTxt.getText();
            selection = selectCombo.getSelectedItem().toString();

            try {

                if (id.isEmpty()) {
                    throw new NullPointerException();
                } else {

                    switch (selection) {

                        case "Insert":

                            if (studentDb.containsKey(id)) {
                                JOptionPane.showMessageDialog(null, "ID already exists in database", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {

                                studentDb.put(id, new Student(name, major));
                                JOptionPane.showMessageDialog(null, "Student added to Database", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                            break;

                        case "Delete":

                            if (!studentDb.containsKey(id)) {
                                JOptionPane.showMessageDialog(null, "ID does not exist in database", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {

                                studentDb.remove(id);
                                JOptionPane.showMessageDialog(null, "Student removed from Database", "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                            break;

                        case "Find":
                            if (!studentDb.containsKey(id)) {
                                JOptionPane.showMessageDialog(null, "ID does not exist in database", "Error", JOptionPane.ERROR_MESSAGE);
                            } else {
                                studentDb.get(id);
                                String toString = studentDb.get(id).toString();
                                JOptionPane.showMessageDialog(null, "Student found in Database\n" + toString, "Success", JOptionPane.INFORMATION_MESSAGE);
                            }
                            break;

                        case "Update":
                            if (studentDb.containsKey(id)) {
                                String grade = (String) JOptionPane.showInputDialog(null, "Choose grade:", "", JOptionPane.QUESTION_MESSAGE, null, gradeStr, gradeStr[0]);
                                if (grade != null) {
                                    String creditHours = (String) JOptionPane.showInputDialog(null, "Choose credits:", "", JOptionPane.QUESTION_MESSAGE, null, creditStr, creditStr[0]);
                                    if (creditHours != null) {
                                        studentDb.get(id).courseCompleted(grade, Integer.parseInt(creditHours));
                                        JOptionPane.showMessageDialog(null, "Student record was updated", "Success", JOptionPane.INFORMATION_MESSAGE);
                                    } else {
                                        JOptionPane.showMessageDialog(null, "Credits were not entered", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(null, "Grade was not entered", "Error", JOptionPane.ERROR_MESSAGE);
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "ID does not exists in database", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            break;
                    }
                }
            } catch (NullPointerException e1) {
                JOptionPane.showMessageDialog(null, "The ID text field is required", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        //Creating the size closing operation visibility and resizability of the JPanel.
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(350, 200);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }

    //Main method of the program used to create an instance of this program.
    public static void main(String[] args) {
        Project4 test = new Project4();
    }
}
