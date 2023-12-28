package example.springProject.contracts;

import io.reactivex.Flowable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.EventEncoder;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Event;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.DefaultBlockParameter;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.request.EthFilter;
import org.web3j.protocol.core.methods.response.BaseEventResponse;
import org.web3j.protocol.core.methods.response.Log;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple3;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/web3j/web3j/tree/master/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 4.10.3.
 */
@SuppressWarnings("rawtypes")
public class ProductRegistry extends Contract {
    public static final String BINARY = "608060405234801561000f575f80fd5b5061067b8061001d5f395ff3fe608060405234801561000f575f80fd5b506004361061004a575f3560e01c80632d5617871461004e57806334e7717814610063578063c27ed2a21461008e578063ed90c7b7146100a1575b5f80fd5b61006161005c3660046103cf565b6100b4565b005b610076610071366004610493565b61014e565b604051610085939291906104aa565b60405180910390f35b61006161009c3660046103cf565b6101f5565b6100616100af366004610493565b6102e2565b5f848152602081905260409020600101546101165760405162461bcd60e51b815260206004820152601660248201527f53616e207068616d206b686f6e6720746f6e207461690000000000000000000060448201526064015b60405180910390fd5b5f84815260208190526040902061012d8482610589565b505f9384526020849052604090932060018101919091556002019190915550565b5f6020819052908152604090208054819061016890610503565b80601f016020809104026020016040519081016040528092919081815260200182805461019490610503565b80156101df5780601f106101b6576101008083540402835291602001916101df565b820191905f5260205f20905b8154815290600101906020018083116101c257829003601f168201915b5050505050908060010154908060020154905083565b5f84815260208190526040902060010154156102535760405162461bcd60e51b815260206004820152601360248201527f53616e207068616d2064612074616e2074616900000000000000000000000000604482015260640161010d565b6040805160608101825284815260208082018590528183018490525f8781529081905291909120815181906102889082610589565b506020820151816001015560408201518160020155905050837f0107e99fb43ba873db8a5ce7c34a12cd44cd3041167cff32040ddb6aca455ae68484846040516102d4939291906104aa565b60405180910390a250505050565b5f8181526020819052604090206001015461033f5760405162461bcd60e51b815260206004820152601660248201527f53616e207068616d206b686f6e6720746f6e2074616900000000000000000000604482015260640161010d565b5f818152602081905260408120906103578282610369565b505f6001820181905560029091015550565b50805461037590610503565b5f825580601f10610384575050565b601f0160209004905f5260205f20908101906103a091906103a3565b50565b5b808211156103b7575f81556001016103a4565b5090565b634e487b7160e01b5f52604160045260245ffd5b5f805f80608085870312156103e2575f80fd5b84359350602085013567ffffffffffffffff80821115610400575f80fd5b818701915087601f830112610413575f80fd5b813581811115610425576104256103bb565b604051601f8201601f19908116603f0116810190838211818310171561044d5761044d6103bb565b816040528281528a6020848701011115610465575f80fd5b826020860160208301375f928101602001929092525095989597505050506040840135936060013592915050565b5f602082840312156104a3575f80fd5b5035919050565b606081525f84518060608401525f5b818110156104d657602081880181015160808684010152016104b9565b505f608082850101526080601f19601f830116840101915050836020830152826040830152949350505050565b600181811c9082168061051757607f821691505b60208210810361053557634e487b7160e01b5f52602260045260245ffd5b50919050565b601f821115610584575f81815260208120601f850160051c810160208610156105615750805b601f850160051c820191505b818110156105805782815560010161056d565b5050505b505050565b815167ffffffffffffffff8111156105a3576105a36103bb565b6105b7816105b18454610503565b8461053b565b602080601f8311600181146105ea575f84156105d35750858301515b5f19600386901b1c1916600185901b178555610580565b5f85815260208120601f198616915b82811015610618578886015182559484019460019091019084016105f9565b508582101561063557878501515f19600388901b60f8161c191681555b5050505050600190811b0190555056fea2646970667358221220dc82e2427da1f6553d7bd80dc7e96d90b449059d0eeeac2dad2f20050e6a56ed64736f6c63430008140033";

    public static final String FUNC_ADDPRODUCT = "addProduct";

    public static final String FUNC_DELETEPRODUCT = "deleteProduct";

    public static final String FUNC_PRODUCTREGISTRY = "productRegistry";

    public static final String FUNC_UPDATEPRODUCT = "updateProduct";

