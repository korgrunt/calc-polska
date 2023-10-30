# calc-polska
calculatrice a notation pôlonaise invers

# Compile and run 
javac -d target src/**/*.java && java -cp target src.Application

# Specification
Mode of use
java -cp target src.Application --user=<UerModeEnum> --log=<LogModeEnum>
--log=<LOG | REPLAY | NO_LOG> (default NO_LOG)
--user=<LOCAL | REMOTE_SHARED_STACK | REMOTE_NOT_SHARED_STACK | USERS_REMOTE_SHARED_STACK | USERS_REMOTE_NOT_SHARED_STACK> (default LOCAL)

exemple:
javac -d target src/**/*.java && java -cp target src.Application --user=REMOTE_NOT_SHARED_STACK --log=LOG

Information: 

--log=LOG 
Will log all action in a log.txt file
Al log are shared, if a remote user do anything on the stack and a local user do another thing, it will be impossible to 
differenciate action from remote user from action of local user.



$$$$$$$$$
HTTP pour interagir via rowser web
Allow change mode while running program (ex: start as etwork, switch session to local, then, reswith to network)
un return par method,

INIT FULL LOCAL, INIT FULL REMOTE, INIT REPLAY LOCAL, INIT REPLAY REMOTE

pas de break dans les boucle( seulement dans un switch)

// TODO: 


HTTP server if projet C is finish
Create constant class for all message
allow change mode from command instead be forced to restart program