package contracts.product

import org.springframework.cloud.contract.spec.Contract

/**
 * USE-CASE: Should update an existing product
 */
Contract.make {
    description "Should update an existing product"
    request {
        method PUT()
        url "/api/products/1000"
        headers {
            contentType("application/json")
            accept("application/hal+json")
        }
        body([

        ])
    }
    response {
        status OK()
        headers {
            contentType("application/hal+json")
        }
        body([

        ])
    }
}