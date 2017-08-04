package org.ejfr.fintonic.fintonic.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**Class that manages the https connections.
 * The connections are https, by default it accepts all connections.
 *
 * Created by EmilioJosé on 04/08/2017.
 */

public class HttpUtils {

    /**Method that returns the data read by the string_url given as argument
     *
     * @param string_url String. This is the URL to establish connection
     * @return String. This is the data read
     */
    public static String getDataFromURL(String string_url){
        HttpsURLConnection con = null ;
        InputStream is = null;

        try {
            trustAllHosts();
            con = (HttpsURLConnection) ( new URL(string_url)).openConnection();
            con.setRequestMethod("GET");

            con.connect();

            // Let's read the response
            StringBuffer buffer = new StringBuffer();
            is = con.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ( (line = br.readLine()) != null )
                buffer.append(line + "rn");

            is.close();
            con.disconnect();
            return buffer.toString();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;
    }

    /**Method that creates an SSL Contexts which trusts in all hosts
     *
     */
    private static void trustAllHosts()
    {
        // Create a trust manager that does not validate certificate chains
        TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
            @Override
            public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {

            }

            @Override
            public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws java.security.cert.CertificateException {

            }

            @Override
            public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                return new java.security.cert.X509Certificate[0];
            }
        }};

        // Install the all-trusting trust manager
        try
        {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, trustAllCerts, new java.security.SecureRandom());
            HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**Method that returns byte[] which the image content in the url given as argument
     *
     * @param string_url String. This is the url which contains the image
     * @return byte[]. This is the image read
     */
    public static byte[] getImageFromUrl(String string_url){
        HttpsURLConnection con = null ;
        InputStream is = null;

        try {
            trustAllHosts();
            con = (HttpsURLConnection) ( new URL(string_url)).openConnection();
            con.setRequestMethod("GET");

            con.connect();

            // Let's read the response
            is = con.getInputStream();
            byte[] buffer = new byte[1024];
            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            while ( is.read(buffer) != -1)
                baos.write(buffer);

            is.close();
            con.disconnect();
            return baos.toByteArray();
        }
        catch(Throwable t) {
            t.printStackTrace();
        }
        finally {
            try { is.close(); } catch(Throwable t) {}
            try { con.disconnect(); } catch(Throwable t) {}
        }

        return null;
    }
}
