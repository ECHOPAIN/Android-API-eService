package com.example.android_api_eservice.presentation.viewmodel;

public class Event<T> {
    private boolean hasBeenHandled;
    private T content;

    public Event(T content) {
        this.content = content;
    }

    public T getContentIfHasntBeenHandled() {
        if (hasBeenHandled) {
            return null;
        } else {
            hasBeenHandled = true;
            return content;
        }
    }
}
