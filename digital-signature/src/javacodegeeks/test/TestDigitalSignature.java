package javacodegeeks.test;

import javacodegeeks.DigitalSignature;

import java.io.File;

public class TestDigitalSignature {
    public static void main(String[] args) {
        String xmlFilePath = "src/javacodegeeks/xml/employee.xml";
        String signedXmlFilePath = "src/javacodegeeks/xml/digitallySignedEmployee.xml";
        String privateKeyFilePath = "src/javacodegeeks/keys/privatekey.key";
        String publicKeyFilePath = "src/javacodegeeks/keys/publickey.key";
        DigitalSignature xmlSig = new DigitalSignature();
        xmlSig.generateXMLDigSig(xmlFilePath, signedXmlFilePath, privateKeyFilePath, publicKeyFilePath);

    }
}
