package kodejava;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;

public class GenerateDigitalSignature {
    public static void main(String[] args) {
        try{
            // Get instance and initialize a KeyPairGenerator object.
            KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA","SUN");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG","SUN");
            keyPairGen.initialize(1024,secureRandom);

            //Get a PrivateKey from the generated key pair.
            KeyPair keyPair = keyPairGen.generateKeyPair();
            PrivateKey privateKey = keyPair.getPrivate();

            //Get an instance of Signature object and initialize it
            Signature signature = Signature.getInstance("SHA1withDSA","SUN");
            signature.initSign(privateKey);

            //Supply the data to be signed to the Signature object
            //using the update() method and generate the digital signature
            byte[] bytes = Files.readAllBytes(Paths.get("src/kodejava/README.md"));
            signature.update(bytes);
            byte[] digitalSignature = signature.sign();

            //Save digital signature and public key to a file
            Files.write(Paths.get("src/kodejava/signature"), digitalSignature);
            Files.write(Paths.get("src/kodejava/publickey"), keyPair.getPublic().getEncoded());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
