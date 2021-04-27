def dmCtx = api.getDatamartContext()

def salesDm = dmCtx.getDatamart("Transaction")

def customer = out.Customer
def filters = [
        Filter.equal("CustomerId", customer)
]
def query = dmCtx.newQuery(salesDm, true) // Rollup = true for aggregation
        .select("CustomerId", "Customer ID")
        .select("COUNTDISTINCT(InvoiceDate)", "Invoice Count")
        .select("SUM(Margin)/COUNTDISTINCT(InvoiceDate)", "Avg Profit")
        .select("SUM(InvoicePrice)", "Total Revenue")
        .select("SUM(Margin)", "Total profit")
        .select("SUM(Quantity)", "Total Qty")
        .select("SUM(Discount)", "Total Discount")
        .where(filters)

def otherQuery = dmCtx.newQuery(query)
otherQuery.select("Customer ID")

def result = dmCtx.executeQuery(otherQuery)

result?.getData()?.each {
    row->
        api.trace("Row ", row)
}