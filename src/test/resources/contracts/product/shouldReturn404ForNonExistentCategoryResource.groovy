package contracts.product

import org.springframework.cloud.contract.spec.Contract

/**
 * USE-CASE: Should return 404 for non-existent category resource
 */
Contract.make {
    description "Should return 404 for non-existent category resource"
    request {
        method POST()
        url "/api/products"
        headers {
            contentType("application/json")
            accept("application/hal+json")
        }
        // NonExistentCategoryResource - category
        // Exotic Liquids --> supplier
        body([
                name: "Test Product",
                category: "NonExistentCategoryResource", // category has been changed
                supplier: "Exotic Liquids",
                sku: "SP00001",
                barcode: "BAR1000000001",
                price: 149.99,
                quantityPerUnit: "quantity-per-unit",
                unitInStock: 15,
        ])
    }
    response {
        status NOT_FOUND()
    }

}

