/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab3;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.OutputStreamWriter;
import java.math.BigInteger;
import java.security.interfaces.RSAPublicKey;
import javax.swing.JOptionPane;

/**
 *
 * @author Son
 */
public class EncRSA {

    public static void main(String[] args) throws Exception {
        // TODO Auto-generated method stub
        String s = "Mot hai ba bon nam sau bay tam chin";
        FileInputStream f = new FileInputStream("G:\\test\\RSApublic.txt");
        ObjectInputStream b = new ObjectInputStream(f);
        RSAPublicKey pbk = (RSAPublicKey) b.readObject();
        BigInteger e = pbk.getPublicExponent();
        BigInteger n = pbk.getModulus();
        System.out.println("e= " + e);
        System.out.println("n= " + n);
        byte ptext[] = s.getBytes("UTF8");
        BigInteger m = new BigInteger(ptext);
        BigInteger c = m.modPow(e, n);
        System.out.println("c= " + c);
        String cs = c.toString();
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("G:\\test\\EncRSA.txt")));
        out.write(cs, 0, cs.length());
        out.close();
    }

    public static String encrypt(String s) {
        String cs = null;
        try {
            FileInputStream f = new FileInputStream("G:\\test\\RSApublic.txt");
            ObjectInputStream b = new ObjectInputStream(f);
            RSAPublicKey pbk = (RSAPublicKey) b.readObject();
            BigInteger e = pbk.getPublicExponent();
            BigInteger n = pbk.getModulus();
            System.out.println("e= " + e);
            System.out.println("n= " + n);
            byte ptext[] = s.getBytes("UTF8");
            BigInteger m = new BigInteger(ptext);
            BigInteger c = m.modPow(e, n);
            System.out.println("c= " + c);
            cs = c.toString();
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("G:\\test\\EncRSA.txt")));
            out.write(cs, 0, cs.length());
            out.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error when encrypting text");
        }
        return cs;
    }
}
