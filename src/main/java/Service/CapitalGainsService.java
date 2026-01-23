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

                BigDecimal sellPrice = op.

            }
        }

    }
}
