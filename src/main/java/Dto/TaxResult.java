package Dto;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class TaxResult {

    private BigDecimal tax;

    public TaxResult(BigDecimal tax) {
        this.tax = tax.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getTax(){
        return tax;
    }

    public void setTax(BigDecimal tax) {
        this.tax = tax;
    }
}
