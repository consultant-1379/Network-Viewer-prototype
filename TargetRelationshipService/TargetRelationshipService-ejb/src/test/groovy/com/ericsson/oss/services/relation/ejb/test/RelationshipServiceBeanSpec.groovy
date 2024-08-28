package com.ericsson.oss.services.relation.ejb.test

import com.ericsson.cds.cdi.support.rule.ImplementationClasses
import com.ericsson.cds.cdi.support.rule.MockedImplementation
import com.ericsson.cds.cdi.support.rule.ObjectUnderTest
import com.ericsson.oss.itpf.sdk.core.EServiceNotFoundException
import com.ericsson.oss.services.api.RelationRequest
import com.ericsson.oss.services.api.RelationResponse
import com.ericsson.oss.services.relation.custom.R2SRelation
import com.ericsson.oss.services.relation.ejb.CustomRelationshipClient
import com.ericsson.oss.services.relation.ejb.RelationshipServiceBean
import com.ericsson.oss.services.relation.ejb.data.RelationshipServiceBeanDataConfigurator
import com.ericsson.oss.services.relation.ejb.model.AddNode
import com.ericsson.oss.services.relation.ejb.utils.ResponseRetriever

/**
 * All tests for relationship service.
 */
class RelationshipServiceBeanSpec extends AddNode {

    @ObjectUnderTest
    RelationshipServiceBean objUnderTest

    @ImplementationClasses
            myClasses = [R2SRelation]

    @MockedImplementation
    CustomRelationshipClient customRelationshipClient

    def 'get relationship details for the given relationship types and targets'() {
        given: 'list of relationship types and list of targets'
            relationshipTypes
            Map<String, Long> targetNameToPoIds = getTargetNameToPoIds(useCase)
            List<Long> targets = getTargets(targetNameToPoIds)
        and: 'create request'
            RelationRequest request = new RelationRequest(relationTypes: relationshipTypes, poIds: targets)
        and: 'initialize implementation for custom code'
            getCustomImplementation(relationshipTypes, customRelationshipClient)
        when: 'get relationship info is invoked'
            getOrResetDps()
            RelationResponse response = objUnderTest.getRelationInfoForTargets(request)
        then: 'correct relation is found'
            with(response.relationTypeToTargets) {
                (keySet() as List).containsAll(expectedRelationTypes)
                expectedRelationTypes.every { relationType ->
                    def expectedAssociatedTargets = RelationshipServiceBeanDataConfigurator.getExpectedAssociatedTargets(useCase, relationType)
                    def expectedAssociatedPoIds = ResponseRetriever.getExpectedResponseInPoIds(targetNameToPoIds, expectedAssociatedTargets)
                    expectedAssociatedPoIds.sourcePoId == get(relationType).sourcePoId &&
                            expectedAssociatedPoIds.destinationPoId == get(relationType).destinationPoId
                }
            }
        where:
            relationshipTypes | useCase                      | expectedRelationTypes
            ["R2S"]           | "No_Targets_In_Relation"     | []
            ["R2S", "DUMMY"]  | "Single_R2SRelation_Simple"  | ["R2S"]
            ["R2S", "DUMMY"]  | "Single_R2SRelation_Medium"  | ["R2S"]
            ["R2S", "DUMMY"]  | "Single_R2SRelation_Complex" | ["R2S"]

    }

    def getCustomImplementation(List<String> relationTypes, CustomRelationshipClient customRelationshipClient) {
        relationTypes.each {
            if (it == "R2S") {
                customRelationshipClient.getCustomRelationshipBean(it) >> new R2SRelation()
            } else {
                customRelationshipClient.getCustomRelationshipBean(it) >> { throw new EServiceNotFoundException() }
            }
        }
    }

    List<Long> getTargets(Map<String, Long> targetNamesToPoIds) {
        targetNamesToPoIds.values() as List
    }

    Map<String, Long> getTargetNameToPoIds(String useCase) {
        Map<String, String> targetNameToTargetType = RelationshipServiceBeanDataConfigurator.getTargetNameToTargetType(useCase)
        getTargetNameToPoIds(targetNameToTargetType)
    }
}
