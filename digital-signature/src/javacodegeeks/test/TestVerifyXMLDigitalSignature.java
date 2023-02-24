package javacodegeeks.test;

import javacodegeeks.DigitalSignatureVerifier;

public class TestVerifyXMLDigitalSignature {
    public static void main(String[] args) {
        //Test for Valid one
        testSignedXMLDoc();
    }

    private static void testSignedXMLDoc() {
        String signedXmlFilePath = "src/javacodegeeks/xml/digitallySignedEmployee.xml";
        String publicKeyFilePath = "src/javacodegeeks/keys/publickey.key";
        try {
            boolean validFlag = DigitalSignatureVerifier.isDigitalSignatureValid(signedXmlFilePath, publicKeyFilePath);
            System.out.println("Validity of XML Digital Signature : " + validFlag);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
