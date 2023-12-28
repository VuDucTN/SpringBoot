package example.springProject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.http.HttpService;
import org.web3j.protocol.websocket.WebSocketClient;
import org.web3j.protocol.websocket.WebSocketService;
import org.web3j.tx.gas.ContractGasProvider;
import org.web3j.tx.gas.StaticGasProvider;

import java.math.BigInteger;
import java.net.URI;

@Configuration
public class Web3jConfig {
    @Bean
    public Web3j web3j() throws Exception {
//        return Web3j.build(new HttpService( "https://goerli.infura.io/v3/f25dd7378899465bb7720a1c3d408ed5"));
        WebSocketService webSocketService = new WebSocketService("wss://goerli.infura.io/ws/v3/f25dd7378899465bb7720a1c3d408ed5", false);
        webSocketService.connect();
        return  Web3j.build(webSocketService);
    }

    @Bean
    public ContractGasProvider contractGasProvider() {
        return new StaticGasProvider(
                BigInteger.valueOf(200_000_000L), // Gas price
                BigInteger.valueOf(8_000_000) // Gas limit
        );
    }
}
