package auto.core.util;
 
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
 

/**
 * 〈分组公共类〉
 * 〈对list按照某个值进行分组〉
 * @author mengxy[孟祥元]
 * @version 2015年12月14日
 * @see GroupUtils
 * @since 1.0
 */
public class GroupUtils{
 

    /**
     * 〈分組依据接口，用于集合分組时，获取分组依据〉
     * 〈功能详细描述〉
     * @author mengxy[孟祥元]
     * @version 2015年12月14日
     * @see GroupBy
     * @since 1.0
     */
    public interface GroupBy<T> {
        T groupby(Object obj) ;
    }   
    
    /**
     * Description:分组 <br>
     * 1、…<br>
     * 2、…<br>
     * Implement: <br>
     * 1、…<br>
     * 2、…<br>
     * 
     * @param colls
     * @param gb
     * @return 
     * @see 
     */
    public static final <T extends Comparable<T> ,D> Map<T ,List<D>> group(Collection<D> colls ,GroupBy<T> gb){
        if(colls == null || colls.isEmpty()) {
            return null ;
        }
        if(gb == null) {
            return null ;
        }
        Iterator<D> iter = colls.iterator() ;
        Map<T ,List<D>> map = new HashMap<T, List<D>>() ;
        while(iter.hasNext()) {
            D d = iter.next() ;
            T t = gb.groupby(d) ;
            if(map.containsKey(t)) {
                map.get(t).add(d) ;
            } else {
                List<D> list = new ArrayList<D>() ;
                list.add(d) ;
                map.put(t, list) ;
            }
        }
        return map ;
    }   
    
//    写法如下：
//    Map<String, List<PriceReturnInfo>> priceReturnInfoMap = GroupUtils.group(data, new GroupBy<String>() {
//        @Override
//        public String groupby(Object obj) {
//            PriceReturnInfo priceReturnInfo = (PriceReturnInfo) obj;
//            return priceReturnInfo.getShowPk(); // 分组依据为场次ID
//        }
//    });
     
}