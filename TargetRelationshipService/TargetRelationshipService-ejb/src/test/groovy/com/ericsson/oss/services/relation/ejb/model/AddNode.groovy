package com.ericsson.oss.services.relation.ejb.model

import org.junit.Rule

import com.ericsson.cds.cdi.support.rule.custom.node.*
import com.ericsson.cds.cdi.support.spock.CdiSpecification
import com.ericsson.oss.itpf.datalayer.dps.DataBucket
import com.ericsson.oss.itpf.datalayer.dps.DataPersistenceService
import com.ericsson.oss.itpf.datalayer.dps.persistence.ManagedObject
import com.ericsson.oss.itpf.datalayer.dps.persistence.PersistenceObject
import com.ericsson.oss.itpf.datalayer.dps.stub.RuntimeConfigurableDps
import com.ericsson.oss.services.relation.ejb.utils.FdnFromTargetTypeGenerator

/**
 * This class is responsible for adding any node for cdi test.
 */
class AddNode extends CdiSpecification implements NodeDataProvider {

    DataPersistenceService dataPersistenceService = getOrResetDps()

    @Rule
    final NodeDataInjector nodeDataInjector = new NodeDataInjector(this, cdiInjectorRule)

    // Radio Node

    @RootNode(nodeName = 'RadioNode_001', ipAddress = '10.0.0.1', properties = [
            @NodeProperty(name = 'neType', value = 'RadioNode')
    ])
    ManagedObject radioNode01

    @RootNode(nodeName = 'RadioNode_002', ipAddress = '10.0.0.2', properties = [
            @NodeProperty(name = 'neType', value = 'RadioNode')
    ])
    ManagedObject radioNode02

    // SGSN-MME Node
    @RootNode(nodeName = 'SGSN-MME_001', ipAddress = '11.0.0.1', properties = [
            @NodeProperty(name = 'neType', value = 'SGSN-MME')
    ])
    ManagedObject sgsnmme01

    @RootNode(nodeName = 'SGSN-MME_002', ipAddress = '11.0.0.2', properties = [
            @NodeProperty(name = 'neType', value = 'SGSN-MME')
    ])
    ManagedObject sgsnmme02

    @RootNode(nodeName = 'SGSN-MME_003', ipAddress = '11.0.0.3', properties = [
            @NodeProperty(name = 'neType', value = 'SGSN-MME')
    ])
    ManagedObject sgsnmme03

    // ERBS Node
    @RootNode(nodeName = 'ERBS_001', ipAddress = '12.0.0.1', properties = [
            @NodeProperty(name = 'neType', value = 'ERBS')
    ])
    ManagedObject erbsNode

    // MGW Node
    @RootNode(nodeName = 'MGW_001', ipAddress = '13.0.0.1', properties = [
            @NodeProperty(name = 'neType', value = 'MGW')
    ])
    ManagedObject mgwNode

    // EPG Node
    @RootNode(nodeName = 'EPG_001', ipAddress = '14.0.0.1', properties = [
            @NodeProperty(name = 'neType', value = 'EPG')
    ])
    ManagedObject epgNode

    /*
     * NodeDataProvider methods to override.
     */

    @Override
    Map<String, Object> getAttributesForMo(final String moFdn) {
        return [:]
    }

    @Override
    List<ManagedObjectData> getAdditionalNodeManagedObjects() {
        return []
    }

    def getOrResetDps() {
        cdiInjectorRule.getService(RuntimeConfigurableDps).build()
    }

    /**
     * Get poIds for all the targets involved in the test.
     * @param nodeNameToTargetType - node name and its target type
     * @return - target is node. node name and their poIds
     */
    Map<String, Long> getTargetNameToPoIds(Map<String, String> nodeNameToTargetType) {

        Map<String, Long> NodeNameToPoIds = [:]
        DataBucket bucket = dataPersistenceService.getLiveBucket()
        nodeNameToTargetType.each {
            def targetType = it.value
            def nodeName = it.key
            def fdn = FdnFromTargetTypeGenerator.getFdn(nodeName, targetType)
            PersistenceObject targetPo = bucket.getPersistenceObjectBuilder().namespace('DPS').type('Target').version('1.0.0').addAttribute('type', targetType).create()
            ManagedObject node = bucket.findMoByFdn(fdn)
            node.setTarget(targetPo)
            NodeNameToPoIds.put(nodeName, node.poId)
        }
        NodeNameToPoIds
    }
}
