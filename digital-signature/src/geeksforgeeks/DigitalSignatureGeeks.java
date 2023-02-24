package geeksforgeeks;





import javax.xml.bind.DatatypeConverter;
import java.security.*;
public class DigitalSignatureGeeks {
    //Signing Algorithm
    private static final String SIGNING_ALGORITHM = "SHA256withRSA";
    private static final String RSA = "RSA";

    // Driver Code
    public static void main(String[] args) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        String input = "GEEKSFORGEEKS IS A COMPUTER SCIENCE PORTAL";

        KeyPair keyPair = generateRSAKepPair();

        //Function Call
        byte[] signature = createDigSig(input.getBytes(), keyPair.getPrivate());
        System.out.println("Signature Value:\n "+ DatatypeConverter.printHexBinary(signature));

        System.out.println("Verification: "+ verifyDigSig(input.getBytes(),signature, keyPair.getPublic()));

        /*run console output
        Signature Value:
        B492483B5C130482CFFA58723D85B506A4F4EACB004949957CBF14563F21B00B004A1F9EB697F2EDBE8776A314CEE048133A4BBFF2A96C36F20EDFF1ED325C42F17F47AA9E87B246245A2EDB99A5B33318A5A1CB841A3836F6C9BA20368A4C16DBF94CA9BDF460A8DC21D9472AB4BFD8D4D17FEB2B3AA16994B07D8FC3B1D7A611E8EC12D3F4C6C1D04A3489C92021C6FF5CB8F4A46A5753126354BEFFCDB050CF0E0BCA888D02B0991F6BA2D78C522EB51153404C6A9D02674C027767C30FFF8BAB90B8F49C7EEADF7399FEDC9E3ED622414592FE02756D0B3F7F03D4B1C4B4A58C16E254F58749D4C9BF30A83B7739A5871DA026D77C49D3B1EAD6B5E4352C
        Verification: true
        */
    }


    // Function to implement Digital signature
    // using SHA256 and RSA algorithm
    // by passing private key.
    public static byte[] createDigSig(byte[] input, PrivateKey privateKey) throws NoSuchAlgorithmException, SignatureException, InvalidKeyException {
        Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
        signature.initSign(privateKey);
        signature.update(input);
        return signature.sign();
    }

    // Generating the asymmetric key pair
    // using SecureRandom class
    // functions and RSA algorithm.
    public static KeyPair generateRSAKepPair() throws NoSuchAlgorithmException {
        SecureRandom secureRandom = new SecureRandom();
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(RSA);
        keyPairGenerator.initialize(2048,secureRandom);
        return keyPairGenerator.generateKeyPair();
    }

    // Function for Verification of the
    // digital signature by using the public key
    public static boolean verifyDigSig(byte[] input,byte[] signatureToVerify, PublicKey publicKey) throws SignatureException, InvalidKeyException, NoSuchAlgorithmException {
        Signature signature = Signature.getInstance(SIGNING_ALGORITHM);
        signature.initVerify(publicKey);
        signature.update(input);
        return signature.verify(signatureToVerify);
    }
}


