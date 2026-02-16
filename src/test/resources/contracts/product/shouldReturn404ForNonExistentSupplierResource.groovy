package contracts.product

import org.springframework.cloud.contract.spec.Contract

/**
 * USE-CASE: Should return 404 for non-existent supplier resource
 */
Contract.make {
    description "Should return 404 for non-existent supplier resource"
    request {
        method POST()
        url "/api/products"
        headers {
            contentType("application/json")
            accept("application/hal+json")
        }
        // Beverages - category
        // NonExistentSupplierResource --> supplier
        body([
                name: "Test Product",
                category: "Beverages",
                supplier: "NonExistentSupplierResource", // supplier has been changed
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