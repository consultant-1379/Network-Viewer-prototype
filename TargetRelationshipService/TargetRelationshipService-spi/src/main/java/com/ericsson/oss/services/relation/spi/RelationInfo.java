package com.ericsson.oss.services.relation.spi;

import java.io.Serializable;
import java.util.List;

/**
 * The data class to define relationship and targets involved in relationship.
 */
public class RelationInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String relationName;

    private List<AssociatedTargetType> associatedTargetTypes;

    public String getRelationName() {
        return relationName;
    }

    public void setRelationName(final String relationName) {
        this.relationName = relationName;
    }

    public List<AssociatedTargetType> getAssociatedTargetTypes() {
        return associatedTargetTypes;
    }

    public void setAssociatedTargetTypes(final List<AssociatedTargetType> associatedTargetTypes) {
        this.associatedTargetTypes = associatedTargetTypes;
    }
}
