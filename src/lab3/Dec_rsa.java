/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import javax.swing.JOptionPane;

/**
 *
 * @author Son
 */
public class Dec_rsa {

    public static void main(String args[]) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("G:\\test\\EncRSA.txt")));
        String ctext = in.readLine();
        BigInteger c = new BigInteger(ctext);
        FileInputStream f = new FileInputStream("G:\\test\\RSAprivate.txt");
        ObjectInputStream b = new ObjectInputStream(f);
        RSAPrivateKey prk = (RSAPrivateKey) b.readObject();
        BigInteger d = prk.getPrivateExponent();
        BigInteger n = prk.getModulus();
        System.out.println("d= " + d);
        System.out.println("n= " + n);
        BigInteger m = c.modPow(d, n);
        System.out.println("m= " + m);
        byte[] mt = m.toByteArray();
        System.out.println("PlainText is ");
        for (int i = 0; i < mt.length; i++) {
            System.out.print((char) mt[i]);
        }
    }

    public static String decrypt() {
        StringBuilder sb = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("G:\\test\\EncRSA.txt")));
            String ctext = in.readLine();
            BigInteger c = new BigInteger(ctext);
            FileInputStream f = new FileInputStream("G:\\test\\RSAprivate.txt");
            ObjectInputStream b = new ObjectInputStream(f);
            RSAPrivateKey prk = (RSAPrivateKey) b.readObject();
            BigInteger d = prk.getPrivateExponent();
            BigInteger n = prk.getModulus();
            System.out.println("d= " + d);
            System.out.println("n= " + n);
            BigInteger m = c.modPow(d, n);
            System.out.println("m= " + m);
            byte[] mt = m.toByteArray();
            System.out.println("PlainText is ");
            for (int i = 0; i < mt.length; i++) {
                System.out.print((char) mt[i]);
                sb.append((char)mt[i]);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error when encrypting text");
        }
        return sb.toString();
    }
}
