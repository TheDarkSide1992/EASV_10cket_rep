package be;

public class Administrator {

    private int adminID;
    private String userName;
    private String name;
    private int userType;
    private String userEmail;
    private String userPhone;
    String password;

    public Administrator(int id, String username, String name, int userType, String userEmail, String userPhone){
        this.adminID = id;
        this.userName = username;
        this.name = name;
        this.userType = userType;
        this.userEmail = userEmail;
        this.userPhone = userPhone;
    }
    public boolean validPassword(String password){
        String specialChars = "!,.:;<>\\/()#%=+?'*";
        if (password.length()>8){
            for (int i = 0; i < password.length(); i++) {
                for (int j = 0; j< specialChars.length(); i++){
                    if(password.charAt(i) == specialChars.charAt(j)){
                        return true;
                    }
                }
            }
        }
        return false;
    }
    public void setPassword(String password){
        if (!validPassword(password)){
            this.password = password;
        }
    }

    @Override
    public String toString() {
        return "AdministratorID: " + adminID +
                ", username='" + userName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }
}
