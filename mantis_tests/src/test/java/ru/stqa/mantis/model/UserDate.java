package ru.stqa.mantis.model;

public record UserDate(String username, String email, String password) {

    public UserDate(){
        this("","","");
    }

    public UserDate withUsername (String username){
        return new UserDate(username, username + "@localhost", this.password);
    }


    public UserDate withPassword (String password){
        return new UserDate(this.username, this.email, password);
    }
}
