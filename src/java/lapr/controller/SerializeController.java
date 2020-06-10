package lapr.controller;

import lapr.model.App;
import lapr.utils.Constants;

import java.io.IOException;

public class SerializeController {
    /**
     * Serialize data.
     * @throws IOException If the file is not able to be opened or serialization fails.
     */
    public void serialize() throws IOException {
        final App app = AppPOE.getInstance().getApp();
        app.serialize(Constants.dataSavePath);
    }
}
