# SRProxy
http simple proxy for system where outbound traffic is blocked by firewall of proxy computer, but inbound traffic is open. 
This proxy have two part proxy_server_part and proxy_client_part


### SRProxy
<pre>
                                      Network 1                        Network 2                                            
                                            \                           /                                                   
                                             \                         /                                                    
                                              \                       /                                                     
 __________        _______________________     |                     /    _______________________                           
|          |      | proxy_server_part     |    |                    |    | proxy_client_part     |        _________         
|          |      |    _____________      |    |                    |    |    _____________      |       |         |        
|  Client  |------|-> |server socket|     |    |                    |    |   |client socket|-----|------>|  Server |        
|          |<-----|-- |_____________|     |    |                    |    |   |_____________|<----|-------|         |        
|          |      |    _____^__v____      |    |                    |    |    ____^__V_____      |       |         |        
 ----------       |   |server socket|-----|----|--------------------|----|-->|client socket|     |       |         |        
                  |   |_____________|<----|----|--------------------|----|---|_____________|     |        ---------         
                  |_______________________|    |                    |    |_______________________|                          
                                               |                    |
</pre>
### Normal Proxy
<pre>
                                      Network 1                        Network 2                                            
                                            \                           /                                                   
                                             \                         /                                                    
                                              \                       /                                                     
                                               |                     /    _______________________                           
                                               |                    |    | proxy_server          |        _________         
 __________                                    |                    |    |    _____________      |       |         |        
|          |                                   |                    |    |   |client socket|-----|------>|  Server |        
|          |                                   |                    |    |   |_____________|<----|-------|         |        
|  Client  |                                   |                    |    |    ____v__^_____      |       |         |        
|          |<----------------------------------|--------------------|----|---|server socket|     |       |         |        
|          |-----------------------------------|--------------------|----|-->|_____________|     |        ---------         
 ----------                                    |                    |    |_______________________|                          
                                               |                    |
</pre>

# How to use?

### to start server-side of proxy
`java -jar SRProxy.jar -s -configFile /path/to/config/file`

### to start client-side of proxy
`java -jar SRProxy.jar -c -configFile /path/to/config/file`

### configFile sample
```
#Properties
#Sun Jul 07 18:28:44 CEST 2019
config.logging.enableANSIColor=true
config.proxy_part_type=server
config.sync_port=4041
config.logging.printSocketComunication=false
config.client_port=22
config.bind_timeout=-10000
config.client_host=192.168.110.132
config.host=localhost
config.port=4040
config.access_port=7071
config.access_host=localhost

```
