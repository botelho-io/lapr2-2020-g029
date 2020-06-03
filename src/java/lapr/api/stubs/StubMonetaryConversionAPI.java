package lapr.api.stubs;

import lapr.api.MonetaryConversionAPI;

public class StubMonetaryConversionAPI implements MonetaryConversionAPI {
    @Override
    public Double convert(String country, Double euro) {
        final Double amount = euro * 0.9;
        final String msg = String.format("MonetaryConversionAPI - Requested conversion from %f€ to country %s, gave %f", euro, country, amount);
        return amount;
    }
}
