package mkyong;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.util.Base64;

/*
*Asymmetric Cryptography, also known as Public Key Cryptography,
*is an encryption system in which two different but uniquely related cryptographic keys are used.
*
* The data encrypted using one key can be decrypted with the other.
* These keys are known as Public and Private Key Pair,
* and as the name implies the private key must remain private
* while the public key can be distributed.
*
* The most popular Public Key Algorithms are RSA, Diffie-Hellman, ElGamal, DSS.
*/
public class JavaAsymmetricCryptographyExample {

    /*
    There are several ways to generate a Public-Private Key Pair depending on your platform.
    In this example, we will create a pair using Java.
    The Cryptographic Algorithm we will use in this example is RSA.
    */

    private KeyPairGenerator keyPairGenerator;
    private KeyPair keyPair;
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public JavaAsymmetricCryptographyExample(int keyLength) throws NoSuchAlgorithmException {
        this.keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        this.keyPairGenerator.initialize(keyLength);
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, IOException {
        JavaAsymmetricCryptographyExample javaAsymmetricCryptographyExample;

        javaAsymmetricCryptographyExample = new JavaAsymmetricCryptographyExample(1024);
        javaAsymmetricCryptographyExample.createKeys();
        javaAsymmetricCryptographyExample.writeToFile("src/mkyong/publicKey", javaAsymmetricCryptographyExample.getPublicKey().getEncoded());
        javaAsymmetricCryptographyExample.writeToFile("src/mkyong/privateKey", javaAsymmetricCryptographyExample.getPrivateKey().getEncoded());
    }

    public void createKeys(){
        this.keyPair = this.keyPairGenerator.generateKeyPair();
        this.privateKey = keyPair.getPrivate();
        this.publicKey = keyPair.getPublic();
    }

    public PrivateKey getPrivateKey() {
        return privateKey;
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public void writeToFile(String path, byte[] key) throws IOException {
        File f = new File(path);
        f.getParentFile().mkdirs();

        FileOutputStream fos = new FileOutputStream(f);
        fos.write(key);
        fos.flush();
        fos.close();
    }
}
