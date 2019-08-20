package com.beardby.entity.commands;

import com.beardby.entity.User;

public class changeName implements Command {
    private final String commandText = "/name";

    @Override
    public boolean execCommand(User user, String... commandSpecifier) {
        if (commandSpecifier != null) {
            user.setName(commandSpecifier[0]);
            if (user.getName().equals(commandSpecifier[0])) return true;
            else return false;
        }
        else return false;
    }

    @Override
    public String getCommandText() {
        return commandText;
    }
}
