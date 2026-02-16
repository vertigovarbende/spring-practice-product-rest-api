package contracts.product

import org.springframework.cloud.contract.spec.Contract

/**
 * USE-CASE: Should return 404 when client sends DELETE request for non-existing product resource
 */
Contract.make {
    description "Should return 404 when client sends DELETE request for non-existing product resource"
    request {
        method DELETE()
        url "/api/products/999"
        headers {
            accept("application/hal+json")
        }
    }
    response {
        status NOT_FOUND()
    }
}