package service;

import model.Admin;
import model.Borrower;

import java.util.List;

public class AuthService {

    public String borrowerLogin(String email, String password, List<Borrower> allBorrower){
        for(Borrower i:allBorrower){
            if(i.getEmail().equals(email)) {
                if(i.getPassword().equals(password)){
                    i.setLoggedIn(true);
                    return  "Logged in successfully as Borrower..!"+"\nHello "+i.getName()+" !";
                }
                else{
                    return "Incorrect password..!";
                }
            }
        }
        return  "Invalid Credentials";
    }

    public String adminLogin(String email,String password , List<Admin> allAdmins){
        for(Admin i:allAdmins){
            if(i.getEmail().equals(email)) {
                if(i.getPassword().equals(password)){
                    i.setLoggedIn(true);
                    return  "Logged in successfully as Admin..!"+"\nHello "+i.getName()+" !";
                }
                else{
                    return "Incorrect password..!";
                }
            }
        }
        return  "Invalid Credentials";
    }

//    public String adminLogout(String)

}
