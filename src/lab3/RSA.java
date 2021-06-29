/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Random;

/**
 *
 * @author Son
 */
public class RSA {

    int primeSize;
    BigInteger p, q;
    BigInteger N;
    BigInteger r;
    BigInteger E, D;

    public RSA() {

    }

    public RSA(int primeSize) {
        this.primeSize = primeSize;
        generatePrimeNumbers();
        generatePublicPrivateKeys();
    }

    public void generatePrimeNumbers() {
        p = BigInteger.probablePrime(primeSize / 2, new Random());
        do {
            q = BigInteger.probablePrime(primeSize / 2, new Random());
        } while (q.compareTo(p) == 0);
    }

    public void generatePublicPrivateKeys() {
        N = p.multiply(q);
        r = p.subtract(BigInteger.valueOf(1));
        r = r.multiply(q.subtract(BigInteger.valueOf(1)));

        do {
            E = new BigInteger(2 * primeSize, new Random());
        } while ((E.compareTo(r) != -1) || (E.gcd(r).compareTo(BigInteger.valueOf(1)) != 0));
        D = E.modInverse(r);
    }

    public BigInteger[] encrypt(String message) {
        int i;
        byte[] temp = new byte[1];

        byte[] digits = message.getBytes();

        BigInteger[] bigDigits = new BigInteger[digits.length];
        for (i = 0; i < bigDigits.length; i++) {
            temp[0] = digits[i];
            bigDigits[i] = new BigInteger(temp);
        }

        BigInteger[] encrypted = new BigInteger[bigDigits.length];
        for (i = 0; i < bigDigits.length; i++) {
            encrypted[i] = bigDigits[i].modPow(E, N);
        }
        return encrypted;
    }

    public BigInteger[] encrypt(String message, BigInteger userD, BigInteger userN) {
        int i;
        byte[] temp = new byte[1];
        byte[] digits = message.getBytes();
        BigInteger[] bigdigits = new BigInteger[digits.length];
        for (i = 0; i < bigdigits.length; i++) {
            temp[0] = digits[i];
            bigdigits[i] = new BigInteger(temp);
        }
        BigInteger[] encrypted = new BigInteger[bigdigits.length];
        for (i = 0; i < bigdigits.length; i++) {
            encrypted[i] = bigdigits[i].modPow(userD, userN);
        }
        return encrypted;
    }

    public String decrypt(BigInteger[] encrypted, BigInteger D, BigInteger N) {
        int i;
        BigInteger[] decrypted = new BigInteger[encrypted.length];
        for (i = 0; i < decrypted.length; i++) {
            decrypted[i] = encrypted[i].modPow(D, N);
        }
        char[] charArray = new char[decrypted.length];

        for (i = 0; i < charArray.length; i++) {
            charArray[i] = (char) (decrypted[i].intValue());
        }
        return new String(charArray);
    }

    public int getPrimeSize() {
        return primeSize;
    }

    public void setPrimeSize(int primeSize) {
        this.primeSize = primeSize;
    }

    public BigInteger getP() {
        return p;
    }

    public void setP(BigInteger p) {
        this.p = p;
    }

    public BigInteger getQ() {
        return q;
    }

    public void setQ(BigInteger q) {
        this.q = q;
    }

    public BigInteger getN() {
        return N;
    }

    public void setN(BigInteger N) {
        this.N = N;
    }

    public BigInteger getR() {
        return r;
    }

    public void setR(BigInteger r) {
        this.r = r;
    }

    public BigInteger getE() {
        return E;
    }

    public void setE(BigInteger E) {
        this.E = E;
    }

    public BigInteger getD() {
        return D;
    }

    public void setD(BigInteger D) {
        this.D = D;
    }

    public static void main(String[] args) throws IOException {
        int primeSize = 8;

        RSA rsa = new RSA(primeSize);

        System.out.println("Key Size: [" + primeSize + "]");

        System.out.println("");

        System.out.println("Generated prime numbers p and q");

        System.out.println("p: [" + rsa.getP().toString(16).toUpperCase() + "]");

        System.out.println("q: [" + rsa.getQ().toString(16).toUpperCase() + "]");

        System.out.println("");

        System.out.println("The public key is the pair (N, E) which will be published.");

        System.out.println("N: [" + rsa.getN().toString(16).toUpperCase() + "]");

        System.out.println("E: [" + rsa.getE().toString(16).toUpperCase() + "]");

        System.out.println("");

        System.out.println("The private key is the pair(N, D) which will be kept private.");

        System.out.println("N: [" + rsa.getN().toString(16).toUpperCase() + "]");

        System.out.println("D: [" + rsa.getD().toString(16).toUpperCase() + "]");

        System.out.println("");

        // Get message (plaintext) from user
        System.out.println("Please enter message (plaintext):");

        String plaintext = (new BufferedReader(new InputStreamReader(System.in))).readLine();

        System.out.println("");

        // Encrypt Message
        BigInteger[] ciphertext = rsa.encrypt(plaintext);

        System.out.print("Ciphertext: [");
        for (int i = 0; i < ciphertext.length; i++) {

            System.out.print(ciphertext[i].toString(16).toUpperCase());

            if (i != ciphertext.length - 1) {
                System.out.print("");
            }

        }
        System.out.println("J");
        System.out.println("");
        RSA rsal = new RSA(8);
        String recoveredPlaintext = rsal.decrypt(ciphertext, rsa.getD(), rsa.getN());
        System.out.println("Recovered plaintext: [" + recoveredPlaintext + "]");
    }
}
