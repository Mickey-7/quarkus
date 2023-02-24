package mkyong;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.*;
import java.nio.Buffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Arrays;

import org.apache.commons.codec.binary.Base64;

/*Use the Key Pair to encrypt and decrypt data*/
public class AsymmetricCryptography {
    /*
    * In this example, we create a class that can load the Public and the Private keys
    * from their files and then uses them to encrypt and decrypt a String and a File.
    * To run this example, you need to have run the code above
    * to generate keys or download the source below.
    * */

    private Cipher cipher;

    public AsymmetricCryptography() throws NoSuchPaddingException, NoSuchAlgorithmException {
        this.cipher = Cipher.getInstance("RSA");
    }

    public static void main(String[] args) throws NoSuchPaddingException, NoSuchAlgorithmException, IOException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, InvalidKeySpecException, NoSuchProviderException {
        AsymmetricCryptography ac = new AsymmetricCryptography();
        PrivateKey privateKey = ac.getPrivateKey("src/mkyong/privateKey");
        PublicKey publicKey = ac.getPublicKey("src/mkyong/publicKey");

        String msg = "Cryptography is fun!";
        String encryptMsg = ac.encryptText(msg,privateKey);
        String decryptMsg = ac.decryptText(encryptMsg,publicKey);

        System.out.println("Original Message: " + msg +
                "\nEncrypted Message: " + encryptMsg
                + "\nDecrypted Message: " + decryptMsg);

        /*run console output
        Original Message: Cryptography is fun!
        Encrypted Message: AaEqTnDlG/a9LH/0puSGbF7FFaeHF2NBrmjM3aAtWjji4Hflwvk8qbzahz/0m6Mkum21cSVd5LPzO65MQcYs0OkHuudznUcOzOwRZaLvMuu8RTqh6QsFU7+dKAowbh5F0xMnnThGqBQGoxZBHmjeUQ8ZaVdK3qPf5TJKlckCMn0=
        Decrypted Message: Cryptography is fun!
        */

        /*
        if (new File("KeyPair/text.txt").exists()) {
			ac.encryptFile(ac.getFileInBytes(new File("KeyPair/text.txt")),
				new File("KeyPair/text_encrypted.txt"),privateKey);
			ac.decryptFile(ac.getFileInBytes(new File("KeyPair/text_encrypted.txt")),
				new File("KeyPair/text_decrypted.txt"), publicKey);
		} else {
			System.out.println("Create a file text.txt under folder KeyPair");
		}
		*/

    }

    public PrivateKey getPrivateKey(String filename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }

    public PublicKey getPublicKey(String filename) throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        byte[] keyBytes = Files.readAllBytes(new File(filename).toPath());
        X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePublic(spec);
    }

    public void encryptFile(byte[] input,File output, PrivateKey privateKey) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
        this.cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        writeToFile(output, this.cipher.doFinal(input));
    }

    public void decryptFile(byte[] input,File output, PublicKey publicKey) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, IOException {
        this.cipher.init(Cipher.DECRYPT_MODE, publicKey);
        writeToFile(output, this.cipher.doFinal(input));
    }
    private void writeToFile(File output, byte[] toWrite) throws IOException {
        FileOutputStream fos = new FileOutputStream(output);
        fos.write(toWrite);
        fos.flush();
        fos.close();
    }

    public String encryptText(String msg, PrivateKey privateKey) throws InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        this.cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return Base64.encodeBase64String(cipher.doFinal(msg.getBytes()));
    }

    public String decryptText(String msg, PublicKey publicKey) throws InvalidKeyException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException {
        this.cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return new String(cipher.doFinal(Base64.decodeBase64(msg)));
    }

    public byte[] getFileInBytes(File f) throws IOException {
        FileInputStream fis = new FileInputStream(f);
        byte[] fbytes = new byte[(int) f.length()];
        fis.read(fbytes);
        fis.close();
        return fbytes;
    }
}
