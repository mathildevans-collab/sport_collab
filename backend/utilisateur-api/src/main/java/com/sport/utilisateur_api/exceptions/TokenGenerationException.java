package com.sport.utilisateur_api.exceptions;

public class TokenGenerationException extends Exception {
  
  public TokenGenerationException(String message) {
        super(message);
    }

    public TokenGenerationException(String message, Throwable cause) {
        super(message, cause);
    }

    public TokenGenerationException(Throwable cause) {
        super(cause);
    }

}
