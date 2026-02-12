package contracts.product

import org.springframework.cloud.contract.spec.Contract

/**
 * USE-CASE: Should delete a product by id
 */
Contract.make {
    description "Should delete a product by id"
    request {
        method DELETE()
        url "/api/products/1000"
    }
    response {
        status NO_CONTENT()
    }

}