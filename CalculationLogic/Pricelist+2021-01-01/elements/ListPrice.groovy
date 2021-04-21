
if(out.BasePrice == null || out.AttributeAdjAbs == null || out.MarginAdjAbs == null){
    api.addWarning("List Price cannot be calculated: missing parameter(s).")
    return
}

return out.BasePrice + out.MarginAdjAbs + out.AttributeAdjAbs