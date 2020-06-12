/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.strategies.htf;

import lapr.strategies.htf.utils.HistoricalTransactionFileStrategyCommonCSV;

/**
 *
 * @author Universidade
 */
public class CSVTransactionFileStrategy extends HistoricalTransactionFileStrategyCommonCSV {
    public CSVTransactionFileStrategy() {
        super(";");
    }
}
