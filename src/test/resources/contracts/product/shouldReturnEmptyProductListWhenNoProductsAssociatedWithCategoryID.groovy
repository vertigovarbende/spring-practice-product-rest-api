package contracts.product

import org.springframework.cloud.contract.spec.Contract

/**
 * USE-CASE: Should return empty product list when no products associated with category ID.
 */
Contract.make {
    description "Should return empty product list when no products associated with category ID"
    request {
        method GET()
        url "/api/products/category/1"
        headers {
            accept("application/hal+json")
        }
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