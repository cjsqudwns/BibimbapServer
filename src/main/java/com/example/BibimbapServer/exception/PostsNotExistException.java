package com.example.BibimbapServer.exception;

public class PostsNotExistException extends RuntimeException{
    public PostsNotExistException(String message) {
        super(message);
    }
}