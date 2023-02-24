package javacodegeeks;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Collections;

public class DigitalSignature {
    /**
     * Method used to store Private and Public Keys inside a directory
     *
     * @param dirPath to store the keys
     */
    public void storeKeyPairs(String dirPath) {
        KeyPair keyPair = generateKeyPairs();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        storeKeys(dirPath + File.separator + "publickey.key", publicKey);
        storeKeys(dirPath + File.separator + "privatekey.key", privateKey);
    }


    /**
     * Method used to store the key(Public/Private)
     *
     * @param filePath , name of the file
     * @param key
     */
    public void storeKeys(String filePath, Key key) {
        try{
            byte[] keyBytes = key.getEncoded();
            OutputStream os = new FileOutputStream(filePath);
            os.write(keyBytes);
            os.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    /**
     * This method is used to generate key pair based upon the provided
     * algorithm
     *
     * @return KeyPair
     */
    public KeyPair generateKeyPairs() {
        KeyPair keyPair = null;
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(1024);
            keyPair = keyGen.genKeyPair();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return keyPair;
    }


    public void generateXMLDigSig(String xmlFilePath, String xmlDestPath, String privateKeyPath, String publicKeyPath){
        //get xml document
        Document document = getXmlDoc(xmlFilePath);

        //Create XML Signature Factory
        XMLSignatureFactory xmlSignatureFactory = XMLSignatureFactory.getInstance("DOM");
        PrivateKey privateKey = getStoredPrivateKey(privateKeyPath);
        DOMSignContext domSignContext = new DOMSignContext(privateKey,document.getDocumentElement());
        Reference reference = null;
        SignedInfo signedInfo = null;

        try{
            reference = xmlSignatureFactory.newReference(
                    "",
                    xmlSignatureFactory.newDigestMethod(DigestMethod.SHA1,null),
                    Collections.singletonList(xmlSignatureFactory.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)
                    ), null, null
            );

            signedInfo = xmlSignatureFactory.newSignedInfo(
                    xmlSignatureFactory.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE, (C14NMethodParameterSpec) null),
                    xmlSignatureFactory.newSignatureMethod(SignatureMethod.RSA_SHA1,null),
                    Collections.singletonList(reference)
            );

            //Pass the Public Key File Path
            KeyInfo keyInfo = getKeyInfo(xmlSignatureFactory, publicKeyPath);


            //Create a new XML Signature
            XMLSignature xmlSignature = xmlSignatureFactory.newXMLSignature(signedInfo, keyInfo);

            //Sign the document
            xmlSignature.sign(domSignContext);

            //Store the digitally signed document inta a location
            storeSignedDoc(document, xmlDestPath);

        }catch (Exception e){
            e.printStackTrace();
        }
        

    }

    /*
     * Method used to store the signed XMl document
     */
    public void storeSignedDoc(Document document, String xmlDestPath) {
        try{
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            StreamResult streamResult = new StreamResult(new File(xmlDestPath));
            //transformer.setOutputProperty(OutputKeys.INDENT,"yes");
            transformer.transform(new DOMSource(document), streamResult);

        }catch (Exception e){
            e.printStackTrace();
        }
        System.out.println("XML file with attached digital signature generated successfully ...");
    }

    /**
     * Method used to get the KeyInfo
     *
     * @param xmlSigFactory
     * @param publicKeyPath
     * @return KeyInfo
     */
    public KeyInfo getKeyInfo(XMLSignatureFactory xmlSignatureFactory, String publicKeyPath) {
        KeyInfo ki = null;
        try{
            PublicKey publicKey = getStoredPublicKey(publicKeyPath);
            KeyInfoFactory kif = xmlSignatureFactory.getKeyInfoFactory();
            KeyValue keyValue = kif.newKeyValue(publicKey);
            ki = kif.newKeyInfo(Collections.singletonList(keyValue));

        }catch (Exception e){
            e.printStackTrace();
        }
        return ki;
    }


    /**
     * Method used to get the generated Public Key
     *
     * @param filePath of the PublicKey file
     * @return PublicKey
     */
    public PublicKey getStoredPublicKey(String publicKeyPath) {
        PublicKey publicKey = null;
        try{
            byte[] keyData = getKeyData(publicKeyPath);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec encodedKeySpec = new X509EncodedKeySpec(keyData);
            publicKey = keyFactory.generatePublic(encodedKeySpec);
        }catch (Exception e){
            e.printStackTrace();
        }
        return publicKey;
    }

    /**
     * Method used to get the XML document by parsing
     *
     * @param xmlFilePath , file path of the XML document
     * @return Document
     */
    public Document getXmlDoc(String xmlFilePath) {
        try {
            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            dbf.setNamespaceAware(true);
            return dbf.newDocumentBuilder().parse(new FileInputStream(xmlFilePath));

        } catch (ParserConfigurationException | SAXException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method used to get the generated Private Key
     *
     * @param filePath of the PrivateKey file
     * @return PrivateKey
     */
    public PrivateKey getStoredPrivateKey(String filePath){
        PrivateKey privateKey =null;
        try{
            byte[] keyData = getKeyData(filePath);
            PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(keyData);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            privateKey = keyFactory.generatePrivate(encodedKeySpec);
        }catch (Exception e){
            e.printStackTrace();
        }
        return privateKey;
    }


    /**
     * Method used to retrieve the keys in the form byte array
     *
     * @param filePath of the key
     * @return byte array
     */
    public byte[] getKeyData(String filePath) {
        File file = new File(filePath);
        byte[] buffer = new byte[(int) file.length()];
        try{
            FileInputStream fis = new FileInputStream(file);
            fis.read(buffer);
            fis.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return buffer;
    }


}
