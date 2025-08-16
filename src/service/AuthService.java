package service;


import exception.UserNotFoundException;
import model.User;

import java.util.ArrayList;
import java.util.List;

public class AuthService {
    private static User currLoggedIn=null;
    private static boolean overallLoggedInStatus;
    public static List<User> allUsers=new ArrayList<>();

    public static User getCurrLoggedIn() {
        return currLoggedIn;
    }

    public static void setCurrLoggedIn(User currLoggedIn) {
        AuthService.currLoggedIn = currLoggedIn;
    }

    public static boolean isOverallLoggedInStatus() {
        return overallLoggedInStatus;
    }

    public static void setOverallLoggedInStatus(boolean overallLoggedInStatus) {
        AuthService.overallLoggedInStatus = overallLoggedInStatus;
    }

    public void login(String email, String password){
        for(User i:allUsers){
            if(i.getEmail().equals(email)) {
                if(i.getPassword().equals(password)){
                    System.out.println("Logged in successfully as "+i.getRole()+"..!"+"\nHello "+i.getName()+" !");
                    setCurrLoggedIn(i);
                    setOverallLoggedInStatus(true);
                    return;
                }
                else{
                    System.out.println("Incorrect password..!");
                }
            }
        }
        throw new UserNotFoundException();
    }

    public void logout() {
        if(overallLoggedInStatus){
            System.out.println("Logged out successfully..!");
            setOverallLoggedInStatus(false);
            setCurrLoggedIn(null);
            return;
        }
        System.out.println("No user is logged in currently..!");
    }


}
