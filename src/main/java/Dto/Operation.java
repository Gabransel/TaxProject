package Dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public class Operation {

    @NotNull(message = "The operation is mandatory.")
    private String operation;

    @NotNull(message = "The unit cost is mandatory.")
    @Min(value = 0, message = "The unit cost cannot  be negative.")
    @JsonProperty("unit-cost")
    private BigDecimal unitCost;

    @NotNull(message = "The quantity is mandatory.")
    @Min(value = 1, message = "The quantity must be greater than zero.")
    private Integer quantity;

    public boolean isBuy() {
        return "buy".equalsIgnoreCase(this.operation);
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(BigDecimal unitCost) {
        this.unitCost = unitCost;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
