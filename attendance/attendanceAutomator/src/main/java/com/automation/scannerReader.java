package com.automation;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

import javax.usb.UsbDevice;
import javax.usb.UsbDeviceDescriptor;
import javax.usb.UsbException;
import javax.usb.UsbHostManager;
import javax.usb.UsbHub;
import javax.usb.UsbServices;

/**
 * Hello world!
 *
 */
public class scannerReader {

    private UsbDevice scanner;
    private static final Logger LOGGER = Logger.getLogger(scannerReader.class.getName());

    public scannerReader() throws SecurityException, UsbException {
        LOGGER.info("Scanning for scanner");
        this.scanner = findDevice();
    }

    public void run() {
        LOGGER.info("Scanner reader started");
        while (this.scanner == null) {
            try {
                this.scanner = findDevice();
            } catch (UsbException e) {
                e.printStackTrace();
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
        LOGGER.info("Scanner found " + this.scanner);
        while (true) {
            Scanner scanner = new Scanner(System.in);
            LOGGER.info("Waiting for input");
            while (scanner.hasNextLine()) {
                String input = scanner.nextLine();
                LOGGER.info("Input: " + input);
                try {
                    sendToServer(input);
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            scanner.close();
        }

    }

    private UsbDevice findDevice() throws SecurityException, UsbException {

        UsbServices services = UsbHostManager.getUsbServices();
        UsbHub rootHub = services.getRootUsbHub();
        UsbDevice device = findDevice(rootHub, (short) 5824, (short) 10203); // this is harcoded for the scanner
        if (device == null) {
            // LOGGER.info("Device not found with ID 5824:10203");
        } else {
            // LOGGER.info("Device found " + device);
        }
        return device;
    }

    private UsbDevice findDevice(UsbHub hub, short vendorId, short productId) {
        for (UsbDevice device : (List<UsbDevice>) hub.getAttachedUsbDevices()) {
            UsbDeviceDescriptor desc = device.getUsbDeviceDescriptor();
            if (desc.idVendor() == vendorId && desc.idProduct() == productId)
                return device;
            if (device.isUsbHub()) {
                device = findDevice((UsbHub) device, vendorId, productId);
                if (device != null)
                    return device;
            }
        }
        return null;
    }

    private void sendToServer(String message) throws UnknownHostException, IOException {
        String host = "localhost";
        int port = 8080; // that is just the number of the port I am using might change

        Socket socket = new Socket(host, port);
        PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);
        writer.println(message);
        writer.close();
        socket.close();
    }

    public static void main(String[] args) throws SecurityException, UsbException {
        scannerReader scanner = new scannerReader();
        scanner.run();
    }
}
