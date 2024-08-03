package help.lixin.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Md5Crypt {

    private static Logger logger = LoggerFactory.getLogger(Md5Crypt.class);

    public static String md5Crypt(String body) {
        try {
            MessageDigest md = MessageDigest.getInstance("md5");
            byte[] bytes = md.digest(body.getBytes(StandardCharsets.UTF_8));
            String md5 = Base64.getEncoder().encodeToString(bytes);
            return md5;
        } catch (NoSuchAlgorithmException e) {
            logger.error("没有找到md5算法");
        }
        return null;
    }
}
