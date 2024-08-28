package com.ericsson.oss.services.relation.ejb.utils

/**
 * This class would generate Fdn for nodes based on the given target type.
 */
class FdnFromTargetTypeGenerator {

    static List CATEGORY_NODE = ["RadioNode", "SGSN-MME", "ERBS", "MGW", "EPG"]

    static String getFdn(String nodeName, String targetType) {
        if (CATEGORY_NODE.contains(targetType)) {
            return "NetworkElement=" + nodeName
        }
    }

}
