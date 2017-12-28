import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.io.*;


public class test {
    public String callSoap(String url, String body) throws Exception {
        HttpURLConnection con = null;
        try {
            URL ourl = new URL(url);
            con = (HttpURLConnection) ourl.openConnection();
            con.setDoOutput(true);
            con.setRequestMethod("POST");

            /* con.setRequestProperty("Content-Type","application/soap+xml");*/
            OutputStream reqStream = con.getOutputStream();
            reqStream.write(body.getBytes("UTF-8"));
            // java.io.DataOutputStream dos = new java.io.DataOutputStream(con.getOutputStream());
            // dos.writeBytes(body);
            // String result = CharStreams.toString(new InputStreamReader(
            //     dos,"utf8"));
                // System.out.println(result);
            InputStream resStream = con.getInputStream();
            byte[] byteBuf = new byte[128];
            int len = 0;
            StringBuffer sb = new StringBuffer();
            while ((len = resStream.read(byteBuf)) != -1) {
                sb.append(new String(byteBuf, 0, len));
            }
            return sb.toString();

        } finally {
            try {
                if (con != null) {
                    con.disconnect();
                }
            } catch (Exception ex) {

            }
        }

    }

    public static void main(String[] args) {
        test t = new test();
        String str = "abc=§A¦n";
        try{
        byte[] test1 = new byte[str.getBytes("utf8").length];
        test1 = str.getBytes("utf8");
        System.out.println(test1.length);
        String bytesToString = new String(test1,StandardCharsets.UTF_8);
        System.out.println("bbb: "+bytesToString);
        for(int i=0 ;i<test1.length;i++){
            System.out.println((char)test1[i]);
        }
        System.out.println(str);
        }catch(Exception ex){
            System.out.println(ex);
        }
        try {
            String aaa = t.callSoap("http://54.173.144.156:3000/java", str);
            System.out.println(aaa);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}