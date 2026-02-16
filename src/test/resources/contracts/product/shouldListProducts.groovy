package contracts.product

import org.springframework.cloud.contract.spec.Contract


/**
 * USE-CASE: Should return list of products
 */
Contract.make {
    description "Should return list of products"
    request {
        method GET()
        url "/api/products"
        headers {
            accept("application/hal+json")
        }
    }
    response {
        status OK()
        headers {
            contentType("application/hal+json")
        }
        body(
                "_embedded": [
                        "productList": [
                                [
                                        id             : anyPositiveInt(),
                                        name           : "Test Product",
                                        category       : [
                                                id         : anyPositiveInt(),
                                                name       : "Beverages",
                                                description: "Soft drinks, coffees, teas, beers, and ales"
                                        ],
                                        supplier       : [
                                                id          : anyPositiveInt(),
                                                companyName : "Exotic Liquids",
                                                contactName : "Charlotte Cooper",
                                                contactTitle: "Purchasing Manager",
                                                address     : "49 Gilbert St.",
                                                country     : "UK",
                                                phone       : "(171) 555-2222",
                                                homepage    : null
                                        ],
                                        sku            : "SP00001",
                                        barcode        : "BAR1000000001",
                                        quantityPerUnit: "quantity-per-unit",
                                        price          : 149.99,
                                        unitInStock    : 15,
                                        unitInOrder    : anyPositiveInt()
                                ],
                                [
                                        id             : anyPositiveInt(),
                                        name           : "Test Product",
                                        category       : [
                                                id         : anyPositiveInt(),
                                                name       : "Beverages",
                                                description: "Soft drinks, coffees, teas, beers, and ales"
                                        ],
                                        supplier       : [
                                                id          : anyPositiveInt(),
                                                companyName : "Exotic Liquids",
                                                contactName : "Charlotte Cooper",
                                                contactTitle: "Purchasing Manager",
                                                address     : "49 Gilbert St.",
                                                country     : "UK",
                                                phone       : "(171) 555-2222",
                                                homepage    : null
                                        ],
                                        sku            : "SP00001",
                                        barcode        : "BAR1000000001",
                                        quantityPerUnit: "quantity-per-unit",
                                        price          : 149.99,
                                        unitInStock    : 15,
                                        unitInOrder    : anyPositiveInt()
                                ],
                                [
                                        id: anyPositiveInt(),
                                        name: "Test Product",
                                        category: [
                                                id: anyPositiveInt(),
                                                name: "Beverages",
                                                description: "Soft drinks, coffees, teas, beers, and ales"
                                        ],
                                        supplier: [
                                                id: anyPositiveInt(),
                                                companyName: "Exotic Liquids",
                                                contactName: "Charlotte Cooper",
                                                contactTitle: "Purchasing Manager",
                                                address: "49 Gilbert St.",
                                                country: "UK",
                                                phone: "(171) 555-2222",
                                                homepage: null
                                        ],
                                        sku: "SP00001",
                                        barcode: "BAR1000000001",
                                        quantityPerUnit: "quantity-per-unit",
                                        price: 149.99,
                                        unitInStock: 15,
                                        unitInOrder: anyPositiveInt()
                                ],
                                [
                                        id: anyPositiveInt(),
                                        name: "Test Product",
                                        category: [
                                                id: anyPositiveInt(),
                                                name: "Beverages",
                                                description: "Soft drinks, coffees, teas, beers, and ales"
                                        ],
                                        supplier: [
                                                id: anyPositiveInt(),
                                                companyName: "Exotic Liquids",
                                                contactName: "Charlotte Cooper",
                                                contactTitle: "Purchasing Manager",
                                                address: "49 Gilbert St.",
                                                country: "UK",
                                                phone: "(171) 555-2222",
                                                homepage: null
                                        ],
                                        sku: "SP00001",
                                        barcode: "BAR1000000001",
                                        quantityPerUnit: "quantity-per-unit",
                                        price: 149.99,
                                        unitInStock: 15,
                                        unitInOrder: anyPositiveInt()
                                ],
                                [
                                        id: anyPositiveInt(),
                                        name: "Test Product",
                                        category: [
                                                id: anyPositiveInt(),
                                                name: "Beverages",
                                                description: "Soft drinks, coffees, teas, beers, and ales"
                                        ],
                                        supplier: [
                                                id: anyPositiveInt(),
                                                companyName: "Exotic Liquids",
                                                contactName: "Charlotte Cooper",
                                                contactTitle: "Purchasing Manager",
                                                address: "49 Gilbert St.",
                                                country: "UK",
                                                phone: "(171) 555-2222",
                                                homepage: null
                                        ],
                                        sku: "SP00001",
                                        barcode: "BAR1000000001",
                                        quantityPerUnit: "quantity-per-unit",
                                        price: 149.99,
                                        unitInStock: 15,
                                        unitInOrder: anyPositiveInt()
                                ],
                                [
                                        id: anyPositiveInt(),
                                        name: "Test Product",
                                        category: [
                                                id: anyPositiveInt(),
                                                name: "Beverages",
                                                description: "Soft drinks, coffees, teas, beers, and ales"
                                        ],
                                        supplier: [
                                                id: anyPositiveInt(),
                                                companyName: "Exotic Liquids",
                                                contactName: "Charlotte Cooper",
                                                contactTitle: "Purchasing Manager",
                                                address: "49 Gilbert St.",
                                                country: "UK",
                                                phone: "(171) 555-2222",
                                                homepage: null
                                        ],
                                        sku: "SP00001",
                                        barcode: "BAR1000000001",
                                        quantityPerUnit: "quantity-per-unit",
                                        price: 149.99,
                                        unitInStock: 15,
                                        unitInOrder: anyPositiveInt()
                                ],
                                [
                                        id: anyPositiveInt(),
                                        name: "Test Product",
                                        category: [
                                                id: anyPositiveInt(),
                                                name: "Beverages",
                                                description: "Soft drinks, coffees, teas, beers, and ales"
                                        ],
                                        supplier: [
                                                id: anyPositiveInt(),
                                                companyName: "Exotic Liquids",
                                                contactName: "Charlotte Cooper",
                                                contactTitle: "Purchasing Manager",
                                                address: "49 Gilbert St.",
                                                country: "UK",
                                                phone: "(171) 555-2222",
                                                homepage: null
                                        ],
                                        sku: "SP00001",
                                        barcode: "BAR1000000001",
                                        quantityPerUnit: "quantity-per-unit",
                                        price: 149.99,
                                        unitInStock: 15,
                                        unitInOrder: anyPositiveInt()
                                ],
                                [
                                        id: anyPositiveInt(),
                                        name: "Test Product",
                                        category: [
                                                id: anyPositiveInt(),
                                                name: "Beverages",
                                                description: "Soft drinks, coffees, teas, beers, and ales"
                                        ],
                                        supplier: [
                                                id: anyPositiveInt(),
                                                companyName: "Exotic Liquids",
                                                contactName: "Charlotte Cooper",
                                                contactTitle: "Purchasing Manager",
                                                address: "49 Gilbert St.",
                                                country: "UK",
                                                phone: "(171) 555-2222",
                                                homepage: null
                                        ],
                                        sku: "SP00001",
                                        barcode: "BAR1000000001",
                                        quantityPerUnit: "quantity-per-unit",
                                        price: 149.99,
                                        unitInStock: 15,
                                        unitInOrder: anyPositiveInt()
                                ],
                                [
                                        id: anyPositiveInt(),
                                        name: "Test Product",
                                        category: [
                                                id: anyPositiveInt(),
                                                name: "Beverages",
                                                description: "Soft drinks, coffees, teas, beers, and ales"
                                        ],
                                        supplier: [
                                                id: anyPositiveInt(),
                                                companyName: "Exotic Liquids",
                                                contactName: "Charlotte Cooper",
                                                contactTitle: "Purchasing Manager",
                                                address: "49 Gilbert St.",
                                                country: "UK",
                                                phone: "(171) 555-2222",
                                                homepage: null
                                        ],
                                        sku: "SP00001",
                                        barcode: "BAR1000000001",
                                        quantityPerUnit: "quantity-per-unit",
                                        price: 149.99,
                                        unitInStock: 15,
                                        unitInOrder: anyPositiveInt()
                                ],
                                [
                                        id: anyPositiveInt(),
                                        name: "Test Product",
                                        category: [
                                                id: anyPositiveInt(),
                                                name: "Beverages",
                                                description: "Soft drinks, coffees, teas, beers, and ales"
                                        ],
                                        supplier: [
                                                id: anyPositiveInt(),
                                                companyName: "Exotic Liquids",
                                                contactName: "Charlotte Cooper",
                                                contactTitle: "Purchasing Manager",
                                                address: "49 Gilbert St.",
                                                country: "UK",
                                                phone: "(171) 555-2222",
                                                homepage: null
                                        ],
                                        sku: "SP00001",
                                        barcode: "BAR1000000001",
                                        quantityPerUnit: "quantity-per-unit",
                                        price: 149.99,
                                        unitInStock: 15,
                                        unitInOrder: anyPositiveInt()
                                ]
                        ],
                        "_links": [
                              "self": [
                                      "href": "http://localhost:8080/api/products",
                                      "type": "GET"
                              ]
                        ]
                ]
        )
    }
}