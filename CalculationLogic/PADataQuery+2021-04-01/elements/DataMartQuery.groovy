def dmCtx = api.getDatamartContext()

def salesDm = dmCtx.getDatamart("Transaction")

def customer = out.Customer

def query = dmCtx.newQuery(salesDm, false)
        .select("CustomerId")
        .select("Cost")
        .select("InvoiceDate")
        .where(
            Filter.equal("CustomerId", customer)
        )

def result = dmCtx.executeQuery(query)

result?.getData()?.each {
    row->
        api.trace("Customer ID:", row.CustomerId)
        api.trace("Date:", row.InvoiceDate)
        api.trace("Cost", row.Cost)
}