package com.yongqiang.wms.common.utils;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yongqiang.wms.mapper.WmsInInfoMapper;
import com.yongqiang.wms.model.stock.WmsInInfo;
import com.yongqiang.wms.model.stock.WmsInInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import javax.annotation.PostConstruct;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 单号生成工具类 生成规则为RKYYYYMMDDXXXXXX
 * XXXXXX代表count 6位 从000001 => 999999 一天最多只能生成100W的单据号数据量
 *
 * @author yang.shang
 * @create 2019-05-30 10:11
 **/
@Component
public class CodeProductUtil {

    @Autowired
    private WmsInInfoMapper wmsInInfoMapper;
    /**
     * 使用AtomicInteger 类控制自增的原子性以及保证读取的缓存一致性
     */
    private static AtomicInteger count = new AtomicInteger();

    private static Date  lastDay = new Date();

    private static String prefix = "RX";

    ////程序刚启动时 需要去数据库中查询当天count的最大值 初始化时进行count的查询
    @PostConstruct //在依赖注入完成后进行初始化操作
    public void init(){
        //查询当天单据号中最大的count
        int curCount = getCountFromDB();
        count.set(curCount);

        //设置count的计数过期时间
        lastDay = getEndDay();
    }
    /**
     * 自增方法 获取的是自增后的数
     */
    private int increase(){
        return count.incrementAndGet();
    }
    /**
     * 生成入库单号
     * @return
     */
    public synchronized String getWhsInCode(){
        //这里需要进行时间的判断，假如已经过了一天 则需要进行count值的归零处理
        Date curDate = new Date();
        if (curDate.getTime() > lastDay.getTime() ){
            count.set(0);
        }

        //自增
        String countVal = String.format("%06d",increase());
        String dateStr = new SimpleDateFormat("yyyyMMdd").format(new Date());
        return prefix+dateStr+countVal;
    }

    /**
     * 从数据库中查询当前count的最大值
     * @return
     */
    public int getCountFromDB(){
        Calendar todayBegin = new GregorianCalendar();
        todayBegin.set(Calendar.HOUR_OF_DAY,0);
        todayBegin.set(Calendar.MINUTE,0);
        todayBegin.set(Calendar.SECOND,0);

        WmsInInfoDto query = new WmsInInfoDto();
        query.setCreateStartTime(todayBegin.getTime());
        query.setCreateEndTime(getEndDay());
        WmsInInfo lastRecord = wmsInInfoMapper.getLastRecordByDate(query);
        try {
            return Integer.parseInt(lastRecord.getWhsInCode().substring(10));
        }
        catch (Exception e){
            return 0;
        }
    }

    private Date getEndDay(){
        Calendar todayEnd = Calendar.getInstance();
        todayEnd.set(Calendar.HOUR_OF_DAY, 23);
        todayEnd.set(Calendar.MINUTE, 59);
        todayEnd.set(Calendar.SECOND, 59);
        return todayEnd.getTime();
    }
}
