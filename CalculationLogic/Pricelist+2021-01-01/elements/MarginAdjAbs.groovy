if (!out.BasePrice || !out.MarginAdjPct){
    api.addWarning("Margin Adjustment cannot be calculated: missing parameter(s)")
    return
}

if(out.MarginAdjPct == 1){
    api.addWarning("Margin Adjustment % cannot be 100% -> division by 0")
    return
}

return out.BasePrice * out.MarginAdjPct  / ( 1 - out.MarginAdjPct)