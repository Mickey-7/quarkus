/*
The resulting enveloped XML Signature,
 indented and formatted for readability, is as follows:
*/

//<?xml version="1.0" encoding="UTF-8"?>
//<Envelope xmlns="urn:envelope">
//  <Signature xmlns="http://www.w3.org/2000/09/xmldsig#">
//    <SignedInfo>
//      <CanonicalizationMethod
//        Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments"/>
//      <SignatureMethod Algorithm="http://www.w3.org/2000/09/xmldsig#dsa-sha1"/>
//      <Reference URI="">
//        <Transforms>
//          <Transform Algorithm="http://www.w3.org/2000/09/xmldsig#enveloped-signature"/>
//        </Transforms>
//        <DigestMethod Algorithm="http://www.w3.org/2000/09/xmldsig#sha1"/>
//        <DigestValue>uooqbWYa5VCqcJCbuymBKqm17vY=</DigestValue>
//      </Reference>
//    </SignedInfo>
//    <SignatureValue>
//      KedJuTob5gtvYx9qM3k3gm7kbLBwVbEQRl26S2tmXjqNND7MRGtoew==
//    </SignatureValue>
//    <KeyInfo>
//      <KeyValue>
//        <DSAKeyValue>
//          <P>
//            /KaCzo4Syrom78z3EQ5SbbB4sF7ey80etKII864WF64B81uRpH5t9jQTxe
//            Eu0ImbzRMqzVDZkVG9xD7nN1kuFw==
//          </P>
//          <Q>li7dzDacuo67Jg7mtqEm2TRuOMU=</Q>
//          <G>
//            Z4Rxsnqc9E7pGknFFH2xqaryRPBaQ01khpMdLRQnG541Awtx/
//            XPaF5Bpsy4pNWMOHCBiNU0NogpsQW5QvnlMpA==
//          </G>
//          <Y>
//            qV38IqrWJG0V/mZQvRVi1OHw9Zj84nDC4jO8P0axi1gb6d+475yhMjSc/
//            BrIVC58W3ydbkK+Ri4OKbaRZlYeRA==
//          </Y>
//        </DSAKeyValue>
//      </KeyValue>
//    </KeyInfo>
//  </Signature>
//</Envelope>
//
/*

The Signature element has been inserted inside the content that it is signing,
thereby making it an enveloped signature.
The required SignedInfo element contains the information that is actually signed:
*/



