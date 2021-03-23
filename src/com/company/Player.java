package com.company;

public class Player {
    private String Username;
    private String Password;

    public Player(){}

    public Player(String Username,String Password) {
        this.Username = Username;
        this.Password = Password;
    }
    public String getUsername() {
        return this.Username;
    }
    public String getPassword() {
        return this.Password;
    }
}
