package org.example.springpractice;

public class GridConsole implements ExamConsole{
    private Exam exam;

    public GridConsole(Exam exam) {
        this.exam = exam;
    }

    public GridConsole() {
    }

    public void print() {
        System.out.printf("Grid:total is %d, avg is %f\n",exam.total(),exam.avg());
    }

    public void setExam(Exam exam) {
        this.exam = exam;
    }
}
