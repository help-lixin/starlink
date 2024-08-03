package help.lixin.starlink.plugin.rocketmq.convert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: 伍岳林
 * @Date: 2023/10/18 2:31 下午
 * @Description
 */
public class TopicRule {

    public Integer booleanToInteger(Boolean order) {
        return order == true ? 1 : 0;
    }

    public Date longToDate(Long lastUpdateTimestamp) throws ParseException {
        String formatStr = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat sd = new SimpleDateFormat(formatStr);
        // long转Date
        Date date = new SimpleDateFormat(formatStr).parse(sd.format(new Date(lastUpdateTimestamp)));
        return date;
    }

}
