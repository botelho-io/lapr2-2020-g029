package lapr.api.sout;

import lapr.api.MonetaryConversionAPI;

/**
 * The API adapter used to convert between monetary units.
 */
public class MonetaryConversionAPIAdapter implements MonetaryConversionAPI {

    /**
     * Converts currency.
     *
     * @param country the country that the currency will be converted.
     * @param euro the currency to convert.
     * @return the currency converted to country of the freelancer.
     */
    @Override
    public Double convert(String country, Double euro) {
        final Double amount = euro * 0.9;
        final String msg = String.format(
                    "*******************************************************\n"+
                    "MonetaryConversionAPI - Requested conversion to country [%s]\n"+
                    "~~~~~~ BEGIN ~~~~~~\n"+
                    "From [%f]€ got [%f]\n"+
                    "~~~~~~  END  ~~~~~~\n"+
                    "*******************************************************\n\n\n"
                ,country, euro, amount);
        System.out.println(msg);
        return amount;
    }

    @Override
    public void close() {
    }

}


