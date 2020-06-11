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
public interface HistoricalTransactionFileStrategy {
    boolean openFile(String path);
    List<Transaction> readData();
    void saveData(RegistFreelancer rf, ListTask ltk, ListTransaction ltr);
}
