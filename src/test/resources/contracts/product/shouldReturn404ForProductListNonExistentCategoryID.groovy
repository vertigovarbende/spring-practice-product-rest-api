package contracts.product

import org.springframework.cloud.contract.spec.Contract

/**
 * USE-CASE: Should return 404 according to the related category ID not found.
 */
Contract.make {
    description "Should return 404 according to the related category ID not found"
    request {
        method GET()
        url "/api/products/category/999"
        headers {
            accept("application/hal+json")
        }
    }
    response {
        status NOT_FOUND()
    }
}