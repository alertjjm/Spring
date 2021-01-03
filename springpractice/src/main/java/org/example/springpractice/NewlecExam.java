package org.example.springpractice;

import jdk.nashorn.internal.objects.annotations.Setter;

public class NewlecExam implements Exam{
    private int kor, eng, math, com;
    public int total() {
        return kor+eng+math+com;
    }

    public double avg() {
        return total()/4.0;
    }

    public NewlecExam() {
        kor=0;
        eng=0;
        math=0;
        com=0;
    }

    public void setKor(int kor) {
        this.kor = kor;
    }

    public void setEng(int eng) {
        this.eng = eng;
    }

    public void setMath(int math) {
        this.math = math;
    }

    public void setCom(int com) {
        this.com = com;
    }
}
