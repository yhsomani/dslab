IMPORTANT: Execute All Commands on command prompt(if using windows) or terminal(if using ubuntu)
Don't use Windows Powershell.


Commands To Execute Assignment-1:
	On Terminal-1:
		javac *.java
		rmiregistry

	On Terminal-2:
		java Server

	On Terminal-3:
		java Client


---------------------------------------------------------------------------------------------------------

Commands To Execute Assignment-2:

	On Terminal-1: 
		idlj -fall Prime.idl	
		javac *.java PrimeApp/*.java
		orbd -ORBInitialPort 1050&

	On Terminal-2: 
		java Server -ORBInitialPort 1050 -ORBInitialHost localhost&

	On Terminal-3:
		java Client -ORBInitialPort 1050 -ORBInitialHost localhost



---------------------------------------------------------------------------------------------------------

Commands To Execute Assignment-5:

	javac TokenRing.java
	java TokenRing

