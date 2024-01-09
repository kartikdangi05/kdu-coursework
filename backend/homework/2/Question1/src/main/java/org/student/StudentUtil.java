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
            double GPA = grades / (double) m;
            gpa[i] = GPA;
        }

        return gpa;
    }

    public static int[] getStudentsByGPA(double lower, double higher, int[]
            studentIdList, char[][] studentsGrades) {

        if(lower > higher || lower < 0 || higher < 0)
            return null;

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

//        int[] studentIdList = {1001, 1002};
//        char[][] studentsGrades = { { 'A', 'A', 'A', 'B' }, { 'A', 'B', 'B' } };

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the number of students:");
        int numberOfStudents = scanner.nextInt();

        int[] studentIdList = new int[numberOfStudents];
        System.out.println("Enter the student IDs:");

        for (int i = 0; i < numberOfStudents; i++) {
            studentIdList[i] = scanner.nextInt();
        }

        char[][] studentsGrades = new char[numberOfStudents][];

        for (int i = 0; i < numberOfStudents; i++) {
            System.out.println("Enter the grades for student " + studentIdList[i] + ":");
            String gradesInput = scanner.next();
            studentsGrades[i] = gradesInput.toCharArray();
        }

        double[] gpa = StudentUtil.calculateGPA(studentIdList,studentsGrades);
        for(int i = 0; i < gpa.length; i++){
            Logging.logger.info("GPA of student with id " + studentIdList[i] + " is " + gpa[i]);
        }

        double lower, higher;
        System.out.println("Enter lower and higher limit : ");
        lower = scanner.nextDouble();
        higher = scanner.nextDouble();

        if(StudentUtil.getStudentsByGPA(lower,higher,studentIdList,studentsGrades) != null) {
            int[] res = StudentUtil.getStudentsByGPA(lower,higher,studentIdList,studentsGrades);
            for(Integer i : res){
                Logging.logger.info("ID which have lower<=GPA<=higher : " + i);
            }
        }
        else{
            Logging.logger.warn("Invalid inputs!!");
        }

    }
}
