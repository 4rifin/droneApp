# task Drone Api

I. Technology
– Java 1.8
– Maven 3.3.3
- DB Postgresql 4.28
- use curl for running service


---------------------------------
* clone project in git lab (https://github.com/4rifin/droneApp.git)
* Import project in IDE Sts
* Import database (DB_DRONES) in postgresgl 

---------------------------------
The service should allow:
* registering a drone; -> curl -d @addNewDrone01.json -H “Content-Type: application/json”  http:/localhost:8020/app/drone/add
* loading a drone with medication items; -> curl -d @addLoadingDroneWithMedication01.json -H “Content-Type: application/json”  http:/localhost:8020/app/packet/add
* checking loaded medication items for a given drone; -> curl http:/localhost:8020/app/packetGetAll / curl http:/localhost:8020/app/packet/packetCode/{packetCode}
* checking available drones for loading; -> curl http:/localhost:8020/app/drone/state/{stateCode}
* check drone battery level for a given drone; -> curl http:/localhost:8020/app/drone/battery/from/{levelA}/to/{levelB}

