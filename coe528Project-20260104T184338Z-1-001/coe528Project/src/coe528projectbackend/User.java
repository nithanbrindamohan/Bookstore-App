/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coe528projectbackend;

/**
 *
 * @author chasv
 */
public class User {

private String userName = new String();
private String password = new String();

    public User() {
    }

    public User(String name, String passWord) {
        userName = name; 
        password = passWord;
    }

    public String getName() {
        return userName;
    }

    public String getPassWord() {
        return password;
    }

    public void setName(String name) {
        this.userName = name;
    }

    public void setPassWord(String passWord) {
        this.password = passWord;
    }
    
    
    
}
