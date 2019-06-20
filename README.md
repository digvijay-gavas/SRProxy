# SRProxy
http simple proxy for system where outbound traffic is blocked by firewall of proxy computer, but inbound traffic is open. 
This proxy have two part proxy_server_part and proxy_client_part



<pre style="line-height: 0.8">
                                      Network 1                        Network 2                                            
                                            \                           /                                                   
                                             \                         /                                                    
                                              \                       /                                                     
 __________        _______________________     |                     /    _______________________                           
|          |      | proxy_server_part     |    |                    |    | proxy_client_part     |        _________         
|          |      |    _____________      |    |                    |    |    _____________      |       |         |        
|  Client  |------|-> |server socket|     |    |                    |    |   |client socket|-----|------>|  Server |        
|          |<-----|-- |_____________|     |    |                    |    |   |_____________|<----|-------|         |        
|          |      |    _____^__v____      |    |                    |    |    ____v__^_____      |       |         |        
 ----------       |   |server socket|<----|----|--------------------|----|---|client socket|     |       |         |        
                  |   |_____________|-----|----|--------------------|----|-->|_____________|     |        ---------         
                  |_______________________|    |                    |    |_______________________|                          
                                               |                    |

</pre>




