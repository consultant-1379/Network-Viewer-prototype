Feature: TRS rest interface POST test

  Scenario: The post is called with dummy data.
    #Given the user is "Network_Explorer_Administrator_User"
    When the ui request is executed with json input '{"relationTypes" : ["X2","R2S"],"poIds" : ["281474979272852", "281474979464867", "281474979339737"]}'
    Then the response is json output '{"relationTypeToTargets":{"R2S":[]}}'

