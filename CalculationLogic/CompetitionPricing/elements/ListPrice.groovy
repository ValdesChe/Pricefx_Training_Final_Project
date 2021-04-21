if(out.CompetitorProductPrice == null || out.CompetitivePositioning == null ){
    api.addWarning("ListPrice cannot be calculated -> Missing parameter(s)")
    return
}

return out.CompetitorProductPrice * out.CompetitivePositioning