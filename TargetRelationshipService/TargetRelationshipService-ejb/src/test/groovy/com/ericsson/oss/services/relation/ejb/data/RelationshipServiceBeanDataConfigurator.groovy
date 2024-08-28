package com.ericsson.oss.services.relation.ejb.data

/**
 * This class is used to set up data for RelationshipServiceBeanSpec.
 * Single_Relation_Simple - to test single relation between two nodes, when only two nodes were given.
 * Single_Relation_Medium - to test single relation between two nodes, when more than two nodes were given.
 */
class RelationshipServiceBeanDataConfigurator {

    static getTargetNameToTargetType(useCase) {

        def useCaseToRequest = [
                "No_Targets_In_Relation"    : ["EPG_001": "EPG", "MGW_001": "MGW"],
                "Single_R2SRelation_Simple" : ["RadioNode_001": "RadioNode", "SGSN-MME_001": "SGSN-MME"],
                "Single_R2SRelation_Medium" : ["RadioNode_001": "RadioNode", "SGSN-MME_001": "SGSN-MME", "ERBS_001": "ERBS"],
                "Single_R2SRelation_Complex": ["RadioNode_001": "RadioNode", "SGSN-MME_001": "SGSN-MME", "ERBS_001": "ERBS",
                                               "RadioNode_002": "RadioNode", "SGSN-MME_002": "SGSN-MME", "SGSN-MME_003": "SGSN-MME"]]
        useCaseToRequest.get(useCase)
    }

    static getExpectedAssociatedTargets(useCase, relationType) {
        def useCaseToExpectedResponse = [
                "No_Targets_In_Relation"    : [:],
                "Single_R2SRelation_Simple" : ["R2S": ["RadioNode_001": ["SGSN-MME_001"]]],
                "Single_R2SRelation_Medium" : ["R2S": ["RadioNode_001": ["SGSN-MME_001"]]],
                "Single_R2SRelation_Complex": ["R2S": ["RadioNode_001": ["SGSN-MME_001", "SGSN-MME_002", "SGSN-MME_003"],
                                                       "RadioNode_002": ["SGSN-MME_001", "SGSN-MME_002", "SGSN-MME_003"]]]]
        useCaseToExpectedResponse.get(useCase).get(relationType)
    }
}
