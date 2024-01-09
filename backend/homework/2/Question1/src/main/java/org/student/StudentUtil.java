package org.student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class StudentUtil {

    public static double[] calculateGPA(int[] studentIdList, char[][] studentsGrades) {

        int listLen = studentIdList.length;
        double[] gpa = new double[listLen];
        HashMap<Character,Integer> map = new HashMap<>();
        map.put('A',4);
        map.put('B',3);
        map.put('C',2);

        for(int i = 0; i < listLen; i++){
            double grades = 0.0;
            int m = studentsGrades[i].length;
            for(int j = 0; j < m; j++){
                grades += map.get(studentsGrades[i][j]);
            }
            double gradePoint = grades / m;
            gpa[i] = gradePoint;
        }

        return gpa;
    }

    public static int[] getStudentsByGPA(double lower, double higher, int[]
            studentIdList, char[][] studentsGrades) {

        if(lower > higher || lower < 0 || higher < 0)
            return new int[0];

        int listLen = studentIdList.length;
        double[] gpa = calculateGPA(studentIdList,studentsGrades);

        List<Integer> list = new ArrayList<>();
        for(int i = 0; i < listLen; i++){
            if(gpa[i] >= lower && gpa[i] <= higher){
                list.add(studentIdList[i]);
            }
        }

        int[] res = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            res[i] = list.get(i);
        }
        return res;
    }

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        Logging.logger.info("Enter the number of students:");
        int numberOfStudents = scanner.nextInt();

        int[] studentIdList = new int[numberOfStudents];
        Logging.logger.info("Enter the student IDs:");

        for (int i = 0; i < numberOfStudents; i++) {
            studentIdList[i] = scanner.nextInt();
        }

        char[][] studentsGrades = new char[numberOfStudents][];

        for (int i = 0; i < numberOfStudents; i++) {
            Logging.logger.info("Enter the grades for student " + studentIdList[i] + ":");
            String gradesInput = scanner.next();
            studentsGrades[i] = gradesInput.toCharArray();
        }

        double[] gpa = StudentUtil.calculateGPA(studentIdList,studentsGrades);
        for(int i = 0; i < gpa.length; i++){
            Logging.logger.info(String.format("GPA of student with id %d is %.4f", studentIdList[i], gpa[i]));
        }

        double higher;
        double lower;
        Logging.logger.info("Enter lower and higher limit : ");
        lower = scanner.nextDouble();
        higher = scanner.nextDouble();

        int[] res = StudentUtil.getStudentsByGPA(lower,higher,studentIdList,studentsGrades);
        if(res.length != 0){
            for(Integer i : res){
                Logging.logger.info("ID which have lower<=GPA<=higher : {} ", i);
            }
        }
        else{
            Logging.logger.warn("Invalid inputs!!");
        }

    }
}
