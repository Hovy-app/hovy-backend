package com.hovy.backend.utils;

import ee.sk.mid.*;
import ee.sk.mid.rest.dao.MidSessionStatus;
import ee.sk.mid.rest.dao.request.MidAuthenticationRequest;
import ee.sk.mid.rest.dao.response.MidAuthenticationResponse;
import org.hamcrest.Matchers;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class CertUtil {

    public static final String AUTHENTICATION_SESSION_PATH = "/authentication/session/{sessionId}";
    public static final String DEMO_HOST_URL = "https://tsp.demo.sk.ee/mid-api";

    public static X509Certificate fileToX509Certificate(String filePath) {
        try {
            File caCertificateFile = new File(CertUtil.class.getResource(filePath).getFile());
            byte[] certificateBytes = Files.readAllBytes(caCertificateFile.toPath());
            CertificateFactory certFactory = CertificateFactory.getInstance("X.509");
            return (X509Certificate) certFactory.generateCertificate(new ByteArrayInputStream(certificateBytes));
        } catch (CertificateException | IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static MidAuthentication createAndSendAuthentication(MidClient client, String phoneNumber, String nationalIdentityNumber, MidAuthenticationHashToSign authenticationHash) {
        MidAuthenticationRequest request = MidAuthenticationRequest.newBuilder()
                .withPhoneNumber(phoneNumber)
                .withNationalIdentityNumber(nationalIdentityNumber)
                .withDisplayText("Omniva Fake Shop Kurta 3")
                .withHashToSign(authenticationHash)
                .withLanguage(MidLanguage.ENG)
                .build();

        MidAuthenticationResponse response = client.getMobileIdConnector().authenticate(request);
        MidSessionStatus sessionStatus = client.getSessionStatusPoller().fetchFinalSessionStatus(response.getSessionID(), AUTHENTICATION_SESSION_PATH);
        return client.createMobileIdAuthentication(sessionStatus, authenticationHash);
    }

    public static void assertAuthenticationResultValid(MidAuthenticationResult authenticationResult) {
        assertThat(authenticationResult.isValid(), is(true));
        assertThat(authenticationResult.getErrors().isEmpty(), is(true));
        assertAuthenticationIdentityValid(authenticationResult.getAuthenticationIdentity());
    }

    public static void assertAuthenticationIdentityValid(MidAuthenticationIdentity authenticationIdentity) {
        assertThat(authenticationIdentity.getGivenName(), not(isEmptyOrNullString()));
        assertThat(authenticationIdentity.getSurName(), not(isEmptyOrNullString()));
        assertThat(authenticationIdentity.getIdentityCode(), not(isEmptyOrNullString()));
        assertThat(authenticationIdentity.getCountry(), not(isEmptyOrNullString()));
    }

    public static void assertAuthenticationCreated(MidAuthentication authentication, String expectedHashToSignInBase64) {
        assertThat(authentication, is(notNullValue()));
        assertThat(authentication.getResult(), not(isEmptyOrNullString()));
        assertThat(authentication.getSignatureValueInBase64(), not(isEmptyOrNullString()));
        assertThat(authentication.getCertificate(), is(notNullValue()));
        assertThat(authentication.getSignedHashInBase64(), is(expectedHashToSignInBase64));
        assertThat(authentication.getHashType(), Matchers.is( MidHashType.SHA256));
    }

}