//<SignedInfo>
//  <CanonicalizationMethod
//    Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments"/>
//  <SignatureMethod Algorithm="http://www.w3.org/2000/09/xmldsig#dsa-sha1"/>
//  <Reference URI="">
//    <Transforms>
//      <Transform Algorithm="http://www.w3.org/2000/09/xmldsig#enveloped-signature"/>
//    </Transforms>
//    <DigestMethod Algorithm="http://www.w3.org/2000/09/xmldsig#sha1"/>
//    <DigestValue>uooqbWYa5VCqcJCbuymBKqm17vY=</DigestValue>
//  </Reference>
//</SignedInfo>
//
/*
The required CanonicalizationMethod element defines the algorithm used
to canonicalize the SignedInfo element before it is signed or validated.
Canonicalization is the process of converting XML content to a canonical form,
to take into account changes that can invalidate a signature over that data.
Canonicalization is necessary due to the nature of XML and the way it is parsed
by different processors and intermediaries, which can change the data such that
the signature is no longer valid but the signed data is still logically equivalent.

The required SignatureMethod element defines the digital signature algorithm
used to generate the signature, in this case DSA with SHA-1.

One or more Reference elements identify the data that is digested.
Each Reference element identifies the data via a URI.
In this example, the value of the URI is the empty String (""),
which indicates the root of the document.
The optional Transforms element contains a list of one or more Transform elements,
each of which describes a transformation algorithm used to transform the data before it is digested.
In this example, there is one Transform element for the enveloped transform algorithm.
The enveloped transform is required for enveloped signatures
so that the signature element itself is removed before calculating the signature value.

The required DigestMethod element defines the algorithm used to digest the data, in this case SHA1.

Finally the required DigestValue element contains the actual base64-encoded digested value.

The required SignatureValue element contains the base64-encoded
signature value of the signature over the SignedInfo element.

The optional KeyInfo element contains information about the key
that is needed to validate the signature:
*/
//
//<KeyInfo>
//  <KeyValue>
//    <DSAKeyValue>
//      <P>
//        /KaCzo4Syrom78z3EQ5SbbB4sF7ey80etKII864WF64B81uRpH5t9jQTxe
//        Eu0ImbzRMqzVDZkVG9xD7nN1kuFw==
//      </P>
//      <Q>li7dzDacuo67Jg7mtqEm2TRuOMU=</Q>
//      <G>
//        Z4Rxsnqc9E7pGknFFH2xqaryRPBaQ01khpMdLRQnG541Awtx/
//        XPaF5Bpsy4pNWMOHCBiNU0NogpsQW5QvnlMpA==
//      </G>
//      <Y>
//        qV38IqrWJG0V/mZQvRVi1OHw9Zj84nDC4jO8P0axi1gb6d+475yhMjSc/
//        BrIVC58W3ydbkK+Ri4OKbaRZlYeRA==
//      </Y>
//    </DSAKeyValue>
//  </KeyValue>
//</KeyInfo>
//
/*
This KeyInfo element contains a KeyValue element,
which in turn contains a DSAKeyValue element consisting
of the public key needed to validate the signature.
KeyInfo can contain various content such as X.509 certificates and PGP key identifiers.
See the KeyInfo section of the XML Signature Recommendation
for more information on the different KeyInfo types.
*/
//
//
//
/* Generating an XML Signature
This example shows you how to generate an XML Signature using the XML Digital Signature API.
More specifically, the example generates an enveloped XML Signature of an XML document.
An enveloped signature is a signature that is contained inside the content that it is signing.
The example uses DOM (the Document Object Model) to parse the XML document
to be signed and a JSR 105 DOM implementation to generate the resulting signature.

A basic knowledge of XML Signatures and their different components is helpful
for understanding this section. See http://www.w3.org/TR/xmldsig-core/ for more information.

*/

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.crypto.KeySelector;
import javax.xml.crypto.MarshalException;
import javax.xml.crypto.dsig.*;
import javax.xml.crypto.dsig.dom.DOMSignContext;
import javax.xml.crypto.dsig.dom.DOMValidateContext;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;
import javax.xml.crypto.dsig.keyinfo.KeyInfoFactory;
import javax.xml.crypto.dsig.keyinfo.KeyValue;
import javax.xml.crypto.dsig.spec.C14NMethodParameterSpec;
import javax.xml.crypto.dsig.spec.TransformParameterSpec;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.security.*;
import java.util.Base64;
import java.util.Collections;

