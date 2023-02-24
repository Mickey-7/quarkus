package mkyong;

import java.io.*;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class VerifyMessage {
    private List<byte[]> list;

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, ClassNotFoundException, InvalidKeySpecException {
        new VerifyMessage("src/mkyong/SignedData.txt","src/mkyong/publicKey");
    }

    //The constructor of VerifyMessage class retrieves the byte arrays from the File
    //and prints the message only if the signature is verified.
    public VerifyMessage(String filename,String keyFile) throws IOException, ClassNotFoundException, NoSuchAlgorithmException, SignatureException, InvalidKeyException, InvalidKeySpecException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename));
        this.list = (List<byte[]>) ois.readObject();
        ois.close();
        
        System.out.println(
                verifySignature(list.get(0), list.get(1), keyFile) ? 
                        "VERIFIED MESSAGE\n----------------\n" + new String(list.get(0)) :
                        "Could not verify the signature."
                );

        /*run console output
        VERIFIED MESSAGE
        ----------------
        Hello from mkyong.com!!
        */
    }
    
    //Method for signature verification that initializes with the Public Key, 
    //updates the data to be verified and then verifies them using the signature
    private boolean verifySignature(byte[] data, byte[] signatureBytes, String keyFile) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException, IOException, InvalidKeySpecException {
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initVerify(getPublicKey(keyFile));
        signature.update(data);
        return signature.verify(signatureBytes);
        
    }

    //Method to retrieve the Public Key from a file
    private PublicKey getPublicKey(String keyFilename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Files.readAllBytes(new File(keyFilename).toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

}
