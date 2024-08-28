FROM armdocker.rnd.ericsson.se/proj_oss_releases/enm/jboss-dps

COPY standalone-enm.xml $JBOSS_HOME/standalone/configuration/standalone-enm.xml

ENTRYPOINT ["entrypoint.sh"]
CMD ["-IPJ", "-s", "jboss"]