public class XMLDigitalSignatureAPI {
    public static void main(String[] args) throws Exception {
        //Instantiating the Document to be Signed
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        DocumentBuilder docBuilder = dbf.newDocumentBuilder();
        Document doc = docBuilder.newDocument();

        Element rootElem = doc.createElement("root");
        doc.appendChild(rootElem);

        //Creating a Public Key Pair
        //We generate a public key pair.
        //Later in the example, we will use the private key to generate the signature.
        //We create the key pair with a KeyPairGenerator.
        //In this example, we will create a DSA KeyPair with a length of 512 bytes :
        //
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("DSA");
        kpg.initialize(512);
        KeyPair kp = kpg.generateKeyPair();

        //Creating a Signing Context
        //We create an XML Digital Signature XMLSignContext
        //containing input parameters for generating the signature.
        //Since we are using DOM, we instantiate a DOMSignContext (a subclass of XMLSignContext),
        //and pass it two parameters, the private key that will be used to sign the document
        //and the root of the document to be signed:
        DOMSignContext dsc = new DOMSignContext(kp.getPrivate(),doc.getDocumentElement());

        //Assembling the XML Signature
        //We assemble the different parts of the Signature element into an XMLSignature object.
        //These objects are all created and assembled using an XMLSignatureFactory object.
        //An application obtains a DOM implementation of
        //XMLSignatureFactory by calling the following line of code:
        XMLSignatureFactory xmlSigFac = XMLSignatureFactory.getInstance("DOM");
        //We then invoke various factory methods to create the different parts of the XMLSignature object as shown below.
        //We create a Reference object, passing to it the following:
        //The URI of the object to be signed (We specify a URI of "", which implies the root of the document.)
        //The DigestMethod (we use SHA1)
        //A single Transform, the enveloped Transform, which is required for enveloped signatures
        //so that the signature itself is removed before calculating the signature value
        Reference reference = xmlSigFac.newReference(
                "",
                xmlSigFac.newDigestMethod(DigestMethod.SHA1,null),
                Collections.singletonList(xmlSigFac.newTransform(Transform.ENVELOPED, (TransformParameterSpec) null)),
                null,
                null

        );

        //Next, we create the SignedInfo object, which is the object that is actually signed, as shown below.
        //When creating the SignedInfo, we pass as parameters:
        //The CanonicalizationMethod (we use inclusive and preserve comments)
        //The SignatureMethod (we use DSA)
        //A list of References (in this case, only one)
        SignedInfo si = xmlSigFac.newSignedInfo(
                xmlSigFac.newCanonicalizationMethod(CanonicalizationMethod.INCLUSIVE_WITH_COMMENTS, (C14NMethodParameterSpec) null),
                xmlSigFac.newSignatureMethod(SignatureMethod.DSA_SHA1,null),
                Collections.singletonList(reference)
        );

        //Next, we create the optional KeyInfo object, which contains information
        //that enables the recipient to find the key needed to validate the signature.
        //In this example, we add a KeyValue object containing the public key.
        //To create KeyInfo and its various subtypes, we use a KeyInfoFactory object,
        //which can be obtained by invoking the getKeyInfoFactory method
        //of the XMLSignatureFactory, as follows:
        KeyInfoFactory kif = xmlSigFac.getKeyInfoFactory();
        //We then use the KeyInfoFactory to create the KeyValue object and add it to a KeyInfo object:
        KeyValue kv = kif.newKeyValue(kp.getPublic());
        KeyInfo ki = kif.newKeyInfo(Collections.singletonList(kv));

        //Finally, we create the XMLSignature object,
        //passing as parameters the SignedInfo and KeyInfo objects that we created earlier:
        XMLSignature signature = xmlSigFac.newXMLSignature(si, ki);

        //Generating the XML Signature
        //Now we are ready to generate the signature,
        //which we do by invoking the sign method on the XMLSignature object,
        //and pass it the signing context as follows:
        //
        signature.sign(dsc);

        //Printing or Displaying the Resulting Document
        //You can use the following code to print the resulting signed document
        //to a file or standard output:
        //
        OutputStream os;
        if (args.length > 1) {
          os = new FileOutputStream(args[1]);
        } else {
          os = System.out;
        }
        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer trans = tf.newTransformer();
        trans.setOutputProperty(OutputKeys.INDENT,"yes");
        trans.transform(new DOMSource(doc), new StreamResult(os));



        //Validating an XML Signature
        //This example shows you how to validate an XML Signature using the JSR 105 API.
        //The example uses DOM (the Document Object Model) to parse an XML document
        //containing a Signature element and a JSR 105 DOM implementation to validate the signature.

        //Instantiating the Document from above that Contains the Signature - doc
        Document docValid =doc;

        //Specifying the Signature Element to be Validated
        //We need to specify the Signature element that we want to validate,
        //since there could be more than one in the document.
        //We use the DOM method Document.getElementsByTagNameNS,
        //passing it the XML Signature namespace URI and the tag name of the Signature element,
        //as shown:
        NodeList nl = docValid.getElementsByTagNameNS(XMLSignature.XMLNS,"Signature");
        if (nl.getLength() == 0){
            throw new Exception("cannot find Signature element");
        }

        //Creating a Validation Context
        //We create an XMLValidateContext instance containing input parameters for validating the signature.
        //Since we are using DOM, we instantiate a DOMValidateContext instance (a subclass of XMLValidateContext),
        //and pass it two parameters, a KeyValueKeySelector object and
        //a reference to the Signature element to be validated (which is the first entry of the NodeList we generated earlier):
        DOMValidateContext validateContext = new DOMValidateContext(KeySelector.singletonKeySelector(kv.getPublicKey()), nl.item(0));

        //Unmarshaling the XML Signature
        //We extract the contents of the Signature element into an XMLSignature object.
        //This process is called unmarshalling. The Signature element is unmarshalled using an XMLSignatureFactory object.
        //An application can obtain a DOM implementation of XMLSignatureFactory by calling the following line of code:
        XMLSignatureFactory xmlSigFacValid = XMLSignatureFactory.getInstance("DOM");
        //We then invoke the unmarshalXMLSignature method of the factory to unmarshal an XMLSignature object,
        //and pass it the validation context we created earlier:
        XMLSignature signatureValid = xmlSigFacValid.unmarshalXMLSignature(validateContext);

        //Validating the XML Signature
        boolean coreValidity = signatureValid.validate(validateContext);
        System.out.println("\nvalidate");
        System.out.println(coreValidity);
    }
}

