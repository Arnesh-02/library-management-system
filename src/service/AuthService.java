package service;

import exception.AdminNotFoundException;
import exception.UserNotFoundException;
import model.Admin;
import model.Borrower;
import model.Admin;
import model.User;

import java.util.List;

import static model.Admin.adminList;

public class AuthService {

    public Borrower borrowerLogin(String email, String password, List<Borrower> allBorrower){
        for(Borrower i:allBorrower){
            if(i.getEmail().equals(email)) {
                if(i.getPassword().equals(password)){
                    i.setLoggedIn(true);
                    System.out.println("Logged in successfully as Borrower..!"+"\nHello "+i.getName()+" !");
                    return i;
                }
                else{
                    System.out.println("Incorrect password..!");
                    return null;
                }
            }
        }
        throw new UserNotFoundException();
    }

    public Admin adminLogin(String email,String password) throws AdminNotFoundException {
        List <Admin> allAdmins=adminList;
        for(Admin i:allAdmins){
            if(i.getEmail().equals(email)) {
                if(i.getPassword().equals(password)){
                    i.setLoggedIn(true);
                    System.out.println("Logged in successfully as Admin..!"+"\nHello "+i.getName()+" !");
                    return i;
                }
                else{
                    System.out.println("Incorrect password..!");
                    return null;
                }
            }
        }
        throw  new AdminNotFoundException();
    }

    public void logout(User currLoggedIn) {

    }

//    public String adminLogout()

}
