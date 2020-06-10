package lapr.controller;

import lapr.model.App;
import lapr.utils.Constants;

import java.io.File;
import java.io.IOException;

public class DeserializeController {

    /**
     * @return True if the data file exists, false otherwise.
     */
    public boolean hasDataFile() {
        File f = new File(Constants.dataSavePath);
        return f.exists() && f.isFile() && f.canRead();
    }

    /**
     * Serialize data.
     * @throws IOException If the file is not able to be opened or serialization fails.
     */
    public void deserialize() throws IOException, ClassNotFoundException {
        App app = App.deserialize(Constants.dataSavePath);
        AppPOE.setApp(app);
    }
}
