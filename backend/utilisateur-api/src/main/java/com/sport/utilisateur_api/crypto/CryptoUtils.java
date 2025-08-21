package com.sport.utilisateur_api.crypto;

import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import com.sport.utilisateur_api.utils.ResourcesUtils;
import io.micrometer.common.lang.NonNull;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class CryptoUtils {

private static final String ALGORITHM = "RSA";

private static final Integer KEY_SIZE = 2048;

@NonNull
private final ResourcesUtils keyUtils;

@Value("${app.security.key.public}")
private String publicKeyPath;

@Value("${app.security.key.private}")
private String privateKeyPath;


public KeyPair getKey() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, URISyntaxException {

  InputStream pubFile = keyUtils.getKeysFile(publicKeyPath);
  InputStream privFile = keyUtils.getKeysFile(privateKeyPath);

log.info("Loading public key {}", publicKeyPath);
		byte[] publicBytes = pubFile.readAllBytes();
		log.info("Loading private key {}", privateKeyPath);
		byte[] privateBytes = privFile.readAllBytes();

		KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);

		X509EncodedKeySpec publicSpec = new X509EncodedKeySpec(publicBytes);
		RSAPublicKey publicKey = (RSAPublicKey) keyFactory.generatePublic(publicSpec);

		PKCS8EncodedKeySpec privateSpec = new PKCS8EncodedKeySpec(privateBytes);
		RSAPrivateKey privateKey = (RSAPrivateKey) keyFactory.generatePrivate(privateSpec);

		return new KeyPair(publicKey, privateKey);
	}

	 @Bean // 👈 cette méthode devient le bean KeyPair
  public KeyPair rsaKeyPair() throws Exception {
    try (InputStream pubFile = keyUtils.getKeysFile(publicKeyPath);
        InputStream privFile = keyUtils.getKeysFile(privateKeyPath)) {

      byte[] publicBytes = pubFile.readAllBytes();
      byte[] privateBytes = privFile.readAllBytes();

      KeyFactory kf = KeyFactory.getInstance("RSA");
      var pub = (RSAPublicKey) kf.generatePublic(new X509EncodedKeySpec(publicBytes));
      var pri = (RSAPrivateKey) kf.generatePrivate(new PKCS8EncodedKeySpec(privateBytes));
      return new KeyPair(pub, pri);
    }
  }

}
