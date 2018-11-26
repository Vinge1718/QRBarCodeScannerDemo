package io.github.vinge1718.qrbarcodescannerdemo.models;

public class FinalStudent {
    String mAvatarUrl;
    String mClass;
    String mEmail;
    String mRoleName;
    String mTimeIn;
    int mUserId;
    String mUserName;

    public FinalStudent(String avatarUrl, String studentClass, String email, String roleName, String timeIn, int userId, String userName){
        this.mAvatarUrl = avatarUrl;
        this.mClass = studentClass;
        this.mEmail = email;
        this.mRoleName = roleName;
        this.mTimeIn = timeIn;
        this.mUserId = userId;
        this.mUserName = userName;

        this.mUserId = userId;
    }

    public String getImageUrl(){
        return mAvatarUrl;
    }

    public String getClasses(){
        return mClass;
    }

    public String getEmail(){
        return mEmail;
    }

    public String getRoleName(){
        return mRoleName;
    }

    public String getTimeIn(){
        return mTimeIn;
    }

    public int getUserId(){
        return mUserId;
    }

    public String getUserName(){
        return mUserName;
    }
}
