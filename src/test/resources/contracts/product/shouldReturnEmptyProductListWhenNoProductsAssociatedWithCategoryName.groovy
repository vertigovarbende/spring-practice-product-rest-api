package contracts.product

import org.springframework.cloud.contract.spec.Contract

/**
 * USE-CASE: Should return empty product list when no products associated with category NAME.
 */
Contract.make {
    description "Should return empty product list when no products associated with category NAME"
    request {
        method GET()
        url "/api/products/category?category_name=Beverages"
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