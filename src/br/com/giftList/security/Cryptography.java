package br.com.giftList.security;

import java.security.NoSuchAlgorithmException;

public interface Cryptography {

  String encrypt(String value) throws NoSuchAlgorithmException;

}
