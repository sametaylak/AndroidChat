package com.sametaylak.chat;

/**
 * Created by sametaylak on 19/09/2017.
 */

class Message {

    String name;
    String message;

    Message(String name, String message) {
        this.name = name;
        this.message = message;
    }

    Message() {}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
