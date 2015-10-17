package model;

import com.mongodb.BasicDBObject;
import service.Util;


import java.util.Map;

/**
 * Created by sasinda on 10/14/15.
 */
public class Query {
    public static  final String START="start";
    public static  final String LIMIT="limit";

    private int start=0;
    private int limit=10;
    private BasicDBObject filter=new BasicDBObject();

    Map<String, String[]> query;

    /**
     * Make a query object using the query string.
     * @param query
     */
    public Query(Map<String, String[]> query) {
        this.query = query;

        String[] start = query.get(START);
        if(start!=null&& start.length==1){
            this.start =new Integer(start[0]);
            query.remove(START);
        }
        String[] limit = query.get(LIMIT);
        if(limit!=null&&limit.length==1){
            this.limit =new Integer(limit[0]);
            query.remove(LIMIT);
        }
        getFilter().putAll(Util.deParam(query));
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public BasicDBObject getFilter() {
        return filter;
    }

    public void setFilter(BasicDBObject filter) {
        this.filter = filter;
    }
}
