package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character
        if(this.password.equals(oldPassword)) {
            change(newPassword);
        }
    }
    private void change(String newPassword) {
        int len = newPassword.length();
        if(len<8) return;

        if(!isUpper(newPassword)) return;

        if(!isLower(newPassword)) return;

        if(!isNum(newPassword)) return;

        if(!isSpecial(newPassword)) return;

        this.password = newPassword;
    }
    private boolean isUpper(String newPassword) {
        int len = newPassword.length();
        for(int i=0;i<len;i++) {
            char ch = newPassword.charAt(i);
            if(ch >= 65 && ch<= 90) return true;
        }

        return false;
    }
    private boolean isLower(String newPassword) {
        int len = newPassword.length();
        for(int i=0;i<len;i++) {
            char ch = newPassword.charAt(i);
            if(ch >= 97 && ch<= 122) return true;
        }

        return false;
    }
    private boolean isNum(String newPassword) {
        int len = newPassword.length();
        for(int i=0;i<len;i++) {
            char ch = newPassword.charAt(i);
            if(ch >= 48 && ch<= 57) return true;
        }

        return false;
    }
    private boolean isSpecial(String newPassword) {
        int len = newPassword.length();
        for(int i=0;i<len;i++) {
            char ch = newPassword.charAt(i);
            if(!isUpper(String.valueOf(ch)) && !isLower(String.valueOf(ch)) && !isNum(String.valueOf(ch))) return true;
        }

        return false;
    }
}
