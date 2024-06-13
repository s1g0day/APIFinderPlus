package utils;

import burp.BurpExtender;
import burp.IHttpRequestResponse;
import burp.IHttpService;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.Map;
import java.util.zip.GZIPInputStream;


public class BurpHttpUtils {
    public static int MaxResponseContentLength = 500000;

    public static Map<String, Object> makeGetRequest(Map<String, Object> pathDataModel) {
        Map<String, Object> onePathData = (Map<String, Object>) pathDataModel.get("path_data");
        onePathData.put("path", pathDataModel.get("path"));
        onePathData.put("url", pathDataModel.get("url"));
        // 解析URL
        String host = (String) onePathData.get("host");
        // 使用Number作为中间类型，以应对可能不同的数字类型
        Number portNumber = (Number) onePathData.get("port");
        int port = portNumber.intValue();
        String protocol = (String) onePathData.get("protocol");
        String path = (String) onePathData.get("path");
        // 创建IHttpService对象
        IHttpService httpService = BurpExtender.getHelpers().buildHttpService(host, port, protocol);

        // 构造GET请求的字节数组
        String request = "GET " + path + " HTTP/1.1\r\n" +
                "Host: " + host + "\r\n" +
                "User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36" + "\r\n" +
                "\r\n";
        byte[] requestBytes = request.getBytes();

        // 初始化返回数据结构
        onePathData.put("method", "GET");
        onePathData.put("time", new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date()));

        IHttpRequestResponse requestResponse = null;

        try {
            // 发起请求
            requestResponse = BurpExtender.getCallbacks().makeHttpRequest(httpService, requestBytes);
            // 空检查
            if (requestResponse == null || requestResponse.getResponse() == null) {
                throw new IllegalStateException("Request failed, no response received.");
            }

            // 获取响应字节
            byte[] responseBytes = requestResponse.getResponse();
            responseBytes = responseBytes.length  > MaxResponseContentLength ? Arrays.copyOf(responseBytes, MaxResponseContentLength) : responseBytes;
            String statusCode = String.valueOf(BurpExtender.getCallbacks().getHelpers().analyzeResponse(responseBytes).getStatusCode());

            // 添加请求和响应数据到返回数据结构
            onePathData.put("requests", Base64.getEncoder().encodeToString(requestBytes));
            onePathData.put("response", Base64.getEncoder().encodeToString(responseBytes));
            onePathData.put("status", statusCode);
        } catch (Exception e) {
            // 异常处理，记录错误信息
            onePathData.put("status", "请求报错");
            onePathData.put("requests", Base64.getEncoder().encodeToString(requestBytes));
            onePathData.put("response", Base64.getEncoder().encodeToString(e.getMessage().getBytes()));
        }

        return onePathData;

    }

    /**
     * 实现Gzip数据的解压
     * @param compressed
     * @return
     * @throws IOException
     */
    public static byte[] gzipDecompress(byte[] compressed) throws IOException {
        if (compressed == null || compressed.length == 0) {
            return null;
        }

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPInputStream gunzip = new GZIPInputStream(new ByteArrayInputStream(compressed));

        byte[] buffer = new byte[256];
        int n;
        while ((n = gunzip.read(buffer)) >= 0) {
            out.write(buffer, 0, n);
        }

        // Close the streams
        gunzip.close();
        out.close();

        // Get the uncompressed data
        return out.toByteArray();
    }

    /**
     * 实现多个bytes数组的相加
     * @param arrays
     * @return
     */
    public static byte[] concatenateByteArrays(byte[]... arrays) {
        int length = 0;
        for (byte[] array : arrays) {
            length += array.length;
        }

        byte[] result = new byte[length];
        int offset = 0;
        for (byte[] array : arrays) {
            System.arraycopy(array, 0, result, offset, array.length);
            offset += array.length;
        }
        return result;
    }
}
