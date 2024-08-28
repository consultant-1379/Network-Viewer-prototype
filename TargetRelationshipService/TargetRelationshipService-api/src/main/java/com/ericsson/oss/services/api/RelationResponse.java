package com.ericsson.oss.services.api;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The data class to define response for relationship service.
 */
@XmlRootElement
public class RelationResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    @XmlElement
    private Map<String, List<AssociatedTarget>> relationTypeToTargets = new HashMap<>();

    public void setRelationTypeToTargets(final Map<String, List<AssociatedTarget>> relationTypeToTargets) {
        this.relationTypeToTargets = relationTypeToTargets;
    }

    public Map<String, List<AssociatedTarget>> getRelationTypeToTargets() {
        return relationTypeToTargets;
    }

    public void addRelation(final String relationType, final Long source, final Long destination) {
        if (relationTypeToTargets.containsKey(relationType)) {
            relationTypeToTargets.get(relationType).add(new AssociatedTarget(source, destination));
        } else {

            final List<AssociatedTarget> relations = new ArrayList<>();
            relations.add(new AssociatedTarget(source, destination));
            relationTypeToTargets.put(relationType, relations);
        }
    }
}
