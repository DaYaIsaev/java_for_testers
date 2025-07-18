package ru.stqa.mantis.model;

public record UserData(String username, String password) {

    public UserData(){
        this("","");
    }

    public UserData withUserName(String username){
        return new UserData(this.username,"");

    }
    public UserData withPassword(String password){
        return new UserData("", this.password);

    }


}

