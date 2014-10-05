package com.lol.lolpro.app;

import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;

import javax.net.ssl.TrustManager;
import javax.net.ssl.TrustManagerFactory;
import javax.net.ssl.X509TrustManager;

/**
 * Created by sergioiglesias on 04/10/14.
 */
public class EasyX509TrustManager implements X509TrustManager {
    private X509TrustManager standardTrustManager = null;
    private X509TrustManager customTrustManager = null;

    /**
     * Constructor for EasyX509TrustManager.
     */
    public EasyX509TrustManager(KeyStore cKeyStore) throws NoSuchAlgorithmException, KeyStoreException {
        super();
        KeyStore noKeyStore = null;
        TrustManagerFactory factory = TrustManagerFactory.getInstance(TrustManagerFactory.getDefaultAlgorithm());
        factory.init(noKeyStore);
        TrustManager[] trustManagers = factory.getTrustManagers();
        if (trustManagers.length == 0) {
            throw new NoSuchAlgorithmException("no trust manager found");
        }
        //Get default TrustManager on older devices
        this.standardTrustManager = (X509TrustManager) trustManagers[0];

        //Use custom TrustManager
        factory.init(cKeyStore);
        trustManagers = factory.getTrustManagers();
        this.customTrustManager = (X509TrustManager) trustManagers[0];
    }

    /**
     * @see javax.net.ssl.X509TrustManager#checkClientTrusted(X509Certificate[], String authType)
     */
    public void checkClientTrusted(X509Certificate[] certificates, String authType) throws CertificateException {
        try {
            customTrustManager.checkServerTrusted(certificates, authType);
        } catch (CertificateException ce) {
            standardTrustManager.checkServerTrusted(certificates, authType);
        }
    }

    /**
     * @see javax.net.ssl.X509TrustManager#checkServerTrusted(X509Certificate[], String authType)
     */
    public void checkServerTrusted(X509Certificate[] certificates, String authType) throws CertificateException {
        // Clean up the certificates chain and build a new one.
        // Theoretically, we shouldn't have to do this, but various web servers
        // in practice are mis-configured to have out-of-order certificates or
        // expired self-issued root certificate.
        int chainLength = certificates.length;
        if (chainLength > 1) {
            // 1. we clean the received certificates chain.
            // We start from the end-entity certificate, tracing down by matching
            // the "issuer" field and "subject" field until we can't continue.
            // This helps when the certificates are out of order or
            // some certificates are not related to the site.
            int currIndex;
            boolean foundNext;
            for (currIndex = 0; currIndex < chainLength; ++currIndex) {
                foundNext = false;
                for (int nextIndex = currIndex + 1; nextIndex < chainLength; ++nextIndex) {
                    if (certificates[currIndex].getIssuerDN().equals(certificates[nextIndex].getSubjectDN())) {
                        foundNext = true;
                        // Exchange certificates so that 0 through currIndex + 1 are in proper order
                        if (nextIndex != currIndex + 1) {
                            X509Certificate tempCertificate = certificates[nextIndex];
                            certificates[nextIndex] = certificates[currIndex + 1];
                            certificates[currIndex + 1] = tempCertificate;
                        }
                        break;
                    }
                }
                if (!foundNext) break;
            }

            // 2. we exam if the last traced certificate is self issued and it is expired.
            // If so, we drop it and pass the rest to checkServerTrusted(), hoping we might
            // have a similar but unexpired trusted root.
            chainLength = currIndex + 1;
            X509Certificate lastCertificate = certificates[chainLength - 1];
            Date now = new Date();
            if (lastCertificate.getSubjectDN().equals(lastCertificate.getIssuerDN())
                    && now.after(lastCertificate.getNotAfter())) {
                --chainLength;
            }
        }

        try {
            customTrustManager.checkServerTrusted(certificates, authType);
        } catch (CertificateException ce) {
            standardTrustManager.checkServerTrusted(certificates, authType);
        }
    }

    /**
     * @see javax.net.ssl.X509TrustManager#getAcceptedIssuers()
     */
    public X509Certificate[] getAcceptedIssuers() {
        return this.standardTrustManager.getAcceptedIssuers();
    }
}