    public static final Event PRODUCTADDED_EVENT = new Event("ProductAdded", 
            Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>(true) {}, new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
    ;

    @Deprecated
    protected ProductRegistry(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected ProductRegistry(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected ProductRegistry(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected ProductRegistry(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static List<ProductAddedEventResponse> getProductAddedEvents(TransactionReceipt transactionReceipt) {
        List<Contract.EventValuesWithLog> valueList = staticExtractEventParametersWithLog(PRODUCTADDED_EVENT, transactionReceipt);
        ArrayList<ProductAddedEventResponse> responses = new ArrayList<ProductAddedEventResponse>(valueList.size());
        for (Contract.EventValuesWithLog eventValues : valueList) {
            ProductAddedEventResponse typedResponse = new ProductAddedEventResponse();
            typedResponse.log = eventValues.getLog();
            typedResponse.productId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
            typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
            typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
            typedResponse.quantity = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
            responses.add(typedResponse);
        }
        return responses;
    }

    public static ProductAddedEventResponse getProductAddedEventFromLog(Log log) {
        Contract.EventValuesWithLog eventValues = staticExtractEventParametersWithLog(PRODUCTADDED_EVENT, log);
        ProductAddedEventResponse typedResponse = new ProductAddedEventResponse();
        typedResponse.log = log;
        typedResponse.productId = (BigInteger) eventValues.getIndexedValues().get(0).getValue();
        typedResponse.name = (String) eventValues.getNonIndexedValues().get(0).getValue();
        typedResponse.price = (BigInteger) eventValues.getNonIndexedValues().get(1).getValue();
        typedResponse.quantity = (BigInteger) eventValues.getNonIndexedValues().get(2).getValue();
        return typedResponse;
    }

    public Flowable<ProductAddedEventResponse> productAddedEventFlowable(EthFilter filter) {
        return web3j.ethLogFlowable(filter).map(log -> getProductAddedEventFromLog(log));
    }

    public Flowable<ProductAddedEventResponse> productAddedEventFlowable(DefaultBlockParameter startBlock, DefaultBlockParameter endBlock) {
        EthFilter filter = new EthFilter(startBlock, endBlock, getContractAddress());
        filter.addSingleTopic(EventEncoder.encode(PRODUCTADDED_EVENT));
        return productAddedEventFlowable(filter);
    }

    public RemoteFunctionCall<TransactionReceipt> addProduct(BigInteger _productId, String _name, BigInteger _price, BigInteger _quantity) {
        final Function function = new Function(
                FUNC_ADDPRODUCT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_productId), 
                new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.generated.Uint256(_quantity)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<TransactionReceipt> deleteProduct(BigInteger _productId) {
        final Function function = new Function(
                FUNC_DELETEPRODUCT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_productId)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple3<String, BigInteger, BigInteger>> productRegistry(BigInteger param0) {
        final Function function = new Function(FUNC_PRODUCTREGISTRY, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Uint256>() {}, new TypeReference<Uint256>() {}));
        return new RemoteFunctionCall<Tuple3<String, BigInteger, BigInteger>>(function,
                new Callable<Tuple3<String, BigInteger, BigInteger>>() {
                    @Override
                    public Tuple3<String, BigInteger, BigInteger> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple3<String, BigInteger, BigInteger>(
                                (String) results.get(0).getValue(), 
                                (BigInteger) results.get(1).getValue(), 
                                (BigInteger) results.get(2).getValue());
                    }
                });
    }

    public RemoteFunctionCall<TransactionReceipt> updateProduct(BigInteger _productId, String _name, BigInteger _price, BigInteger _quantity) {
        final Function function = new Function(
                FUNC_UPDATEPRODUCT, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(_productId), 
                new org.web3j.abi.datatypes.Utf8String(_name), 
                new org.web3j.abi.datatypes.generated.Uint256(_price), 
                new org.web3j.abi.datatypes.generated.Uint256(_quantity)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    @Deprecated
    public static ProductRegistry load(String contractAddress, Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return new ProductRegistry(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static ProductRegistry load(String contractAddress, Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new ProductRegistry(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static ProductRegistry load(String contractAddress, Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return new ProductRegistry(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static ProductRegistry load(String contractAddress, Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new ProductRegistry(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<ProductRegistry> deploy(Web3j web3j, Credentials credentials, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ProductRegistry.class, web3j, credentials, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ProductRegistry> deploy(Web3j web3j, Credentials credentials, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ProductRegistry.class, web3j, credentials, gasPrice, gasLimit, BINARY, "");
    }

    public static RemoteCall<ProductRegistry> deploy(Web3j web3j, TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return deployRemoteCall(ProductRegistry.class, web3j, transactionManager, contractGasProvider, BINARY, "");
    }

    @Deprecated
    public static RemoteCall<ProductRegistry> deploy(Web3j web3j, TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(ProductRegistry.class, web3j, transactionManager, gasPrice, gasLimit, BINARY, "");
    }

    public static class ProductAddedEventResponse extends BaseEventResponse {
        public BigInteger productId;

        public String name;

        public BigInteger price;

        public BigInteger quantity;
    }
}
