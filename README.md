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




