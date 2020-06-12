/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.strategies.htf;

import lapr.list.ListTask;
import lapr.list.ListTransaction;
import lapr.model.Transaction;
import lapr.regist.RegistFreelancer;
import lapr.strategies.htf.utils.HistoricalTransactionFileStrategyCommonCSV;

import java.util.List;

/**
 *
 * @author Universidade
 */
public class TXTTransactionFileStrategy extends HistoricalTransactionFileStrategyCommonCSV {
    public TXTTransactionFileStrategy() {
        super("\t");
    }
}
