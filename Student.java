/*
* File: Student.java
* Author: Kris Sherbondy
* Date: 15 Oct 2017
* Purpose: This program is able to store data for several students. The user is 
* able to create, update, delete, and find students in a database. The database
* stores information and is able to display that data in a JOptionPane for the user
* to see. 
 */


package project4;

import java.text.DecimalFormat;

public class Student {

  
    //Declaring all variables needed for the program to work. 
    private String name;
    private String major;
    private double credits;
    private double qualityPoints;
    private int gradePoints;
    private double gpa =  4.0;
    private static final DecimalFormat DF = new DecimalFormat("#0.00");

  
    //Method for creating an instance of a student
    Student(String name, String major) {
        this.name = name;
        this.major = major;
        credits = 0;
        qualityPoints = 0;
    }

   //This method has teh user put in what grade the student earned. Using a switch
    //statement helps clean up the code. The GPA is then worked out and stored.
    void courseCompleted(String grade, int creditHours) {
      
        switch (grade) {
            case "A":
                gradePoints = 4;
                break;
            case "B":
                gradePoints = 3;
                break;
            case "C":
                gradePoints = 2;
                break;
            case "D":
                gradePoints = 1;
                break;
            case "F":
                gradePoints = 0;
                break;
        }
   
        gradePoints = gradePoints*creditHours;
        qualityPoints += gradePoints;
        credits += creditHours;
        gpa = qualityPoints/credits;
    }

   //Overridden toString() method used to display student name, major, and GPA.
    @Override
    public String toString() {
        return "\nName: \t" + name + "\nMajor: \t" + major + "\nGPA: \t" + DF.format(gpa);
    }
}