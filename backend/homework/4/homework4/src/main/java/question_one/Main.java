package question_one;

import org.student.Logging;

public class Main {
    public static void main(String[] args) {
        int[] studentIdList = {1001, 1002};
        char[][] studentsGrades = {{'A', 'A', 'A', 'B'}, {'A', ' '}};

        try {
            StudentUtil.calculateGPA(studentIdList,studentsGrades);
            StudentUtil.getStudentsByGPA(4.5, 5, studentIdList, studentsGrades);
        } catch (MissingGradeException | InvalidDataException e) {
            Logging.logErr(e.getMessage());
        }
    }
}
