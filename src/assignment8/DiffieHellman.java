package assignment8;

import javax.crypto.KeyAgreement;
import javax.crypto.SecretKey;
import javax.crypto.spec.DHParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.*;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Base64;

public class DiffieHellman {

    // Generate key pair for Diffie-Hellman
    public static KeyPair generateKeyPair() throws Exception {
        AlgorithmParameterSpec paramSpec = new DHParameterSpec(new java.math.BigInteger("12345678901234567890"), new java.math.BigInteger("12345"));
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("DH");
        keyPairGenerator.initialize(paramSpec);
        return keyPairGenerator.generateKeyPair();
    }

    // Generate shared secret key
    public static byte[] generateSharedSecret(PrivateKey privateKey, PublicKey publicKey) throws Exception {
        KeyAgreement keyAgree = KeyAgreement.getInstance("DH");
        keyAgree.init(privateKey);
        keyAgree.doPhase(publicKey, true);
        return keyAgree.generateSecret();
    }

    public static void main(String[] args) {
        try {
            // Alice generates her key pair
            KeyPair aliceKeyPair = DiffieHellman.generateKeyPair();

            // Bob generates his key pair
            KeyPair bobKeyPair = DiffieHellman.generateKeyPair();

            // Alice and Bob exchange public keys and generate the shared secret
            byte[] aliceSharedSecret = DiffieHellman.generateSharedSecret(aliceKeyPair.getPrivate(), bobKeyPair.getPublic());
            byte[] bobSharedSecret = DiffieHellman.generateSharedSecret(bobKeyPair.getPrivate(), aliceKeyPair.getPublic());

            // Print the shared secret (should be the same for both parties)
            System.out.println("Alice's Shared Secret: " + Base64.getEncoder().encodeToString(aliceSharedSecret));
            System.out.println("Bob's Shared Secret: " + Base64.getEncoder().encodeToString(bobSharedSecret));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
