package br.com.giftList.security;

import java.security.NoSuchAlgorithmException;

public class CryptographySHA256 extends CryptographyGeneric implements Cryptography {

  public String encrypt(String value) throws NoSuchAlgorithmException {
    return encryptByAlgorithm("SHA-256", value);
  }

}
