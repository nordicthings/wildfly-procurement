#!/bin/bash
/opt/jboss/wildfly/bin/add-user.sh admin admin123! --silent --realm ManagementRealm
exec /opt/jboss/wildfly/bin/standalone.sh -b=0.0.0.0 -bmanagement=0.0.0.0