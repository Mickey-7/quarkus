package javacodegeeks.test;

import javacodegeeks.DigitalSignature;

public class TestGenerateKeys {
    public static void main(String[] args) {
        String keysPath = "src/javacodegeeks/keys";
        DigitalSignature digitalSignature = new DigitalSignature();
        digitalSignature.storeKeyPairs(keysPath);
        System.out.println("Private and Public Keys generated successfully ..");
    }
}
