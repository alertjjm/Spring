package org.example.springpractice;

public class InlineConsole implements ExamConsole{
    private Exam exam;

    public InlineConsole(Exam exam) {
        this.exam = exam;
    }

    public InlineConsole() {
    }

    public void print() {
        System.out.printf("total is %d, avg is %f\n",exam.total(),exam.avg());
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
