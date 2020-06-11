/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lapr.model.strategies.htf;

import lapr.list.ListTask;
import lapr.list.ListTransaction;
import lapr.model.Transaction;
import lapr.regist.RegistFreelancer;

import java.util.List;

/**
 *
 * @author Universidade
 */
public class CSVTransactionFileStrategy implements HistoricalTransactionFileStrategy {

    @Override
    public boolean openFile(String path) {
        return false;
    }

    @Override
    public List<Transaction> readData() {
        return null;
    }

    @Override
    public void saveData(RegistFreelancer rf, ListTask ltk, ListTransaction ltr) {

    }
}
