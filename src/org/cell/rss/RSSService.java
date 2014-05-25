package org.cell.rss;

import com.mongodb.*;
import com.mongodb.util.JSON;
import org.springframework.stereotype.Service;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

/**
 * 功能:
 *
 * @author chengb
 * @date 2014-05-24
 */
@Service
public class RSSService {

    private DB db() throws UnknownHostException, MongoException {
        DB db = null;
        try {
            //实例化Mongo对象，连接27017端口
            Mongo mongo = new Mongo("localhost", 27017);
            //连接名为yourdb的数据库，假如数据库不存在的话，mongodb会自动建立
            db = mongo.getDB("cell");
            return db;
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }
        return db;

    }

    public List<RSS> getRSS() {
        List<RSS> rssList = new ArrayList<RSS>();
        try {
            DB db = db();

            DBCollection collection = db.getCollection("rss");
            BasicDBObject searchQuery = new BasicDBObject();

            DBCursor cursor = collection.find(searchQuery);


            while (cursor.hasNext()) {
                DBObject next = cursor.next();
                RSS rss = new RSS();

                rss.setAuthor(next.get("author").toString());
                rss.setUrl(next.get("url").toString());

                rssList.add(rss);
            }
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return rssList;

    }

    public static void main(String[] args) throws UnknownHostException, MongoException {
        try {
            //实例化Mongo对象，连接27017端口
            Mongo mongo = new Mongo("localhost", 27017);
            //连接名为yourdb的数据库，假如数据库不存在的话，mongodb会自动建立
            DB db = mongo.getDB("cell");
            // Get collection from MongoDB, database named "yourDB"
            //从Mongodb中获得名为yourColleection的数据集合，如果该数据集合不存在，Mongodb会为其新建立
            DBCollection collection = db.getCollection("rss");
            // 使用BasicDBObject对象创建一个mongodb的document,并给予赋值。
            BasicDBObject document = new BasicDBObject();
//            document.put("author", "thomas kyte");
//            document.put("url", "https://asktom.oracle.com/pls/apex/asktom.popular.rss?p_count=10");
            document.put("author", "Tanel Poder");
            document.put("url", "http://blog.tanelpoder.com/comments/feed/");


            //将新建立的document保存到collection中去
            collection.insert(document);
            // 创建要查询的document
//            BasicDBObject searchQuery = new BasicDBObject();
//            searchQuery.put("id", 1001);
            // 使用collection的find方法查找document
//             collection.findAndRemove(searchQuery);
            //循环输出结果
//            while (cursor.hasNext()) {
//                System.out.println(cursor.next());
//            }
            System.out.println("Done");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (MongoException e) {
            e.printStackTrace();
        }

    }
}
