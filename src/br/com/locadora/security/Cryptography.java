package br.com.locadora.security;

import java.security.NoSuchAlgorithmException;

public interface Cryptography {

  String encrypt(String value) throws NoSuchAlgorithmException;

}
