package com.zhzx.uip.commons.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * 加密工具类.
 * 基于MD5
 */
public class PWCode {

    private static Logger logger = LoggerFactory.getLogger(PWCode.class);

    private final static long[] LONGARRAY = { ~((long) -1 >> 64 << 8),
            ~((long) -1 >> 64 << 16), ~((long) -1 >> 64 << 24),
            ~((long) -1 >> 64 << 32), ~((long) -1 >> 64 << 40),
            ~((long) -1 >> 64 << 48), ~((long) -1 >> 64 << 56),
            ~((long) -1 >> 64 << 64) };

    private final static char hexDigits[] = { '0', '1', '2', '3', '4', '5',
            '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
            'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
            'W', 'X', 'Y', 'Z', '$', '@' };
    
    private final static String[] MD5hexDigits = {"0", "1", "2", "3", "4",
        "5", "6", "7", "8", "9", "a", "b", "c", "d", "e", "f"};
    
    /**
     * 获取iZENEsoft加密密码字符串
     * 
     * @param content 字符串内容。
     * @return 长度为22的字符串
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.UnsupportedEncodingException
     */
    public final static String getPassWordCode(String content) {
        return getMD5Code_64(getMD5Code_16(content));
    }

    /**
     * 获取MD5的64进制表示码
     *
     * @param content 字符串内容。
     * @return 长度为22的字符串
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.UnsupportedEncodingException
     */
    public final static String getMD5Code_64(String content) {
        if (content == null) {
            content = "";
        }

        byte tmp[] = getMD5ByteArray(content); // MD5 的计算结果是一个 128 位的长整数，
        long h = (((long) tmp[0] & LONGARRAY[0])
                | (((long) tmp[1] << 8) & LONGARRAY[1])
                | (((long) tmp[2] << 16) & LONGARRAY[2])
                | (((long) tmp[3] << 24) & LONGARRAY[3])
                | (((long) tmp[4] << 32) & LONGARRAY[4])
                | (((long) tmp[5] << 40) & LONGARRAY[5])
                | (((long) tmp[6] << 48) & LONGARRAY[6]) | ((long) tmp[7] << 56));

        long l = (((long) tmp[8] & LONGARRAY[0])
                | (((long) tmp[9] << 8) & LONGARRAY[1])
                | (((long) tmp[10] << 16) & LONGARRAY[2])
                | (((long) tmp[11] << 24) & LONGARRAY[3])
                | (((long) tmp[12] << 32) & LONGARRAY[4])
                | (((long) tmp[13] << 40) & LONGARRAY[5])
                | (((long) tmp[14] << 48) & LONGARRAY[6]) | ((long) tmp[15] << 56));
        return getCode64(h, l);
    }

    /**
     * 获取MD5的16进制表示码
     *
     * @param content 字符串
     * @return 长度为32的字符串
     * @throws java.security.NoSuchAlgorithmException
     * @throws java.io.UnsupportedEncodingException
     */
    public final static String getMD5Code_16(String content) {
        if (content == null) {
            content = "";
        }
        // 用来将字节转换成 16 进制表示的字符
        byte tmp[] = getMD5ByteArray(content); // MD5 的计算结果是一个 128 位的长整数，
        char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符，
        // 所以表示成 16 进制需要 32 个字符
        int k = 0; // 表示转换结果中对应的字符位置
        for (int i = 0; i < 16; i++) { // 从第一个字节开始，对 MD5 的每一个字节
            byte byte0 = tmp[i]; // 取第 i 个字节
            str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换,
            str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
        }
        return String.valueOf(str);
    }

    private static String getCode64(long h, long l) {
        char[] re = new char[22];
        long blk = (long) 63;
        long tail = (long) 3;
        re[0] = hexDigits[(int) (l & blk)];
        re[1] = hexDigits[(int) ((l & (blk << 6)) >>> 6)];
        re[2] = hexDigits[(int) ((l & (blk << 12)) >>> 12)];
        re[3] = hexDigits[(int) ((l & (blk << 18)) >>> 18)];
        re[4] = hexDigits[(int) ((l & (blk << 24)) >>> 24)];
        re[5] = hexDigits[(int) ((l & (blk << 30)) >>> 30)];
        re[6] = hexDigits[(int) ((l & (blk << 36)) >>> 36)];
        re[7] = hexDigits[(int) ((l & (blk << 42)) >>> 42)];
        re[8] = hexDigits[(int) ((l & (blk << 48)) >>> 48)];
        re[9] = hexDigits[(int) ((l & (blk << 52)) >>> 54)];
        re[10] = hexDigits[(int) (((h & tail) << 4) | (l & (blk << 60)) >>> 60)];
        re[11] = hexDigits[(int) ((h & (blk << 2)) >>> 2)];
        re[12] = hexDigits[(int) ((h & (blk << 8)) >>> 8)];
        re[13] = hexDigits[(int) ((h & (blk << 14)) >>> 14)];
        re[14] = hexDigits[(int) ((h & (blk << 20)) >>> 20)];
        re[15] = hexDigits[(int) ((h & (blk << 26)) >>> 26)];
        re[16] = hexDigits[(int) ((h & (blk << 32)) >>> 32)];
        re[17] = hexDigits[(int) ((h & (blk << 38)) >>> 38)];
        re[18] = hexDigits[(int) ((h & (blk << 44)) >>> 44)];
        re[19] = hexDigits[(int) ((h & (blk << 50)) >>> 50)];
        re[20] = hexDigits[(int) ((h & (blk << 56)) >>> 56)];
        re[21] = hexDigits[(int) ((h & (blk << 62)) >>> 62)];
        return String.valueOf(re);
    }

    private static byte[] getMD5ByteArray(String content) {
        byte[] source = null;
        MessageDigest md = null;
        try {
            source = content.getBytes("UTF-8");
            md = MessageDigest.getInstance("MD5");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        md.update(source);
        return md.digest();
    }
    
    /**
     * 获取MD5加密后的小写字符串
     * 
     * @return
     */
    public static String getMD5String(String originString) {
        if (originString != null) {
            try{
                MessageDigest md = MessageDigest.getInstance("MD5");
                byte[] results = md.digest(originString.getBytes());
                String resultString = byteArrayToHexString(results);
                return resultString;
            } catch(Exception ex) {
                logger.error("异常", ex);
            }
        }
        return null;
    }
    
    
    private static String byteArrayToHexString(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            resultSb.append(byteToHexString(b[i]));
        }
        return resultSb.toString();
    }
    
    
    private static String byteToHexString(byte b) {
        int n = b;
        if (n < 0)
            n = 256 + n;
        int d1 = n / 16;
        int d2 = n % 16;
        return MD5hexDigits[d1] + MD5hexDigits[d2];
    }
    
    /**
     * 生成去'-'的UUID
     * 
     * @return	UUID
     */
    public static String getUUID(){
    	return UUID.randomUUID().toString().replaceAll("-", "");
    }   
  
//    public static void main(String[] args) {
//    	String code = PWCode.getPassWordCode("111111");
//    	System.out.println(code);
//	}
}
