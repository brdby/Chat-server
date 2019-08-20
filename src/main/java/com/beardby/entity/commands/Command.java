package com.beardby.entity.commands;

import com.beardby.entity.User;

public interface Command {
    boolean execCommand(User user, String... commandSpecifier);

    String getCommandText();
}
