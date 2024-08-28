package com.ericsson.oss.services.relation.ejb.utils

import com.ericsson.oss.services.api.AssociatedTarget

/**
 * Utility class involves retrieving poIds.
 */
class ResponseRetriever {

    /**
     * Retrieve expected response in source and destination poIds.
     * @param targetNameToPoIds - has poIds for every node used in the test.
     * @param expectedResponseInTargetName - expected response in source and destination node name.
     * @return
     */
    static getExpectedResponseInPoIds(Map<String, Long> targetNameToPoIds, Map<String, List<String>> expectedResponseInTargetName) {
        List<AssociatedTarget> expectedAssociatedTargets = []
        expectedResponseInTargetName.keySet().each { sourceTargetName ->
            Long sourcePoId = targetNameToPoIds.get(sourceTargetName)
            expectedResponseInTargetName.get(sourceTargetName).each { destinationTargetName ->
                Long destinationPoId = targetNameToPoIds.get(destinationTargetName)
                expectedAssociatedTargets.add(new AssociatedTarget(sourcePoId, destinationPoId))
            }
        }
        expectedAssociatedTargets
    }
}
