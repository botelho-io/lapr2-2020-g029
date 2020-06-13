package lapr.controller;

import lapr.model.App;
import lapr.utils.Constants;

import java.io.File;
import java.io.IOException;

public class DeserializeController {

    /**
     * Returns true if the data file exists, false otherwise.
     * @return rue if the data file exists, false otherwise.
     */
    public boolean hasDataFile() {
        File f = new File(Constants.dataSavePath);
        return f.exists() && f.isFile() && f.canRead();
    }

    /**
     * Serialize data.
     * @throws IOException If the file is not able to be opened or serialization fails.
     * @throws ClassNotFoundException If the file cannot be deserialized.
     */
    public void deserialize() throws IOException, ClassNotFoundException {
        App app = App.deserialize(Constants.dataSavePath);
        AppPOE.setApp(app);
    }
}
