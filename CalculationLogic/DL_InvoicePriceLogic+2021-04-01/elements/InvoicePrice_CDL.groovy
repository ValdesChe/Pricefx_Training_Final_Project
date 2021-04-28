// api.userEntry() is used to generate the input parameters,
// which will be mapped to the columns in the Datamart,
// but will not be used for actual user input.
if(api.isSyntaxCheck()) {
    api.userEntry("ListPrice")
    api.userEntry("Discount")
} else {
    if(input.ListPrice == null || input.Discount == null) {
        return null
    }
    return input.ListPrice - input.Discount
}