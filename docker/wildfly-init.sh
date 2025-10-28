#!/bin/bash
set -e

# Management-User anlegen (sofern der noch nicht existiert)
if ! grep -q "^admin=" /opt/jboss/wildfly/standalone/configuration/mgmt-users.properties; then
  /opt/jboss/wildfly/bin/add-user.sh admin admin123! --silent --realm ManagementRealm
fi

# WildFly starten im Hintergrund, um CLI auszuführen
/opt/jboss/wildfly/bin/standalone.sh -b=0.0.0.0 -bmanagement=0.0.0.0 &
WILDFLY_PID=$!

echo "----- Waiting for WildFly to start... -----"
until curl -s http://localhost:9990/management > /dev/null; do
  sleep 1
done

echo "----- Running CLI Datasource configuration... -----"
/opt/jboss/wildfly/bin/jboss-cli.sh --connect --file=/opt/jboss/wildfly/configure-datasource.cli

echo "----- CLI configuration done. Bringing server to foreground... -----"
wait $WILDFLY_PID