package com.ks.konversa.hsm;

import com.hp.nonstop.io.process.Process;
import com.hp.nonstop.io.process.ProcessIdentifier;
import com.hp.nonstop.io.process.ProcessOpenOption;
import com.hp.nonstop.io.process.ProcessTimeoutException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        String hsmName = System.getProperty("ks.process.name", "$BOX6");

        ProcessOpenOption option = new ProcessOpenOption(
                ProcessOpenOption.ACCESS_READ_WRITE,
                ProcessOpenOption.EXCLUSION_SHARED, 5000, // Timeout in ms
                1000, // maximum message size sent to the server
                500);// maximum size of the response from the server

        Process hsm = new Process(new ProcessIdentifier(hsmName), option);

        OutputStreamWriter out = null;
        try {
            out = new OutputStreamWriter(hsm.getOutputStream());
            String message = "<119#1#U#1kDNE000,09E5D9C68A09609572AED38B52E96650C089DC7CAE5C1D47,1ADE48EB41AEE81E#B0080P0TB00E00009DAB98CCD11C83654575D5B505EDA6CD81B50EFF747782F5D3091925EE1B5D5B#>";
            System.out.println("Sending message to HSM: " + message);
            out.write(message);
            out.flush();

            BufferedReader read = new BufferedReader(new InputStreamReader(hsm.getInputStream()));
            System.out.println("The response from the server is: " + read.readLine());
        } catch (IOException ex) {
            if (ex instanceof ProcessTimeoutException) {
                try {
                    hsm.cancel();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
