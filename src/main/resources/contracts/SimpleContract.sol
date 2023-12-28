// SPDX-License-Identifier: MIT
pragma solidity >=0.8.0;

contract ProductRegistry {
    struct Product {
        string name;
        uint price;
        uint quantity;
    }

    mapping(uint => Product) public productRegistry;

    event ProductAdded(uint indexed productId, string name, uint price, uint quantity);

    function addProduct(uint _productId, string memory _name, uint _price, uint _quantity) public {
        require(productRegistry[_productId].price == 0, "San pham da tan tai");

        productRegistry[_productId] = Product(_name, _price, _quantity);

        emit ProductAdded(_productId, _name, _price, _quantity);
    }

    function updateProduct(uint _productId, string memory _name, uint _price, uint _quantity) public {
        require(productRegistry[_productId].price > 0, "San pham khong ton tai");

        productRegistry[_productId].name = _name;
        productRegistry[_productId].price = _price;
        productRegistry[_productId].quantity = _quantity;
    }

    function deleteProduct(uint _productId) public {
        require(productRegistry[_productId].price > 0, "San pham khong ton tai");
        delete productRegistry[_productId];
    }
}
