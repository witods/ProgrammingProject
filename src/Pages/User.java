package Pages;
public class User {
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private String userMail;
    private int userCredits;
    private int userBet;
    private int recoverCode;

    //Constructor die variablen initialiseerd met gegevens uit de database
    public User(String uName,String uFirstName, String uLastName, String p, String m) {

        this.userName = uName;
        this.userFirstName = uFirstName;
        this.userLastName = uLastName;
        this.userPassword = p;
        this.userMail = m;
    }

    //Getters en setters voor Instance fields
    public String getUserName() {
        return userName;
    }

    public void setUserName(String n) {
        this.userName = userName;
    }
    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String p) {
        this.userPassword = userPassword;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String m) {
        this.userMail = userMail;
    }

    public int getUserCredits() {
        return userCredits;
    }

    public void takeUserCredtis(int b) {
        if((userCredits-b)<=0) {
            userCredits = 0;
        }else {
            userCredits -= b;
        }
    }
    public void giveUserCredits(int b) {
        userCredits += b;
    }
    public int getRecoverCode() {return recoverCode;
    }

    public void setRecoverCode(int recoverCode) {
        this.recoverCode = recoverCode;
    }

}
