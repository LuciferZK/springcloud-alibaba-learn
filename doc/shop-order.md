断点打在rocketMQ事务监听器的两个方法中，executeLocalTransaction中
和checkLocalTransaction方法中，断点走到 orderServiceImpl4.createOrder(txId,(Order) o);，
然后使用命令：`taskkill -F /pid** 21816` （
21816是进程号），kill掉order服务，然后重启order服务，模拟网络异常。
重启后断点会自动到达checkLocalTransaction中打断点的位置。