//run console output:
//
//<?xml version="1.0" encoding="UTF-8" standalone="no"?>
//<root>
//    <Signature xmlns="http://www.w3.org/2000/09/xmldsig#">
//        <SignedInfo>
//            <CanonicalizationMethod Algorithm="http://www.w3.org/TR/2001/REC-xml-c14n-20010315#WithComments"/>
//            <SignatureMethod Algorithm="http://www.w3.org/2000/09/xmldsig#dsa-sha1"/>
//            <Reference URI="">
//                <Transforms>
//                    <Transform Algorithm="http://www.w3.org/2000/09/xmldsig#enveloped-signature"/>
//                </Transforms>
//                <DigestMethod Algorithm="http://www.w3.org/2000/09/xmldsig#sha1"/>
//                <DigestValue>radiotelegrapg==</DigestValue>
//            </Reference>
//        </SignedInfo>
//        <SignatureValue>jOooGmebBY1Tvs6JVEJi07q48mgjkMlwyCEE+Zq0nEwqL7u+qgT1cw==</SignatureValue>
//        <KeyInfo>
//            <KeyValue>
//                <DSAKeyValue>
//                    <P>/KaCzo4Syrom78z3EQ5SbbB4sF7ey80etKII864WF64B81uRpH5t9jQTxeEu0ImbzRMqzVDZkVG9&#13;
//xD7nN1kuFw==</P>
//                    <Q>li7dzDacuo67Jg7mtqEm2TRuOMU=</Q>
//                    <G>Z4Rxsnqc9E7pGknFFH2xqaryRPBaQ01khpMdLRQnG541Awtx/XPaF5Bpsy4pNWMOHCBiNU0Nogps&#13;
//QW5QvnlMpA==</G>
//                    <Y>cT8CqJwnFs3M6Q+VQvfmn2H7oiXPJC3IK4l6vJoJhayMI8U4cSEiFG26GfFk26iszUZ2F51RAYLV&#13;
//4D3rkkNc8Q==</Y>
//                </DSAKeyValue>
//            </KeyValue>
//        </KeyInfo>
//    </Signature>
//</root>
//
//validate
//true