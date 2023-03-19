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
-------------

Constant state in table master_drone
         IDLE("idle", 1), 
	LOADING("loading", 2), 
	LOADED("loaded", 3),
	DELIVERING("delivering", 4), 
	DELIVERED("delivered", 5),
	RETURNING("returning", 6);

Constant status in table trans_packet 
         READY("ready", 1), 
	PROGRESS("progress", 2), 
	DONE("done", 3),
	RETURN("return", 4);

-----------------
additional 

-Scheduler for update state and status in table packet
1. Scheduller Update Packet everyMinutes 
2. Scheduller Update Packet twoMinutes
3. Scheduller Update Packet threeMinutes

*. note scheduler :
 1. - Collect Packet with status Ready and state Drone Loading and update to Packet with status Progress and state Drone Loaded
     - Collect Packet with status Progress and state Drone Loaded and update to Packet with status In Progress and state Drone Delivering
 2. - Collect Packet with status Progress and state Drone Delivering and update to Packet with status Done and state Drone Delivered
 3. - Collect Packet with status Done and state Drone Delivered and update  state Drone Idle and Battery capacity reduce 10%
 
 ------------------
- add a medication; -> curl -d @addNewMedication01.json -H “Content-Type: application/json”  http:/localhost:8020/app/medication/add
