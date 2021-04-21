
if(out.MarginAdjAbs == null || out.AttributeAdjPct == null){
    api.addWarning("Unable to look up Attribute Adjustment with the Product Life Cycle")
    return
}

if(out.AttributeAdjPct == 1){
    api.addWarning("Attribute Adjustment cannot be 100% -> division by 0")
}

return (out.BasePrice + out.MarginAdjAbs ) * out.AttributeAdjPct / (1 - out.AttributeAdjPct)