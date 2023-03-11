package com.example.springJDBC.dto;

import com.example.springJDBC.entity.User;

import java.util.List;

public class UsersInfoResponse {

    private String usersInfoOutput;

    public UsersInfoResponse() {
    }

    public UsersInfoResponse(List<User> users) {
        StringBuilder usersOutput = new StringBuilder();
        for(User user : users) {
            usersOutput.append("id is ").append(user.getId()).append(", ");
            usersOutput.append("name is ").append(user.getName()).append(", ");
            usersOutput.append("age is ").append(user.getAge()).append(" ");
        }
        this.usersInfoOutput = usersOutput.toString();
    }

    public String getUsersInfoOutput() {
        return usersInfoOutput;
    }

    public void setUsersInfoOutput(String usersInfoOutput) {
        this.usersInfoOutput = usersInfoOutput;
    }
}
