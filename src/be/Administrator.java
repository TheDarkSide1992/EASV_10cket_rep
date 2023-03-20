package be;

public class Administrator {
    int adminID;
    String username;
    String password;

    public Administrator(String username, String password){
        this.username = username;
        this.password = password;
    }
    public String getUsername(){
        return username;
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
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
