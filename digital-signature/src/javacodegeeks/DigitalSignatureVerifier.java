package javacodegeeks;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import javax.xml.crypto.dsig.XMLSignature;
import javax.xml.crypto.dsig.XMLSignatureFactory;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import java.security.PublicKey;

public class DigitalSignatureVerifier {
    /**
     * Method used to verify the XML digital signature
     * @param signedXmlFilePath
     * @param publicKeyFilePath
     * @return true or false
     * @throws Exception
     */
    public static boolean isDigitalSignatureValid(String signedXmlFilePath, String publicKeyFilePath) throws Exception {
        boolean validFlag = false;
        Document document = new DigitalSignature().getXmlDoc(signedXmlFilePath);
        NodeList nl = document.getElementsByTagNameNS(XMLSignature.XMLNS,"Signature");
        if (nl.getLength()==0){
            throw new Exception("No XML Digital Signature Found, document is discarded");
        }
        PublicKey publicKey = new DigitalSignature().getStoredPublicKey(publicKeyFilePath);
        DOMValidateContext validateContext = new DOMValidateContext(publicKey,nl.item(0));
        XMLSignatureFactory xmlSignatureFactory = XMLSignatureFactory.getInstance("DOM");
        XMLSignature xmlSignature = xmlSignatureFactory.unmarshalXMLSignature(validateContext);
        validFlag = xmlSignature.validate(validateContext);
        return validFlag;

    }
}
