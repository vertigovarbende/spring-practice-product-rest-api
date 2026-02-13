package contracts.product

import org.springframework.cloud.contract.spec.Contract

/**
 * USE-CASE: Should create a new product
 */
Contract.make {
    description "Should create a new product"
    request {
        method POST()
        url "/api/products"
        headers {
            contentType("application/json")
            accept("application/hal+json")
        }
        // Beverages - category
        // Exotic Liquids --> supplier
        body([
                name: "Test Product",
                category: "Beverages",
                supplier: "Exotic Liquids",
                sku: "SP00001",
                barcode: "BAR1000000001",
                price: 149.99,
                quantityPerUnit: "quantity-per-unit",
                unitInStock: 15,
        ])
    }
    response {
        status CREATED()
        headers {
            contentType("application/hal+json")
        }
        body([
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
        ])
    }


}


