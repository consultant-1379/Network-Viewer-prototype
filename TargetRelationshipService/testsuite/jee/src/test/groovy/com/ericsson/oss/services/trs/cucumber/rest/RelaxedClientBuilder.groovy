/*
 *  COPYRIGHT Ericsson 2012
 *
 *  The copyright to the computer program(s) herein is the property of
 *  Ericsson Inc. The programs may be used and/or copied only with written
 *  permission from Ericsson Inc. or in accordance with the terms and
 *  conditions stipulated in the agreement/contract under which the
 *  program(s) have been supplied.
 */

package com.ericsson.oss.services.trs.cucumber.rest

import java.security.SecureRandom

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import javax.ws.rs.client.ClientBuilder

class RelaxedClientBuilder {

    static TRUST_ALL = [
            getAcceptedIssuers: { },
            checkClientTrusted: { certs, authType -> },
            checkServerTrusted: { certs, authType -> }
    ] as X509TrustManager

    static NO_VERIFY = [
            verify: { hostname, session -> true }
    ] as HostnameVerifier

    static SSL_CONTEXT = SSLContext.getInstance('SSL')

    static {
        SSL_CONTEXT.init(null, [ TRUST_ALL ] as TrustManager[], new SecureRandom())
    }

    static newClient() {
        ClientBuilder.newBuilder().sslContext(SSL_CONTEXT).hostnameVerifier(NO_VERIFY).build()
    }
}
