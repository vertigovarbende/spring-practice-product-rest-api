package contracts.product

import org.springframework.cloud.contract.spec.Contract

/**
 * USE-CASE: Should update an existing product
 */
Contract.make {
    description "Should update an existing product"
    request {
        method PUT()
        url "/api/products"
        headers {
            contentType("application/json")
            accept("application/hal+json")
        }
        body([
                id: 1000,
                name: "New Updated Product",    // name has been updated
                category: "Beverages",
                supplier: "Exotic Liquids",
                sku: "SP00001",
                barcode: "BAR1000000001",
                price: 189.99,      // price has been updated
                quantityPerUnit: "quantity-per-unit",
                unitInStock: 10,    // unitInStock has been updated
        ])
    }
    response {
        status OK()
        headers {
            contentType("application/hal+json")
        }
        body([
                id: anyPositiveInt(),
                name: "New Updated Product",
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
                price: 189.99,
                unitInStock: 10,
                unitInOrder: anyPositiveInt()
        ])
    }
}