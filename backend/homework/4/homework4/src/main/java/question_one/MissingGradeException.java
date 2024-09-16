package question_one;

public class MissingGradeException extends Exception{
    private final int studentId;
    public MissingGradeException(int id){
        super("Grade missing for studentId ".concat(Integer.toString(id)));
        this.studentId = id;
    }

    public int getStudentId() {
        return studentId;
    }
}
