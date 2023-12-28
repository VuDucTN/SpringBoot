package example.springProject.controller;

import example.springProject.contracts.ProductRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tx.gas.ContractGasProvider;

import java.math.BigInteger;
import java.util.Map;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("api/contract")
public class ContractController {

    @Autowired
    private Web3j web3j;  // Inject Web3j instance

    @Autowired
    private ContractGasProvider contractGasProvider; // Inject gas provider

    private Credentials retrieveCredentialsSecurely() {
        String privateKey = "f3f7dbca1d09f8fe7aaf1f9221a973533089f743a925d0daef388a8b09529609";
        return Credentials.create(privateKey);
    }

    private String contractAddress;


    @GetMapping("/deploy")
    public String deployContract() {
        try {
            Credentials credentials = retrieveCredentialsSecurely(); // Retrieve securely

            // Retrieve block number for logging
            BigInteger blockNumber = web3j.ethBlockNumber().send().getBlockNumber();
            System.out.println("Latest Ethereum block number: " + blockNumber);

            ProductRegistry contract = ProductRegistry.deploy(
                    web3j, credentials, contractGasProvider
            ).send();

            contractAddress = contract.getContractAddress();
            return "Contract deployed successfully at address: " + contractAddress;
        } catch (Exception e) {
            return "Error deploying contract: " + e.getMessage();
        }
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestBody Map<String, Object> request) {
        BigInteger productId = new BigInteger(request.get("productId").toString());
        String name = (String) request.get("name");
        BigInteger price = new BigInteger(request.get("price").toString());
        BigInteger quantity = new BigInteger(request.get("quantity").toString());
        try {
            // Retrieve securely
            Credentials credentials = retrieveCredentialsSecurely();

            // Load existing contract using its address
            ProductRegistry contract = ProductRegistry.load(
                    contractAddress, web3j, credentials, contractGasProvider
            );

            // Execute the addProduct function
            TransactionReceipt receipt = contract.addProduct(productId, name, price, quantity).send();

            // Retrieve transaction hash and block number for logging
            String transactionHash = receipt.getTransactionHash();
            BigInteger blockNumber = receipt.getBlockNumber();

            AtomicReference<ProductRegistry.ProductAddedEventResponse> eventResponse = new AtomicReference<>();

            contract.productAddedEventFlowable(DefaultBlockParameter.valueOf(blockNumber), DefaultBlockParameter.valueOf(blockNumber))
                    .subscribe(event -> {
                        eventResponse.set(event);  // Store event data
                    });

            ProductRegistry.ProductAddedEventResponse eventData = eventResponse.get();

            if (eventData != null) {
                return "Product added successfully. Transaction Hash: " + transactionHash
                        + ", Block Number: " + blockNumber
                        + "\n"
                        + "Product ID: " + eventData.productId
                        + ", Name: " + eventData.name
                        + ", Price: " + eventData.price
                        + ", Quantity: " + eventData.quantity;
            } else {
                return "TransactionHash: " + transactionHash
                        + ", Block Number: " + blockNumber
                        + ", (Event data not available)";
            }
        } catch (Exception e) {
            return "Error adding product: " + e.getMessage();
        }
    }

    @PostMapping("/updateProduct")
    public String updateProduct(@RequestBody Map<String, Object> request) {
        BigInteger productId = new BigInteger(request.get("productId").toString());
        String name = (String) request.get("name");
        BigInteger price = new BigInteger(request.get("price").toString());
        BigInteger quantity = new BigInteger(request.get("quantity").toString());
        try {
            // Retrieve securely
            Credentials credentials = retrieveCredentialsSecurely();

            // Load existing contract using its address
            ProductRegistry contract = ProductRegistry.load(
                    contractAddress, web3j, credentials, contractGasProvider
            );

            // Execute the addProduct function
            TransactionReceipt receipt = contract.updateProduct(productId, name, price, quantity).send();

            // Retrieve transaction hash and block number for logging
            String transactionHash = receipt.getTransactionHash();
            BigInteger blockNumber = receipt.getBlockNumber();



            return "Product Update successfully. Transaction Hash: " + transactionHash
                        + ", Block Number: " + blockNumber;
        } catch (Exception e) {
            return "Error adding product: " + e.getMessage();
        }
    }

    @PostMapping("/deleteProduct")
    public String deleteProduct(@RequestBody Map<String, Object> request) {
        BigInteger productId = new BigInteger(request.get("productId").toString());
        try {
            // Retrieve securely
            Credentials credentials = retrieveCredentialsSecurely();

            // Load existing contract using its address
            ProductRegistry contract = ProductRegistry.load(
                    contractAddress, web3j, credentials, contractGasProvider
            );

            // Execute the addProduct function
            TransactionReceipt receipt = contract.deleteProduct(productId).send();

            // Retrieve transaction hash and block number for logging
            String transactionHash = receipt.getTransactionHash();
            BigInteger blockNumber = receipt.getBlockNumber();



            return "Product Delete successfully. Transaction Hash: " + transactionHash
                    + ", Block Number: " + blockNumber;
        } catch (Exception e) {
            return "Error adding product: " + e.getMessage();
        }
    }


}