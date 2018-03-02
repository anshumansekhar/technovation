package com.technovation.technovation20;

/**
 * Created by Anshuman-HP on 01-03-2018.
 */

public class NotificationMessage {
    String message;
    String title;
    String timeOfPost;

    public NotificationMessage() {
    }

    public NotificationMessage(String message, String title, String timeOfPost) {
        this.message = message;
        this.title = title;
        this.timeOfPost = timeOfPost;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTimeOfPost() {
        return this.timeOfPost;
    }

    public void setTimeOfPost(String timeOfPost) {
        this.timeOfPost = timeOfPost;
    }
}
