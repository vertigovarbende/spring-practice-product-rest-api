package contracts.product

import org.springframework.cloud.contract.spec.Contract

/**
 * USE-CASE: Should return empty product list when no products associated with supplier NAME.
 */
Contract.make {
    description "Should return empty product list when no products associated with supplier NAME"
    request {
        method GET()
        url "/api/products/supplier?companyName=NonExistentSupplierName"
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