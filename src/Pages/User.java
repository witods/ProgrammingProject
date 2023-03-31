package Pages;
public class User {
    private String userName;
    private String userFirstName;
    private String userLastName;
    private String userPassword;
    private int userAge;
    private String userMail;
    private int userCredits;
    private int userBet;
    public User(String uName,String uFirstName, String uLastName, String p, int a, String m, int c) {

        this.userName = uName;
        this.userFirstName = uFirstName;
        this.userLastName = uLastName;
        this.userPassword = p;
        this.userAge = a;
        this.userMail = m;
        this.userCredits = c;
    }
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

    public int getUserAge() {
        return userAge;
    }

    public void setUserAge(int a) {
        this.userAge = userAge;
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
}
