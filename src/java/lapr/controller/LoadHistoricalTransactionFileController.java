package lapr.controller;

import csvparser.LineExceptionStack;
import lapr.list.ListTask;
import lapr.list.ListTransaction;
import lapr.model.App;
import lapr.model.Organization;
import lapr.model.Transaction;
import lapr.regist.RegistFreelancer;
import lapr.strategies.htf.CSVTransactionFileStrategy;
import lapr.strategies.htf.TXTTransactionFileStrategy;
import lapr.strategies.htf.utils.HistoricalTransactionFileStrategyCommonCSV;
import lapr.strategies.htf.HistoricalTransactionFileStrategy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoadHistoricalTransactionFileController {

    final List<HistoricalTransactionFileStrategy> strategies;

    public LoadHistoricalTransactionFileController () {
        strategies = new ArrayList<>();
    }

    public void addFiles(final List<File> files) throws IOException {
        for (final File f : files) {
            final String name = f.getName();

            String extension;
            int lio = name.lastIndexOf(".");
            if(lio == -1) extension = "";
            else extension = name.substring(lio+1);

            HistoricalTransactionFileStrategy strat;
            switch (extension) {
                case "csv":
                case "CSV":
                    strat = new CSVTransactionFileStrategy();
                    strat.openFile(f.getPath());
                    break;
                case "txt":
                case "TXT":
                    strat = new TXTTransactionFileStrategy();
                    strat.openFile(f.getPath());
                    break;
                default:
                    throw new IOException("Cannot open file type: " + extension);
            }
            strategies.add(strat);
        }
    }

    public void loadData() throws LineExceptionStack {
        LineExceptionStack acc = new LineExceptionStack("Some errors occurred while loading the information from the file into memory");
        for (HistoricalTransactionFileStrategy s : strategies) {
            try {
                s.loadData();
            } catch (LineExceptionStack e) {
                acc.push(e);
            }
        }
        if(!acc.empty())
            throw acc;
    }

    public List<Transaction> getData() {
        ArrayList<Transaction> trss = new ArrayList<>();
        for (HistoricalTransactionFileStrategy s : strategies) {
            trss.addAll(s.getData());
        }
        return Collections.unmodifiableList(trss);
    }

    public void saveData() throws LineExceptionStack {
        LineExceptionStack acc = new LineExceptionStack("Some errors occurred while trying to save the data into the system");
        final App app = AppPOE.getInstance().getApp();
        final RegistFreelancer rf = app.getRegistFreelancer();
        final String email = app.getAuthFacade().getCurrentSession().getEmailUser();
        final Organization org = app.getRegistOrganization().getOrganizationByEmailUser(email);
        final ListTransaction ltr = org.getListTransaction();
        final ListTask ltk = org.getListTask();
        for (HistoricalTransactionFileStrategy s : strategies) {
            try {
                s.saveData(rf, ltk, ltr);
            } catch (LineExceptionStack e) {
                acc.push(e);
            }
        }
        if(!acc.empty())
            throw acc;
    }
}
