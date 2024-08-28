package com.ericsson.oss.services.relation.custom;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import com.ericsson.oss.itpf.sdk.core.annotation.EServiceQualifier;
import com.ericsson.oss.services.relation.spi.AssociatedTargetType;
import com.ericsson.oss.services.relation.spi.CustomRelationshipProvider;
import com.ericsson.oss.services.relation.spi.RelationInfo;

/**
 * This is domain-specific code for relation R2S.
 */
@EServiceQualifier("R2S")
@Stateless
public class R2SRelation implements CustomRelationshipProvider {

    @Override
    public RelationInfo getRelationInfo(final String relationType) {
        final RelationInfo relationInfo = new RelationInfo();
        final List<AssociatedTargetType> relatedTargets = new ArrayList<>();
        relatedTargets.add(new AssociatedTargetType("RadioNode", "SGSN-MME"));
        relationInfo.setRelationName("RadioToSgsn-mme");
        relationInfo.setAssociatedTargetTypes(relatedTargets);
        return relationInfo;
    }
}
