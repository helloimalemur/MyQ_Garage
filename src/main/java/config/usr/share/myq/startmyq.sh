#!/bin/bash
#pkill -xf "/bin/bash /home/foxx/scripts/startmyq.sh"
pkill -xf "python3 src/garagecontrollistener.py"
pkill -xf "CheckHost Main"
echo "processes stopped.."
cd /usr/share/myq/
#exec -a 'CheckHost' java -cp /usr/share/myq/ Main

exec -a 'CheckHost' /usr/share/myq/myq/bin/Myq_Garage
#exec -a 'CheckHost' /usr/share/myq/Myq_Garage


