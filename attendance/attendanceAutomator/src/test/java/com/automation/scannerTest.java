package com.automation;

import javax.usb.UsbException;

import com.automation.Server.InfoServer;
import org.junit.jupiter.api.Test;

/**
 * Unit test for simple App.
 */
public class scannerTest extends Thread {

    /**
     * Create the test case
     *
     * @param testName name of the test case
     * @throws UsbException
     * @throws SecurityException
     * @throws InterruptedException
     */
    @Test
    public void aserverTester() throws SecurityException, UsbException {
        InfoServer server = new InfoServer(8080);
        server.run();
    }

    @Test
    public void scannerTester() throws SecurityException, UsbException, InterruptedException {
        scannerReader scanner = new scannerReader();
        scanner.run();
    }
}
