package be;

public class EventCoordinator extends User {
    public EventCoordinator(int userID, String userNAme, String userFirstName, String userEmail, String userTLF, int userTypeId) {
        super(userID, userNAme, userFirstName, userEmail, userTLF, userTypeId);
    }



    /*
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
    } */

}
