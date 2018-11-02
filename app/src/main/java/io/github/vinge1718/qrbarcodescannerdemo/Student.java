package io.github.vinge1718.qrbarcodescannerdemo;

public class Student {
    private String mName;
    private String mImageUrl;
    private String mClass;

    public Student(String studentName, String imageUrl, String studentClass){
        this.mName = studentName;
        this.mClass = studentClass;
        this.mImageUrl = imageUrl;
    }

    public String getStudentName(){
        return mName;
    }

    public String getClasses(){
        return mClass;
    }

    public String getImageUrl(){
        return mImageUrl;
    }
}
