/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.math.BigInteger;
import java.util.Scanner;

/**
 *
 * @author Son
 */
public class Bai1 {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Scanner in = new Scanner(System.in);
        BigInteger[] ciphertext = null;
        BigInteger n = null;
        BigInteger d = null;
        String password = "";
        System.out.println("Enter text to be encrypted: ");
        password = in.nextLine();

        System.out.println("Password (Input) : " + password);

        RSA rsa = new RSA(8);
        n = rsa.getN();
        d = rsa.getD();
        ciphertext = rsa.encrypt(password);
        StringBuffer bf = new StringBuffer();
        for (int i = 0; i < ciphertext.length; i++) {
            bf.append(ciphertext[i].toString(16).toUpperCase());
            if (i != ciphertext.length - 1) {
                System.out.print("");
            }
        }
        String message = bf.toString();
        System.out.println();
        System.out.println("Encrypted Message : " + message);
        String dhash = rsa.decrypt(ciphertext, d, n);
        System.out.println();
        System.out.println("Decrypted Message : " + dhash);
    }
}
