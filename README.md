# SRProxy
http simple proxy for system where outbound traffic is blocked by firewall of proxy computer, but inbound traffic is open. 
This proxy have two part proxy_server_part and proxy_client_part



<pre style="line-height: 0.8">
                                      Network 1                        Network 2                                            <br>
                                            \                           /                                                   <br>
                                             \                         /                                                    <br>
                                              \                       /                                                     <br>
 __________        _______________________     |                     /    _______________________                           <br>
|          |      | proxy_server_part     |    |                    |    | proxy_client_part     |        _________         <br>
|          |      |    _____________      |    |                    |    |    _____________      |       |         |        <br>
|  Client  |------|-> |server socket|     |    |                    |    |   |client socket|-----|------>|  Server |        <br>
|          |<-----|-- |_____________|     |    |                    |    |   |_____________|<----|-------|         |        <br>
|          |      |    _____^__v____      |    |                    |    |    ____v__^_____      |       |         |        <br>
 ----------       |   |server socket|<----|----|--------------------|----|---|client socket|     |       |         |        <br>
                  |   |_____________|-----|----|--------------------|----|-->|_____________|     |        ---------         <br>
                  |_______________________|    |                    |    |_______________________|                          <br>
                                               |                    |

</pre>




