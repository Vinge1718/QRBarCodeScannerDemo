package io.github.vinge1718.qrbarcodescannerdemo.models;

public class Student {
    private String mName;
    private String mImageUrl;
    private String mClass;

    public Student(String imageUrl, String studentName,  String studentClass){
        this.mImageUrl = imageUrl;
        this.mName = studentName;
        this.mClass = studentClass;
    }

    public String getImageUrl(){
        return mImageUrl;
    }

    public String getStudentName(){
        return mName;
    }

    public String getClasses(){
        return mClass;
    }
}
