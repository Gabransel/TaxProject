package Service;

import Dto.Operation;
import Dto.TaxResult;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
public class CapitalGainsService {

    public List<TaxResult> calculateTaxes(List<Operation> operations) {
        List<TaxResult> results = new ArrayList<>();

        int currentQuantity = 0;
        BigDecimal weightedAvarege = BigDecimal.ZERO;
        BigDecimal accumulatedLoss = BigDecimal.ZERO;

        for (Operation op : operations) {

            if (op.isBuy()) {

                if (currentQuantity + op.getQuantity() > 0) {

                    BigDecimal totalCurrentValue = weightedAvarege.multiply(new BigDecimal(currentQuantity));

                    BigDecimal totalNewValue = op.getUnitCost().multiply(new BigDecimal(op.getQuantity()));

                    currentQuantity += op.getQuantity();

                    weightedAvarege = totalCurrentValue.add(totalNewValue).divide(new BigDecimal(currentQuantity), 2, RoundingMode.HALF_UP);

                } else {

                    currentQuantity += op.getQuantity();
                    weightedAvarege = op.getUnitCost();
                }

                results.add(new TaxResult(BigDecimal.ZERO));

            } else {

                BigDecimal sellPrice = op.getUnitCost();
                int sellQty = op.getQuantity();

                BigDecimal totalOperationValue = sellPrice.multiply(new BigDecimal(sellQty));

                BigDecimal originalCost = weightedAvarege.multiply(new BigDecimal(sellQty));

                BigDecimal profitOrLoss = totalOperationValue.subtract(originalCost);

                currentQuantity -= sellQty;

                if (profitOrLoss.compareTo(BigDecimal.ZERO) < 0) {

                    accumulatedLoss = accumulatedLoss.add(profitOrLoss.abs());
                    results.add(new TaxResult(BigDecimal.ZERO));

                } else {

                    if (accumulatedLoss.compareTo(BigDecimal.ZERO) > 0) {
                        if (accumulatedLoss.compareTo(profitOrLoss) >= 0) {

                            accumulatedLoss = accumulatedLoss.subtract(profitOrLoss);
                            profitOrLoss = BigDecimal.ZERO;
                        } else {

                            profitOrLoss = profitOrLoss.subtract(accumulatedLoss);
                            accumulatedLoss = BigDecimal.ZERO;
                        }
                    }

                    BigDecimal tax = profitOrLoss.multiply(new BigDecimal("0.20"));
                    results.add(new TaxResult(tax));
                }
            }
        }
        return results;
    }
}
