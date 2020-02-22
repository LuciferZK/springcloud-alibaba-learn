**zipkin持久化:**
1.MySQL
启动命令：`java -jar zipkin-server-2.12.9-exec.jar --STORAGE_TYPE=mysql --MYSQL_HOST=127.0.0.1 --MYSQL_TCP_PORT=3306 --MYSQL_DB=zipkin --MYSQL_USER=root --MYSQL_PASS=123456`
2.elasticsearch
下载：https://www.elastic.co/cn/downloads/elasticsearch
启动命令：`java -jar zipkin-server-2.12.9-exec.jar --STORAGE_TYPE=elasticsearch --ES-HOST=127.0.0.1:9200 `
