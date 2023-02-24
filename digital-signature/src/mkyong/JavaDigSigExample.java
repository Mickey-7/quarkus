package mkyong;

import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.ArrayList;
import java.util.List;

public class JavaDigSigExample {
    List<byte[]> list;
    //The constructor of Message class builds the list that will be written to the file.
    //The list consists of the message and the signature.
    public JavaDigSigExample(String data, String keyFile) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException, IOException, InvalidKeySpecException {
        list = new ArrayList<>();
        list.add(data.getBytes());
        list.add(sign(data,keyFile));

    }


    public static void main(String[] args) throws SignatureException, NoSuchAlgorithmException, IOException, InvalidKeySpecException, InvalidKeyException {
        String data = JOptionPane.showInputDialog("type your message here");
        new JavaDigSigExample(data, "src/mkyong/privateKey")
                .writeToFile("src/mkyong/SignedData.txt");



    }

    //The method that signs the data using the private key that is stored in keyFile path
    private byte[] sign(String data, String keyFile) throws InvalidKeyException, SignatureException, NoSuchAlgorithmException, IOException, InvalidKeySpecException {
        Signature rsa = Signature.getInstance("SHA1withRSA");
        rsa.initSign(getPrivateKey(keyFile));
        rsa.update(data.getBytes());
        return rsa.sign();
    }

    //Method to retrieve the Private Key from a file
    private PrivateKey getPrivateKey(String filename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    ////Method to write the List of byte[] to a file
    private void writeToFile(String filename) throws IOException {
        File f = new File(filename);
        f.getParentFile().mkdirs();
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename));
        out.writeObject(list);
        out.close();
        System.out.println("your file is ready");
    }



}
