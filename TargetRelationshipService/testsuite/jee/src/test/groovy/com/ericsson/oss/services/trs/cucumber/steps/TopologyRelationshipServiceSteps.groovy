package com.ericsson.oss.services.trs.cucumber.steps

import com.ericsson.oss.services.trs.cucumber.rest.TopologyRelationshipServiceRestAdapter

import cucumber.api.DataTable
import cucumber.api.PendingException
import cucumber.api.groovy.EN
import cucumber.api.groovy.Hooks

this.metaClass.mixin(Hooks)
this.metaClass.mixin(EN)

TopologyRelationshipServiceRestAdapter trsRestAdapter = new TopologyRelationshipServiceRestAdapter()
trsRestAdapter.setUserToAdministrator()

World {
    new TestData()
}

Given(~/^the user is "([^"]*)"$/) { String userId ->
    trsRestAdapter.setUserId(userId)
}

When(~/^the ui request is executed with json input '([^']*)'$/) { String inputJson ->
    lastExecutionResult = trsRestAdapter.topologyRelationshipServiceQuery(inputJson)
}

Then(~/^the response is json output '([^']*)'$/) { String jsonOutput ->
    assert lastExecutionResult.toString() == jsonOutput
}


