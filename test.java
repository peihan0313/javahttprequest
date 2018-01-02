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

            con.setRequestProperty("Content-Type","application/soap+xml");
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
        String str =""; 
        str = str + "<soap12:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap12=\"http://www.w3.org/2003/05/soap-envelope\">";
        str = str + "<soap12:Body>";
        str = str + "<MsgData104 xmlns=\"http://tempuri.org/\">";
        str = str + "<name>104測試</name>";
        str = str + "<email>104test@104.com</email>";
        str = str + "<phone></phone>";
        str = str + "<mobile>0910104104</mobile>";
        str = str + "<class_info>12,000堂電腦線上課程</class_info>";
        str = str + "<branch_id>HE</branch_id>";
        str = str + "<inputdate>2017/12/29</inputdate>";
        str = str + "<message>你的職場分量是「M」</message>";
        str = str + "<pno>10612B000009</pno>";
        str = str + "<mad_no>99005127</mad_no>";
        str = str + "<gift></gift>";
        str = str + "<fromurl>https://m.learn.s104.com.tw/mobile/project/gjun20171228/index.html</fromurl>";
        str = str + "</MsgData104>";
        str = str + "</soap12:Body>";
        str = str + "</soap12:Envelope>";
        try{
        byte[] test1 = new byte[str.getBytes("utf8").length];
        test1 = str.getBytes("utf8");
        System.out.println(test1.length);
        String bytesToString = new String(test1,StandardCharsets.UTF_8);
        System.out.println("bbb: "+bytesToString);
        }catch(Exception ex){
            System.out.println(ex);
        }
        try {
            String aaa = t.callSoap("https://www.pcschool.com.tw/wsb2b.asmx?WSDL", str);
            System.out.println("feedback:"+aaa);
        } catch (Exception ex) {
            System.out.println(ex);
        }
    }
}
