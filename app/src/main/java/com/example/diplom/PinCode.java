package com.example.diplom;


import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

public interface PinCode {
    boolean hasPin();
    boolean checkPin(String pin) throws UnsupportedEncodingException, NoSuchAlgorithmException;
    void saveNew(String pin) throws UnsupportedEncodingException, NoSuchAlgorithmException;
}
