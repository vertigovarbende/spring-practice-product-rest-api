package contracts.product

import org.springframework.cloud.contract.spec.Contract

/**
 * USE-CASE: Should return 404 for non-existent product
 */
Contract.make {
    description "Should return 404 for non-existent product"
    request {
        method GET()
        url "/api/products/999"
        headers {
            accept("application/hal+json")
        }
    }
    response {
        status NOT_FOUND()
    }
}