package lapr.controller;

import csvparser.LineExceptionStack;
import lapr.lists.ListTask;
import lapr.lists.ListTransaction;
import lapr.model.App;
import lapr.model.Organization;
import lapr.model.Transaction;
import lapr.lists.RegistFreelancer;
import lapr.strategies.htf.CSVTransactionFileStrategy;
import lapr.strategies.htf.TXTTransactionFileStrategy;
import lapr.strategies.htf.HistoricalTransactionFileStrategy;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LoadHistoricalTransactionFileController {

    /**
     * An instance of historical transaction file strategy.
     */
    final List<HistoricalTransactionFileStrategy> strategies;

    /**
     * Constructor.
     */
    public LoadHistoricalTransactionFileController () {
        strategies = new ArrayList<>();
    }

    /**
     * Adds the files that will be loaded.
     * @param files The files that will be loaded.
     * @throws IOException If the files fail to load.
     */
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

    /**
     * Load's data from the file into memory.
     * @throws LineExceptionStack If any error is encountered while loading the data.
     */
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

    /**
     * Get's data from the transaction file into transaction list.
     * @return The transactions loaded from file.
     */
    public List<Transaction> getData() {
        ArrayList<Transaction> trss = new ArrayList<>();
        for (HistoricalTransactionFileStrategy s : strategies) {
            trss.addAll(s.getData());
        }
        return Collections.unmodifiableList(trss);
    }

    /**
     * Save's data from the file into the system.
     * @throws LineExceptionStack If any error is encountered while loading the data.
     */
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
