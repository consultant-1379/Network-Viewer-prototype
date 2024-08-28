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

import javax.ws.rs.client.Entity
import javax.ws.rs.core.Response

import org.apache.http.HttpStatus

/**
 * Topology relationship service REST adapter
 * Exposes REST interface of TargetRelationshipService-war
 */
class TopologyRelationshipServiceRestAdapter extends AbstractRestAdapter {

    final static String TRS_ENDPOINT = '/TargetRelationshipService/rest/relation'

    /**
     * Executes a topology relationship service query.
     *
     * @param jsonInput the query string
     * @return String response, success is JSON string, failure is <ERROR CODE> <REASON>, e.g. 404 Not Found
     */
    def topologyRelationshipServiceQuery(String jsonInput) {
        Response response = RelaxedClientBuilder.newClient()
                .target(BASE_URL)
                .path(TRS_ENDPOINT)
                .request()
                //.header('X-Tor-UserId', userId)
                //.cookie('iPlanetDirectoryPro', IPLANET_DIRECTORY_PRO_COOKIE)
                .post(Entity.entity(jsonInput, 'application/json'))

        if (response.status == HttpStatus.SC_CREATED) {
            return response.readEntity(String.class)
        }else {
            return response.status + " " + response.statusInfo
        }
    }
}
