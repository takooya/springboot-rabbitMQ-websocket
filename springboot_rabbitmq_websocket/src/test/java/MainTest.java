import redis.clients.jedis.Jedis;

public class MainTest {
    public static void main(String args[]) {
        String host = "101.133.175.232";
        int port = 6379;
        Jedis jedis = null;
        try {
            jedis = new Jedis(host, port);//建立连接
            jedis.auth("Linze5558");//设置连接密码
            jedis.select(1);//选着数据保存在哪个数据库db0 db1 ...
            jedis.set("name", "takooya");
            String name = jedis.get("name");
            System.out.println("name = " + name);
            jedis.flushDB();//清空数据库
            String name2 = jedis.get("name");
            System.out.println("name2 = " + name2);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != jedis) {
                try {
                    jedis.close();
                } catch (Exception e) {
                    System.out.println("redis连接关闭失败");
                    e.printStackTrace();
                }
            }
        }
    }
}
